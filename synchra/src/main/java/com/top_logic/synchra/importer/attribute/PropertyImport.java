package com.top_logic.synchra.importer.attribute;

import java.util.List;

import com.top_logic.basic.config.InstantiationContext;
import com.top_logic.basic.config.PolymorphicConfiguration;
import com.top_logic.model.TLObject;
import com.top_logic.synchra.importer.ImportUtil;
import com.top_logic.synchra.importer.transformer.IdentityTransformer;

/**
 * used to import a simple property like String or Double
 */
public class PropertyImport extends AttributeImport {

	public interface Config extends PolymorphicConfiguration {
		String getName();
	}

	public PropertyImport(InstantiationContext context, Config config) {
		super(ImportUtil.getProperty(config.getName()), IdentityTransformer.INSTANCE);
	}

	@Override
	public void performImport(TLObject object, List<Object> values) {
		Object excelValue = values.get(getColumn());
		Object importValue = getImportValue(excelValue);
		object.tUpdateByName(getName(), importValue);
	}
}