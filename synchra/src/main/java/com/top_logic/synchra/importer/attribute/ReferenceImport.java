package com.top_logic.synchra.importer.attribute;

import java.util.Collection;
import java.util.Map;

import com.top_logic.basic.StringServices;
import com.top_logic.basic.config.InstantiationContext;
import com.top_logic.basic.config.PolymorphicConfiguration;
import com.top_logic.basic.config.annotation.defaults.BooleanDefault;
import com.top_logic.model.TLClass;
import com.top_logic.model.TLObject;
import com.top_logic.model.TLReference;
import com.top_logic.model.TLStructuredTypePart;
import com.top_logic.synchra.importer.ImportUtil;
import com.top_logic.synchra.importer.transformer.ReferenceTransformer;
import com.top_logic.synchra.importer.transformer.ValueTransformer;

/**
 * used to import a reference. The {@link ValueTransformer} must provide the referenced object(s).
 * The {@link ReferenceImport} takes care of the direction of the reference.
 */
public class ReferenceImport extends AttributeImport {

	public interface Config extends PolymorphicConfiguration {

		/**
		 * @return the name of the attribute to import, full qualified
		 */
		String getName();

		/**
		 * @return the name of the {@link TLClass} to which the transformer transforms
		 */
		String getTlClass();

		/**
		 * @return the name of the {@link TLClass} to which the transformer creates when no object
		 *         is existing if no create class is given no objects are created
		 */
		String getCreateTlClass();

		/**
		 * @return the name of the attribute which provides the primary key
		 */
		String getIdAttribute();

		/**
		 * @return flase if no reiport is possible. Used for primary key attributes
		 */
		@BooleanDefault(true)
		boolean getReImport();
	}

	private boolean _reImport;

	public ReferenceImport(InstantiationContext context, Config config) {
		super(ImportUtil.getPart(config.getName()), getTransformer(config));
		_reImport = config.getReImport();
	}

	private static ReferenceTransformer getTransformer(Config config) {
		TLClass tlClass = ImportUtil.getTlClass(config.getTlClass());
		String idAttribute = config.getIdAttribute();
		String createClassName = config.getCreateTlClass();
		TLClass tlCreateClass = null;
		if (!StringServices.isEmpty(createClassName)) {
			tlCreateClass = ImportUtil.getTlClass(createClassName);
		}
		return new ReferenceTransformer(tlClass, idAttribute, tlCreateClass);
	}

	@Override
	public void performImport(TLObject object, Map<Integer, Object> values) {
		Object excelValue = values.get(getColumn());
		if (excelValue != null) {
			Object importValue = getImportValue(excelValue);
			if (getPart().isBackwards()) {
				doSetValue(getPart().getOpposite(), (TLObject) importValue, object);
			} else {
				doSetValue(getPart(), object, importValue);
			}
		}
	}

	private void doSetValue(TLStructuredTypePart attr, TLObject importObject, Object importValue) {
		Object existing = importObject.tValue(attr);
		if (attr.isMultiple()) {
			if (existing instanceof Collection) {
				Collection<?> col = (Collection<?>) existing;
				if (!col.contains(importValue)) {
					importObject.tAdd(attr, importValue);
				}
			} else {
				importObject.tAdd(attr, importValue);
			}
		}
		else {
			// single values: set value
			importObject.tUpdateByName(attr.getName(), importValue);
		}
	}


	@Override
	protected Object getImportValue(Object excelValue) {
		Object value = super.getImportValue(excelValue);
//		if (getPart().isMultiple()) {
//			if (!(value instanceof Collection)) {
//				return Collections.singleton(value);
//			}
//		}
		return value;
	}


	@Override
	public TLReference getPart() {
		return (TLReference) super.getPart();
	}
}