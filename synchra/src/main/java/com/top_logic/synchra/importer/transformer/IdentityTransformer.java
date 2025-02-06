package com.top_logic.synchra.importer.transformer;

/**
 * uses the excel value itsself as import value
 */
public class IdentityTransformer implements ValueTransformer {
	public static final IdentityTransformer INSTANCE = new IdentityTransformer();
	@Override
	public Object get(Object excelValue) {
		return excelValue;
	}
}