package com.top_logic.synchra.importer.attribute;

import java.util.Map;

import com.top_logic.basic.config.InstantiationContext;
import com.top_logic.basic.config.PolymorphicConfiguration;
import com.top_logic.basic.config.annotation.defaults.StringDefault;
import com.top_logic.model.TLObject;
import com.top_logic.synchra.importer.ImportUtil;
import com.top_logic.synchra.importer.transformer.IdentityTransformer;
import com.top_logic.synchra.importer.transformer.RelativeDateTransformer;
import com.top_logic.synchra.importer.transformer.ValueTransformer;

/**
 * used to import a simple property like String or Double
 */
public class PropertyImport extends AttributeImport {

	public interface Config extends PolymorphicConfiguration {
		String getName();

		@StringDefault("identity")
		String getType();
	}

	public PropertyImport(InstantiationContext context, Config config) {
		super(ImportUtil.getProperty(config.getName()), getTransformer(config.getType()));
	}

	@Override
	public void performImport(TLObject object, Map<Integer, Object> values) {
		Object excelValue = values.get(getColumn());
		Object importValue = getImportValue(excelValue);
		object.tUpdateByName(getName(), importValue);
	}

	private static ValueTransformer getTransformer(String type) {
		if("identity".equals(type)) {
			return IdentityTransformer.INSTANCE;
		}
		if("relativeDate".equals(type)) {
			return RelativeDateTransformer.INSTANCE;
		}
		else {
			throw new RuntimeException("Unknown type for transformer: "+type);
		}
	}
}