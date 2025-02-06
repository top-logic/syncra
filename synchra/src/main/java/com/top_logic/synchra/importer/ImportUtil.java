package com.top_logic.synchra.importer;

import com.top_logic.layout.provider.MetaLabelProvider;
import com.top_logic.model.DerivedTLTypePart;
import com.top_logic.model.TLClass;
import com.top_logic.model.TLProperty;
import com.top_logic.model.TLReference;
import com.top_logic.util.model.ModelService;

public class ImportUtil {

	public static TLClass getTlClass(String tlClassString) {
		String[] split = tlClassString.split(":");
		return (TLClass) ModelService.getApplicationModel().getModule(split[0]).getType(split[1]);
	}

	public static TLProperty getProperty(String property) {
		return (TLProperty) getDerivedPart(property);
	}

	public static TLReference getPart(String part) {
		return (TLReference) getDerivedPart(part);
	}

	public static DerivedTLTypePart getDerivedPart(String part) {
		String[] moduleAndClassAndName = part.split(":");
		String module = moduleAndClassAndName[0];
		String[] classAndName = moduleAndClassAndName[1].split("#");
		String type = classAndName[0];
		String attribute = classAndName[1];
		TLClass tlClass = (TLClass) ModelService.getApplicationModel().getModule(module).getType(type);
		return tlClass.getPart(attribute);
	}

	/**
	 * @param obj
	 *        the object to get a label for
	 * @return the label for the given object in the language of the current user
	 */
	public static String label(Object obj) {
		return MetaLabelProvider.INSTANCE.getLabel(obj);
	}
}
