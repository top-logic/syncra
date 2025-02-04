package com.top_logic.synchra.importer.attribute;

import java.util.List;

import com.top_logic.basic.config.InstantiationContext;
import com.top_logic.basic.config.PolymorphicConfiguration;
import com.top_logic.model.TLClass;
import com.top_logic.model.TLObject;
import com.top_logic.model.TLProperty;
import com.top_logic.synchra.importer.transformer.IdentityTransformer;
import com.top_logic.synchra.importer.transformer.ValueTransformer;
import com.top_logic.util.model.ModelService;

/**
 * used to import a simple property like String or Double
 */
public class PropertyImport extends AttributeImport {

	public interface Config extends PolymorphicConfiguration {
		String getProperty();
	}

	public PropertyImport(InstantiationContext context, Config config) {
		this(getProperty(config.getProperty()));
	}

	private static TLProperty getProperty(String property) {
		String[] moduleAndClassAndName = property.split(":");
		String module = moduleAndClassAndName[0];
		String[] classAndName = moduleAndClassAndName[1].split("#");
		String type = classAndName[0];
		String attribute = classAndName[1];
		TLClass tlClass = (TLClass) ModelService.getApplicationModel().getModule(module).getType(type);
		return (TLProperty) tlClass.getPart(attribute);
	}

	public PropertyImport(TLProperty property) {
		this(property, IdentityTransformer.INSTANCE);
	}

	public PropertyImport(TLProperty property, ValueTransformer valueProvider) {
		super(property, valueProvider);
	}

	@Override
	public void performImport(TLObject object, List<Object> values) {
		Object excelValue = values.get(getColumn());
		Object importValue = getImportValue(excelValue);
		object.tUpdateByName(getName(), importValue);
	}
}