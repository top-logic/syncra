package com.top_logic.synchra.importer.attribute;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.top_logic.basic.config.InstantiationContext;
import com.top_logic.basic.config.PolymorphicConfiguration;
import com.top_logic.model.TLObject;
import com.top_logic.model.TLReference;
import com.top_logic.model.TLStructuredTypePart;
import com.top_logic.synchra.importer.ImportUtil;
import com.top_logic.synchra.importer.transformer.ValueTransformer;

/**
 * used to import a reference. The {@link ValueTransformer} must provide the referenced object(s).
 * The {@link ReferenceImport} takes care of the direction of the reference.
 */
public class ReferenceImport extends AttributeImport {

	public interface Config extends PolymorphicConfiguration {
		String getName();

		PolymorphicConfiguration<ValueTransformer> getValueTransformer();
	}

	public ReferenceImport(InstantiationContext context, Config config) {
		super(ImportUtil.getPart(config.getName()), context.getInstance(config.getValueTransformer()));
	}

	@Override
	public void performImport(TLObject object, List<Object> values) {

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