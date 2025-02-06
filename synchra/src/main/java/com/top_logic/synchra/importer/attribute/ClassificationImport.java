package com.top_logic.synchra.importer.attribute;

import java.util.Map;

import com.top_logic.basic.config.InstantiationContext;
import com.top_logic.basic.config.PolymorphicConfiguration;
import com.top_logic.model.TLObject;
import com.top_logic.synchra.importer.ImportUtil;
import com.top_logic.synchra.importer.transformer.ClassificationTransformer;

public class ClassificationImport extends AttributeImport {

	public interface Config extends PolymorphicConfiguration {
		String getName();
		String getEnumeration();
	}

	public ClassificationImport(InstantiationContext context, Config config) {
		super(ImportUtil.getPart(config.getName()), getTransformer(config));
	}

	private static ClassificationTransformer getTransformer(Config config) {
		return new ClassificationTransformer(config.getEnumeration());
	}

	@Override
	public void performImport(TLObject object, Map<Integer, Object> values) {
		Object excelValue = values.get(getColumn());
		Object importValue = getImportValue(excelValue);
		object.tUpdateByName(getName(), importValue);
	}

}
