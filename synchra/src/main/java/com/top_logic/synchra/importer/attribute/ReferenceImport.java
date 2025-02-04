package com.top_logic.synchra.importer.attribute;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.top_logic.basic.config.InstantiationContext;
import com.top_logic.basic.config.PolymorphicConfiguration;
import com.top_logic.model.TLClass;
import com.top_logic.model.TLObject;
import com.top_logic.model.TLReference;
import com.top_logic.model.TLStructuredTypePart;
import com.top_logic.synchra.importer.transformer.ValueTransformer;
import com.top_logic.util.model.ModelService;

/**
 * used to import a reference. The {@link ValueTransformer} must provide the referenced object(s).
 * The {@link ReferenceImport} takes care of the direction of the reference.
 */
public class ReferenceImport extends AttributeImport {

	public interface Config extends PolymorphicConfiguration {
		String getPart();

		PolymorphicConfiguration<ValueTransformer> getValueTransformer();
	}

	public ReferenceImport(InstantiationContext context, Config config) {
		this(getPart(config.getPart()), context.getInstance(config.getValueTransformer()));
	}

	private static TLReference getPart(String part) {
		String[] moduleAndClassAndName = part.split(":");
		String module = moduleAndClassAndName[0];
		String[] classAndName = moduleAndClassAndName[1].split("#");
		String type = classAndName[0];
		String attribute = classAndName[1];
		TLClass tlClass = (TLClass) ModelService.getApplicationModel().getModule(module).getType(type);
		return (TLReference) tlClass.getPart(attribute);
	}

	public ReferenceImport(TLReference part, ValueTransformer valueTransformer) {
		super(part, valueTransformer);
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