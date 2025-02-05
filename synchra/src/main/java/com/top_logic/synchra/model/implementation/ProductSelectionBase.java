/*
 * SPDX-FileCopyrightText: 2025 (c) Business Operation Systems GmbH <info@top-logic.com>
 * 
 * SPDX-License-Identifier: AGPL-3.0-only OR LicenseRef-BOS-TopLogic-1.0
 */
package com.top_logic.synchra.model.implementation;

/**
 * Basic interface for {@link #PRODUCT_SELECTION_TYPE} business objects.
 * 
 * @author Automatically generated by {@link com.top_logic.element.model.generate.InterfaceGenerator}
 */
public interface ProductSelectionBase extends com.top_logic.model.TLObject {

	/**
	 * Name of type <code>ProductSelection</code>
	 */
	String PRODUCT_SELECTION_TYPE = "ProductSelection";

	/**
	 * Part <code>country</code> of <code>ProductSelection</code>
	 * 
	 * <p>
	 * Declared as <code>syn:Country</code> in configuration.
	 * </p>
	 */
	String COUNTRY_ATTR = "country";

	/**
	 * Part <code>product</code> of <code>ProductSelection</code>
	 * 
	 * <p>
	 * Declared as <code>syn:Product</code> in configuration.
	 * </p>
	 */
	String PRODUCT_ATTR = "product";

	/**
	 * Part <code>referenceDate</code> of <code>ProductSelection</code>
	 * 
	 * <p>
	 * Declared as <code>tl.core:DateTime</code> in configuration.
	 * </p>
	 */
	String REFERENCE_DATE_ATTR = "referenceDate";

	/**
	 * Part <code>rule</code> of <code>ProductSelection</code>
	 * 
	 * <p>
	 * Declared as <code>tl.core:String</code> in configuration.
	 * </p>
	 */
	String RULE_ATTR = "rule";

	/**
	 * Getter for part {@link #COUNTRY_ATTR}.
	 */
	default com.top_logic.synchra.model.interfaces.Country getCountry() {
		return (com.top_logic.synchra.model.interfaces.Country) tValueByName(COUNTRY_ATTR);
	}

	/**
	 * Setter for part {@link #COUNTRY_ATTR}.
	 */
	default void setCountry(com.top_logic.synchra.model.interfaces.Country newValue) {
		tUpdateByName(COUNTRY_ATTR, newValue);
	}

	/**
	 * Getter for part {@link #PRODUCT_ATTR}.
	 */
	default com.top_logic.synchra.model.interfaces.Product getProduct() {
		return (com.top_logic.synchra.model.interfaces.Product) tValueByName(PRODUCT_ATTR);
	}

	/**
	 * Setter for part {@link #PRODUCT_ATTR}.
	 */
	default void setProduct(com.top_logic.synchra.model.interfaces.Product newValue) {
		tUpdateByName(PRODUCT_ATTR, newValue);
	}

	/**
	 * Getter for part {@link #REFERENCE_DATE_ATTR}.
	 */
	default java.util.Date getReferenceDate() {
		return (java.util.Date) tValueByName(REFERENCE_DATE_ATTR);
	}

	/**
	 * Setter for part {@link #REFERENCE_DATE_ATTR}.
	 */
	default void setReferenceDate(java.util.Date newValue) {
		tUpdateByName(REFERENCE_DATE_ATTR, newValue);
	}

	/**
	 * Getter for part {@link #RULE_ATTR}.
	 */
	default String getRule() {
		return (String) tValueByName(RULE_ATTR);
	}

	/**
	 * Setter for part {@link #RULE_ATTR}.
	 */
	default void setRule(String newValue) {
		tUpdateByName(RULE_ATTR, newValue);
	}

}
