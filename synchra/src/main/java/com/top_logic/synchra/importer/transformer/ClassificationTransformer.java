package com.top_logic.synchra.importer.transformer;

import com.top_logic.model.TLClassifier;
import com.top_logic.model.TLEnumeration;
import com.top_logic.synchra.importer.ImportUtil;
import com.top_logic.util.model.ModelService;

/**
 * uses the given {@link TLEnumeration} for finding the import value. The german name of the
 * enumeration elements is used to find the right value
 */
public class ClassificationTransformer implements ValueTransformer {

	private TLEnumeration _tlEnumeration;

	public ClassificationTransformer(String enumName) {
		_tlEnumeration = getEnumeration(enumName);
	}

	private static TLEnumeration getEnumeration(String enumName) {
		String[] split = enumName.split(":");
		return (TLEnumeration) ModelService.getApplicationModel().getModule(split[0]).getType(split[1]);
	}

	@Override
	public Object get(Object excelValue) {
		for (TLClassifier cl : _tlEnumeration.getClassifiers()) {
			if (ImportUtil.label(cl).equals(excelValue)) {
				return cl;
			}
		}
		return null;
	}

}