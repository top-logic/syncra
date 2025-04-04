package com.top_logic.synchra.importer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import com.top_logic.basic.Logger;
import com.top_logic.basic.StringServices;
import com.top_logic.basic.config.CommaSeparatedStrings;
import com.top_logic.basic.config.InstantiationContext;
import com.top_logic.basic.config.PolymorphicConfiguration;
import com.top_logic.basic.config.annotation.Format;
import com.top_logic.model.TLClass;
import com.top_logic.model.TLNamed;
import com.top_logic.model.TLObject;
import com.top_logic.synchra.importer.attribute.AttributeImport;
import com.top_logic.synchra.importer.attribute.ClassificationImport;
import com.top_logic.synchra.importer.attribute.PropertyImport;
import com.top_logic.synchra.importer.attribute.ReferenceImport;
import com.top_logic.synchra.model.ModelFactory;

public class TypeImporter {

	private TLClass _tlClass;

	private Map<String, AttributeImport> _attributesByName;

	private Map<Integer, Map<Integer, Object>> _importData;

	private Map<String, AttributeImport> _attributesByHeaderName;

	private List<String> _idAttributes;

	private ImportSession _session;

	public interface Config extends PolymorphicConfiguration {
		/**
		 * @return the name of the {@link TLClass} to import
		 */
		String getTlClass();

		/**
		 * @return a list of {@link ClassificationImport} to import
		 */
		List<PolymorphicConfiguration<ClassificationImport>> getClassifications();

		/**
		 * @return a list of {@link PropertyImport} to import
		 */
		List<PolymorphicConfiguration<PropertyImport>> getProperties();

		/**
		 * @return a list of {@link ReferenceImport} to import
		 */
		List<PolymorphicConfiguration<ReferenceImport>> getReferences();

		/**
		 * @return names of attributes that build the primary key of an object.
		 */
		@Format(CommaSeparatedStrings.class)
		List<String> getIdAttributes();

	}

	public TypeImporter(InstantiationContext context, Config config) {
		_tlClass = ImportUtil.getTlClass(config.getTlClass());
		_idAttributes = config.getIdAttributes();
		initAttributes(getAttributes(context, config));
	}

	private static Set<AttributeImport> getAttributes(InstantiationContext context, Config config) {
		Set<AttributeImport> attributes = new HashSet<>();
		for (PolymorphicConfiguration<PropertyImport> tif : config.getProperties()) {
			attributes.add(context.getInstance(tif));
		}
		for (PolymorphicConfiguration<ReferenceImport> tif : config.getReferences()) {
			attributes.add(context.getInstance(tif));
		}
		for (PolymorphicConfiguration<ClassificationImport> tif : config.getClassifications()) {
			attributes.add(context.getInstance(tif));
		}
		return attributes;
	}


	protected ImportSession getSession() {
		return _session;
	}

	public void prepare(ImportSession session) {
		_session = session;
		_importData = new HashMap<>();
		for (AttributeImport ad : _attributesByName.values()) {
			ad.prepare(session);
		}
	}


	private void initAttributes(Set<AttributeImport> attributes) {
		_attributesByName = new HashMap<>();
		_attributesByHeaderName = new HashMap<>();
		for (AttributeImport ad : attributes) {
			_attributesByName.put(ad.getName(), ad);
			_attributesByHeaderName.put(ad.getHeaderName(), ad);
		}
		for (String attr : _idAttributes) {
			if (!_attributesByName.containsKey(attr)) {
				throw new RuntimeException("Attributes must contain idAttribute " + attr);
			}
		}
	}
	

	public String getSheetName() {
		return ImportUtil.label(_tlClass);
	}

	/**
	 * translate a given object in a String ID
	 * 
	 * @param obj
	 *        the existing object
	 * @return a string representing a primary key for the object
	 */
	protected String asIdString(TLObject obj) {
		List<String> idParts = new ArrayList<>();
		for (String idAttr : _idAttributes) {
			Object val = obj.tValueByName(idAttr);
			if (val instanceof TLNamed) {
				idParts.add(((TLNamed) val).getName().trim());
			} else {
				if (val == null) {
					idParts.add("null");
				}
				else {
					idParts.add(val.toString().trim());
				}
			}
		}
		return StringServices.toString(idParts, "-");
	}

	/**
	 * @param values
	 *        values holding data for a primary key
	 * @return extracted primary key from the values
	 */
	private String getAsId(Map<Integer, Object> values) {
		List<String> idParts = new ArrayList<>();
		for (String idAttr : _idAttributes) {
			int idCol = _attributesByName.get(idAttr).getColumn();
			Object val = values.get(idCol);
			val = val == null ? "null" : val;
			idParts.add(val.toString().trim());
		}
		return StringServices.toString(idParts, "-");
	}

	public void performImport() {
		Logger.info("Start import for: " + _tlClass.getName(), TypeImporter.class);
		Set<TLObject> all = getSession().getAllInstancesOfType(_tlClass);
		Map<String, TLObject> existing = new HashMap<>();
		for(TLObject obj : all) {
			existing.put(asIdString(obj), obj);
		}
		for (Map<Integer, Object> values : _importData.values()) {

			String id = getAsId(values);
			TLObject existingObject = existing.get(id);
			if (existingObject == null) {
				Logger.info("Create instance: " + _tlClass.getName(), TypeImporter.class);
				existingObject = ModelFactory.getInstance().createObject(_tlClass);
				// set values
				for (AttributeImport ad : _attributesByName.values()) {
					ad.performImport(existingObject, values);
				}
				afterCreation(existingObject);
				register(existingObject);
			}
			else{
				// set values
				Logger.info("Set value for existing: " + _tlClass.getName(), TypeImporter.class);

				// beware: do not set again the attributes for primary key
				for (AttributeImport ad : _attributesByName.values()) {
					ad.performImport(existingObject, values);
				}
			}
		}
		Logger.info("Done import for: " + _tlClass.getName(), TypeImporter.class);
	}

	protected void register(TLObject existingObject) {
		getSession().register(existingObject);
	}

	/**
	 * @param newObject
	 *        the new created object
	 */
	public void afterCreation(TLObject newObject) {
	}

	public void checkData() {
	}

	public void addRow(Row row) {
		int rowNum = row.getRowNum();
		Map<Integer, Object> values = new HashMap<>();
		for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
			Cell cell = row.getCell(i);
			if (cell != null) {
				CellType cellType = cell.getCellType();
				if (CellType.STRING == cellType) {
					values.put(i, cell.getStringCellValue());
				} else if (CellType.NUMERIC == cellType) {
					values.put(i, cell.getNumericCellValue());
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
			if (!StringServices.isEmpty(value)) {
				AttributeImport description = _attributesByHeaderName.get(value);
				if (description == null) {
					throw getException("Not allowed header: " + value);
				}
				description.setColumn(i);
			}
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