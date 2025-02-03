package com.top_logic.synchra.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
import com.top_logic.model.TLProperty;
import com.top_logic.model.TLReference;
import com.top_logic.model.TLStructuredTypePart;
import com.top_logic.synchra.model.ModelFactory;
import com.top_logic.synchra.model.interfaces.Activity;
import com.top_logic.synchra.model.interfaces.Company;
import com.top_logic.synchra.model.interfaces.ConstructionGroup;
import com.top_logic.synchra.model.interfaces.Material;
import com.top_logic.synchra.model.interfaces.PartCatalog;
import com.top_logic.synchra.model.interfaces.Product;
import com.top_logic.synchra.model.interfaces.SinglePart;
import com.top_logic.synchra.risk.RiskFactory;
import com.top_logic.synchra.risk.interfaces.Risk;

public class SynchraImporter extends POIExcelImporter {




	private static final Set<AttributeImport> MATERIAL_ATTS =
		new SetBuilder<AttributeImport>().add(new PropertyImport(ModelFactory.getNameMaterialAttr())).toSet();

	private static final Set<AttributeImport> CATALOG_ATTS =
		new SetBuilder<AttributeImport>().add(new PropertyImport(ModelFactory.getNamePartCatalogAttr())).toSet();

	private static final Set<AttributeImport> GESELLSCHAFT_ATTS =
		new SetBuilder<AttributeImport>()
			.add(new PropertyImport(ModelFactory.getNameCompanyAttr()))
			.add(new PropertyImport(ModelFactory.getContactPersonCompanyAttr()))
			.add(new PropertyImport(ModelFactory.getPhoneCompanyAttr()))
			.add(new ReferenceImport(ModelFactory.getStructureCompanyAttr(),
				new TLEnumerationValueProvider(ModelFactory.getCompanyStructureEnum())))
			.toSet();

	private static final TLClass TEIL = ModelFactory.getSinglePartType();

	private static final Set<AttributeImport> PRODUKT_ATTS =
		new SetBuilder<AttributeImport>().add(new PropertyImport(ModelFactory.getNameProductAttr())).toSet();

	private static final Set<AttributeImport> ACTIVITY_ATTS = new SetBuilder<AttributeImport>()
		.add(new PropertyImport(ModelFactory.getNameActivityAttr()))
		.add(new PropertyImport(ModelFactory.getCostsActivityAttr()))
		.add(new PropertyImport(ModelFactory.getRiskProbImpactActivityAttr()))
		.add(new PropertyImport(ModelFactory.getRiskCostImpactActivityAttr()))
		.toSet();


	private TypeImporter _materialImporter;
	private TypeImporter _catalogImporter;
	private TypeImporter _companyImporter;
	private TypeImporter _partsImporter;

	private Map<String, TypeImporter> _typeImporters;

	private TypeImporter _productImporter;

	private TypeImporter _riskImporter;

	private TypeImporter _activityImporter;
	
	
	public SynchraImporter(BinaryContent importSource) {
		super(importSource);
		setupTypeImporters();
	}

	private void setupTypeImporters() {
		_typeImporters = new HashMap<>();

		_materialImporter =
			new TypeImporter(ModelFactory.getMaterialType(), Material.class, MATERIAL_ATTS, Material.NAME);
		_catalogImporter =
			new TypeImporter(ModelFactory.getPartCatalogType(), PartCatalog.class, CATALOG_ATTS, PartCatalog.NAME);
		_companyImporter =
			new TypeImporter(ModelFactory.getCompanyType(), Company.class, GESELLSCHAFT_ATTS, Company.NAME);
		Set<AttributeImport> teileAttrs = getTeileAttrs(_materialImporter, _catalogImporter, _companyImporter);

		_partsImporter = new TypeImporter(TEIL, SinglePart.class, teileAttrs, SinglePart.NAME);

		_productImporter =
			new ProductImporter(ModelFactory.getProductType(), Product.class, PRODUKT_ATTS, Product.NAME);

		_activityImporter =
			new TypeImporter(ModelFactory.getActivityType(), Activity.class, ACTIVITY_ATTS, Activity.NAME);

		Set<AttributeImport> riskAttrs = getRiskAttrs(_activityImporter);
		_riskImporter =
			new TypeImporter(RiskFactory.getRiskType(), Risk.class, riskAttrs, Risk.NAME);



		registerImporter(_materialImporter);
		registerImporter(_catalogImporter);
		registerImporter(_companyImporter);
		registerImporter(_partsImporter);
		registerImporter(_productImporter);
		registerImporter(_riskImporter);
		registerImporter(_activityImporter);
	}
	
	private void registerImporter(TypeImporter importer) {
		_typeImporters.put(importer.getSheetName(), importer);
	}

	private Set<AttributeImport> getRiskAttrs(TypeImporter activityImporter) {
		ValueProvider activityValueProvider = new ObjectValueProvider(activityImporter);
		return new SetBuilder<AttributeImport>()
			.add(new PropertyImport(RiskFactory.getNameRiskAttr()))
			.add(new PropertyImport(RiskFactory.getEstimatedDamageRiskAttr()))
			.add(new PropertyImport(RiskFactory.getEstimatedProbabilityRiskAttr()))
			.add(new ReferenceImport(RiskFactory.getActivitiesRiskAttr(), activityValueProvider))
			.toSet();
	}

	private Set<AttributeImport> getTeileAttrs(TypeImporter materialImporter, TypeImporter catalogImporter,
			TypeImporter companyImporter) {
		ValueProvider materialValueProvider = new ObjectValueProvider(materialImporter);
		ValueProvider catalogValueProvider = new ObjectValueProvider(catalogImporter);
		ValueProvider companyValueProvider = new ObjectValueProvider(companyImporter);
		return
			new SetBuilder<AttributeImport>()
				.add(new PropertyImport(ModelFactory.getNameComponentAttr()))
				.add(new PropertyImport(ModelFactory.getPriceComponentAttr()))
				.add(new ReferenceImport(ModelFactory.getMaterialSinglePartAttr(), materialValueProvider))
				.add(new ReferenceImport(ModelFactory.getCatalogSinglePartAttr(), catalogValueProvider))
				.add(new ReferenceImport(ModelFactory.getCompanySinglePartAttr(), companyValueProvider))
				.toSet();
	}
	
	

	private static String germanName(Object tlClass) {
		return MetaLabelProvider.INSTANCE.getLabel(tlClass);
	}

	public void checkData() {
		_materialImporter.checkData();
		_catalogImporter.checkData();
		_companyImporter.checkData();
		_partsImporter.checkData();
		_productImporter.checkData();
		_riskImporter.checkData();
		_activityImporter.checkData();
	}

	public void performImport() {
		_materialImporter.performImport();
		_catalogImporter.performImport();
		_companyImporter.performImport();
		_partsImporter.performImport();
		_productImporter.performImport();
		_activityImporter.performImport();
		_riskImporter.performImport();
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

		private Map<String, AttributeImport> _attributesByName;

		private Map<Integer, List<Object>> _importData;

		private Class _type;

		private String _idAttribute;

		private Map<String, AttributeImport> _attributesByHeaderName;

		private Map<String, TLObject> _imported;

		public TypeImporter(TLClass tlClass, Class type, Set<AttributeImport> attributes, String idAttribute) {
			_tlClass = tlClass;
			_type = type;
			_idAttribute = idAttribute;
			_attributesByName = new HashMap<>();
			_attributesByHeaderName = new HashMap<>();
			for (AttributeImport ad : attributes) {
				_attributesByName.put(ad.getName(), ad);
				_attributesByHeaderName.put(ad.getHeaderName(), ad);
			}
			if (!_attributesByName.containsKey(idAttribute)) {
				throw new RuntimeException("Attributes must contain idAttribute " + idAttribute);
			}
			_importData = new HashMap<>();
			_imported = new HashMap<>();
		}
		
		public String getSheetName() {
			return germanName(_tlClass);
		}

		public void performImport() {
			List<TLObject> all = AttributedUtil.getAllInstancesOfType(_tlClass, _type);
			Map<String, TLObject> existing = new HashMap<>();
			for(TLObject obj : all) {
				existing.put(obj.tGetDataString(_idAttribute), obj);
			}
			for (List<Object> values : _importData.values()) {
				int idCol = _attributesByName.get(_idAttribute).getColumn();
				String id = values.get(idCol).toString().trim();
				TLObject existingObject = existing.get(id);
				if (existingObject == null) {
					existingObject = ModelFactory.getInstance().createObject(_tlClass);
					for (AttributeImport ad : _attributesByName.values()) {
						ad.performImport(existingObject, values);
					}
					afterCreation(existingObject);
				}
				_imported.put(id, existingObject);
			}
		}

		/**
		 * @param newObject
		 *        the new created object
		 */
		public void afterCreation(TLObject newObject) {
		}

		public TLObject get(String id) {
			return _imported.get(id);
		}

		public void checkData() {
		}

		public void addRow(Row row) {
			int rowNum = row.getRowNum();
			List<Object> values = new ArrayList<>();
			for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
				Cell cell = row.getCell(i);
				if (cell != null) {
					CellType cellType = cell.getCellType();
					if (CellType.STRING == cellType) {
						values.add(cell.getStringCellValue());
					} else if (CellType.NUMERIC == cellType) {
						values.add(cell.getNumericCellValue());
					} else if (CellType.BLANK == cellType) {
						// ignore
					} else {
						throw new RuntimeException("Can not handle " + cellType);
					}
				}
			}
			if (!values.isEmpty()) {
				_importData.put(rowNum, values);
			}
		}

		// check that for all attributes the corresponding header is set. Store the column in
		// the attribute
		public void initHeaders(Row row) throws IOException {
			for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
				String value = row.getCell(i).getStringCellValue();
				AttributeImport description = _attributesByHeaderName.get(value);
				if (description == null) {
					throw getException("Not allowed header: " + value);
				}
				description.setColumn(i);
			}
			for (AttributeImport ad : _attributesByName.values()) {
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

	private static interface ValueProvider {
		/**
		 * @param excelValue
		 *        the value read from excel
		 * @return the java value for import
		 */
		Object get(Object excelValue);
	}

	/**
	 * uses the excel value itsself as import value
	 */
	private static class DirectValueProvider implements ValueProvider {
		public static final DirectValueProvider INSTANCE = new DirectValueProvider();
		@Override
		public Object get(Object excelValue) {
			return excelValue;
		}
	}

	/**
	 * uses the given {@link TLEnumeration} for finding the import value. The german name of the
	 * enumeration elements is used to find the right value
	 */
	private static class TLEnumerationValueProvider implements ValueProvider {

		private TLEnumeration _tlEnumeration;

		public TLEnumerationValueProvider(TLEnumeration tlEnumeration) {
			_tlEnumeration = tlEnumeration;
		}

		@Override
		public Object get(Object excelValue) {
			for (TLClassifier cl : _tlEnumeration.getClassifiers()) {
				if (germanName(cl).equals(excelValue)) {
					return cl;
				}
			}
			return null;
		}
	}

	/**
	 * uses the created values of an {@link TypeImporter} as objects to set. They are identified by
	 * their value in the idColumn
	 */
	private static class ObjectValueProvider implements ValueProvider {

		private TypeImporter _importer;

		public ObjectValueProvider(TypeImporter importer) {
			_importer = importer;
		}

		@Override
		public Object get(Object excelValue) {
			String rawValue = (String) excelValue;
			String[] split = rawValue.split(",");
			if (split.length > 1) {
				List<Object> res = new ArrayList();
				for (String val : split) {
					res.add(_importer.get(val.trim()));
				}
				return res;
			} else {
				return _importer.get(split[0]);
			}
		}
	}


	private static class ReferenceImport extends AttributeImport {

		public ReferenceImport(TLReference part, ValueProvider valueProvider) {
			super(part, valueProvider);
		}

		@Override
		protected void performImport(TLObject object, List<Object> values) {

			Object excelValue = values.get(getColumn());
			Object importValue = getImportValue(excelValue);
			if (getPart().isBackwards()) {
				TLStructuredTypePart directAttribute = getPart().getOpposite();
				((TLObject) importValue).tAdd(directAttribute, object);
			} else {
				object.tUpdateByName(getName(), importValue);
			}
		}

		@Override
		protected Object getImportValue(Object excelValue) {
			Object value = super.getImportValue(excelValue);
			if (getPart().isMultiple()) {
				if (!(value instanceof Collection)) {
					return Collections.singleton(value);
				}
			}
			return value;
		}


		@Override
		public TLReference getPart() {
			return (TLReference) super.getPart();
		}
	}


	private static class PropertyImport extends AttributeImport {

		public PropertyImport(TLProperty property) {
			this(property, DirectValueProvider.INSTANCE);
		}

		public PropertyImport(TLProperty property, ValueProvider valueProvider) {
			super(property, valueProvider);
		}

		@Override
		protected void performImport(TLObject object, List<Object> values) {
			Object excelValue = values.get(getColumn());
			Object importValue = getImportValue(excelValue);
			object.tUpdateByName(getName(), importValue);
		}
	}
	

	private abstract static class AttributeImport {

		private int _col;

		private DerivedTLTypePart _part;

		/**
		 * translates the excel value in a java value or object
		 */
		private ValueProvider _valueProvider;

		public AttributeImport(DerivedTLTypePart part) {
			this(part, DirectValueProvider.INSTANCE);
		}

		public AttributeImport(DerivedTLTypePart part, ValueProvider valueProvider) {
			_part = part;
			_valueProvider = valueProvider;
			_col = -10;
		}

		protected abstract void performImport(TLObject object, List<Object> values);

		protected Object getImportValue(Object excelValue) {
			return _valueProvider.get(excelValue);
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

		/**
		 * @return the german name of the part
		 */
		public String getHeaderName() {
			return germanName(_part);
		}

		public String getName() {
			return getPart().getName();
		}

		public DerivedTLTypePart getPart() {
			return _part;
		}

	}

	private static class ProductImporter extends TypeImporter {

		public ProductImporter(TLClass tlClass, Class type, Set<AttributeImport> attributes, String idAttribute) {
			super(tlClass, type, attributes, idAttribute);
		}

		/**
		 * @param newObject
		 *        the new created object
		 */
		@Override
		public void afterCreation(TLObject newObject) {
			Product product = (Product) newObject;
			
			// create constructionGroup for the new product
			ConstructionGroup constructionGroup = ModelFactory.getInstance().createConstructionGroup();
			constructionGroup.setName("BG " + product.getName());
			product.setBuildGroup(constructionGroup);
//			con -> x -> y -> $x.set(`fma:Product#buildGroup`, 
//				new(`fma:ConstructionGroup`, transient: false)
//				..set(`fma:ConstructionGroup#name`,"BG "+$x.get(`fma:Product#name`))
//					)    
		}

	}

}
