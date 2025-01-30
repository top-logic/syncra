package com.top_logic.synchra.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import com.top_logic.basic.Logger;
import com.top_logic.basic.col.SetBuilder;
import com.top_logic.basic.io.BinaryContent;
import com.top_logic.contact.business.POIExcelImporter;
import com.top_logic.layout.provider.MetaLabelProvider;
import com.top_logic.model.DerivedTLTypePart;
import com.top_logic.model.TLClass;
import com.top_logic.model.TLClassifier;
import com.top_logic.model.TLEnumeration;
import com.top_logic.model.TLObject;
import com.top_logic.synchra.model.ModelFactory;
import com.top_logic.synchra.model.interfaces.Company;
import com.top_logic.synchra.model.interfaces.Material;
import com.top_logic.synchra.model.interfaces.PartCatalog;

public class CatalogImporter extends POIExcelImporter {



	private static final TLClass MATERIAL = ModelFactory.getMaterialType();
	private static final Set<AttributeDescription> MATERIAL_ATTS =
		new SetBuilder<AttributeDescription>().add(new StringAttribute(ModelFactory.getNameMaterialAttr())).toSet();

	private static final TLClass CATALOG = ModelFactory.getPartCatalogType();
	private static final Set<AttributeDescription> CATALOG_ATTS =
		new SetBuilder<AttributeDescription>().add(new StringAttribute(ModelFactory.getNamePartCatalogAttr())).toSet();


	private static final TLClass GESELLSCHAFT = ModelFactory.getCompanyType();

	private static final Set<AttributeDescription> GESELLSCHAFT_ATTS =
		new SetBuilder<AttributeDescription>()
			.add(new StringAttribute(ModelFactory.getNameCompanyAttr()))
			.add(new StringAttribute(ModelFactory.getContactPersonCompanyAttr()))
			.add(new StringAttribute(ModelFactory.getPhoneCompanyAttr()))
			.add(new StructureAttribute(ModelFactory.getStructureCompanyAttr(), ModelFactory.getCompanyStructureEnum()))

			.toSet();

	private static final TLClass TEIL = ModelFactory.getSinglePartType();

	private static final Set<AttributeDescription> TEIL_ATTS =
		new SetBuilder<AttributeDescription>()
			.add(new StringAttribute(ModelFactory.getNameComponentAttr()))
			.add(new DoubleAttribute(ModelFactory.getPriceComponentAttr()))
			.add(new StringAttribute(ModelFactory.getMaterialCompanyPartCatalogAttr()))
//			.add(new StructureAttribute(ModelFactory.getStructureCompanyAttr()))

			.toSet();

	private Map<String, TypeImporter> _typeImporters;

	public CatalogImporter(BinaryContent importSource) {
		super(importSource);
		setupTypeImporters();
	}

	private void setupTypeImporters() {
		_typeImporters = new HashMap<>();
		_typeImporters.put(germanName(MATERIAL),
			new TypeImporter(MATERIAL, Material.class, MATERIAL_ATTS, Material.NAME));

		_typeImporters.put(germanName(CATALOG),
			new TypeImporter(CATALOG, PartCatalog.class, CATALOG_ATTS, PartCatalog.NAME));

		_typeImporters.put(germanName(GESELLSCHAFT),
			new TypeImporter(GESELLSCHAFT, Company.class, GESELLSCHAFT_ATTS, Company.NAME));

//		_typeImporters.put(GESELLSCHAFT, new TypeImporter(GESELLSCHAFT,))
	}

	private static String germanName(Object tlClass) {
		return MetaLabelProvider.INSTANCE.getLabel(tlClass);
	}

	public void checkData() {
		for (TypeImporter ti : _typeImporters.values()) {
			ti.checkData();
		}
	}

	public void performImport() {
		_typeImporters.get(germanName(MATERIAL)).performImport();
		_typeImporters.get(germanName(CATALOG)).performImport();
		_typeImporters.get(germanName(GESELLSCHAFT)).performImport();
	}

	/**
	 * Check that the first row (as found in CSVTokenizer) is as expected.
	 * 
	 * @throws IOException
	 *         when format is not OK.
	 * 
	 * @return false to indicate that no check is needed, (assuming first line contains data)
	 */
	@Override
	protected boolean checkColumnFormat(int aSheetNum, String sheetname, Row row) throws IOException {
		TypeImporter ti = _typeImporters.get(sheetname);
		ti.initHeaders(row);
		return true;
	}

	@Override
	protected void importRow(int aSheetNum, String sheetname, Row row) throws Exception {
		TypeImporter ti = _typeImporters.get(sheetname);
		
		ti.addRow(row);
		
		// TODO store the data in transient structure
	}

	/**
	 * Check if the current sheet defined by numSheet and sheetName should be imported or ignored.
	 * By default, only the first sheet should be imported.
	 */
	@Override
	protected boolean shoudImportSheet(int numSheet, String sheetName) {
		return _typeImporters.keySet().contains(sheetName);
	}

	private static class TypeImporter {

		private TLClass _tlClass;

		private Map<String, AttributeDescription> _attributesByName;

		private Map<Integer, List<Object>> _importData;

		private Class _type;

		private String _idAttribute;

		private Map<String, AttributeDescription> _attributesByHeaderName;

		public TypeImporter(TLClass tlClass, Class type, Set<AttributeDescription> attributes, String idAttribute) {
			_tlClass = tlClass;
			_type = type;
			_idAttribute = idAttribute;
			_attributesByName = new HashMap<>();
			_attributesByHeaderName = new HashMap<>();
			for (AttributeDescription ad : attributes) {
				_attributesByName.put(ad.getName(), ad);
				_attributesByHeaderName.put(ad.getHeaderName(), ad);
			}
			if (!_attributesByName.containsKey(idAttribute)) {
				throw new RuntimeException("Attributes must contain idAttribute " + idAttribute);
			}
			_importData = new HashMap<>();
		}

		public void performImport() {
			List<TLObject> all = AttributedUtil.getAllInstancesOfType(_tlClass, _type);
			Map<String, Object> existing = new HashMap<>();
			for(TLObject obj : all) {
				existing.put(obj.tGetDataString(_idAttribute), obj);
			}
			for (List<Object> values : _importData.values()) {
				int idCol = _attributesByName.get(_idAttribute).getColumn();
				String id = values.get(idCol).toString();
				if (!existing.keySet().contains(id)) {
					TLObject object = ModelFactory.getInstance().createObject(_tlClass);
					for (AttributeDescription ad : _attributesByName.values()) {
						ad.performImport(object, values);
					}
				}
			}
		}

		public void checkData() {
			int i = 0;
		}

		public void addRow(Row row) {
			int rowNum = row.getRowNum();
			List<Object> values = new ArrayList<>();
			for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
				Cell cell = row.getCell(i);
				CellType cellType = cell.getCellType();
				if (CellType.STRING == cellType) {
					values.add(cell.getStringCellValue());
				} else if (CellType.NUMERIC == cellType) {
					values.add(cell.getNumericCellValue());
				} else {
					throw new RuntimeException("Can not handle " + cellType);
				}
			}
			_importData.put(rowNum, values);
		}

		// check that for all attributes the corresponding header is set. Store the column in
		// the attribute
		public void initHeaders(Row row) throws IOException {
			for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
				String value = row.getCell(i).getStringCellValue();
				AttributeDescription description = _attributesByHeaderName.get(value);
				if (description == null) {
					throw getException("Not allowed header: " + value);
				}
				description.setColumn(i);
			}
			for (AttributeDescription ad : _attributesByName.values()) {
				if (!ad.hasColumn()) {
					throw getException("No column for: " + ad.getHeaderName());
				}
			}
		}

		private IOException getException(String txt) {
			Logger.error(txt, TypeImporter.class);
			return new IOException(txt);
		}

	}

	private static abstract class AttributeDescription {

		private String _headerName;

		private int _col;

		private DerivedTLTypePart _part;

		public AttributeDescription(DerivedTLTypePart part) {
			_part = part;
			_headerName = germanName(part);
			_col = -10;
		}

		protected void performImport(TLObject object, List<Object> values) {
			object.tUpdateByName(getName(), values.get(getColumn()));
		}

		public int getColumn() {
			return _col;
		}

		public void setColumn(int col) {
			_col = col;
		}

		public boolean hasColumn() {
			return _col > -10;
		}

		public String getHeaderName() {
			return _headerName;
		}

		public String getName() {
			return getPart().getName();
		}

		public DerivedTLTypePart getPart() {
			return _part;
		}

	}

	private static class StringAttribute extends AttributeDescription {

		public StringAttribute(DerivedTLTypePart tlProperty) {
			super(tlProperty);
		}

	}

	private static class StructureAttribute extends AttributeDescription {
		private TLEnumeration _tlEnumeration;

		public StructureAttribute(DerivedTLTypePart tlProperty, TLEnumeration tlEnumeration) {
			super(tlProperty);
			_tlEnumeration = tlEnumeration;
		}

		@Override
		protected void performImport(TLObject object, List<Object> values) {
			Object value = values.get(getColumn());
			for (TLClassifier cl : _tlEnumeration.getClassifiers()) {
				if (germanName(cl).equals(value)) {
					object.tUpdateByName(getName(), cl);
					break;
				}
			}
		}
	}

	private static class DoubleAttribute extends AttributeDescription {
		public DoubleAttribute(DerivedTLTypePart tlProperty) {
			super(tlProperty);
		}
	}

}
