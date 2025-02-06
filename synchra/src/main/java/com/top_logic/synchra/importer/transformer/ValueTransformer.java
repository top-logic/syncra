package com.top_logic.synchra.importer.transformer;

import com.top_logic.synchra.importer.ImportSession;

/**
 * Transforms a value read from excel to the value to be used as attribute value in the java object
 */
public interface ValueTransformer {
	/**
	 * @param excelValue
	 *        the value read from excel
	 * @return the java value for import
	 */
	Object get(Object excelValue);

	/**
	 * prepares the provider for the given session
	 * 
	 * @param session
	 *        the session to use from now on
	 */
	default void prepare(ImportSession session) {
		// does nothing by default
	};
}