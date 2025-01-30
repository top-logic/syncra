/*
 * SPDX-FileCopyrightText: 2025 (c) Business Operation Systems GmbH <info@top-logic.com>
 * 
 * SPDX-License-Identifier: AGPL-3.0-only OR LicenseRef-BOS-TopLogic-1.0
 */
package com.top_logic.synchra.model.implementation;

/**
 * Basic interface for {@link #COMPANY_TYPE} business objects.
 * 
 * @author Automatically generated by {@link com.top_logic.element.model.generate.InterfaceGenerator}
 */
public interface CompanyBase extends com.top_logic.model.TLNamed {

	/**
	 * Name of type <code>Company</code>
	 */
	String COMPANY_TYPE = "Company";

	/**
	 * Part <code>contactPerson</code> of <code>Company</code>
	 * 
	 * <p>
	 * Declared as <code>tl.core:String</code> in configuration.
	 * </p>
	 */
	String CONTACT_PERSON_ATTR = "contactPerson";

	/**
	 * Part <code>name</code> of <code>Company</code>
	 * 
	 * <p>
	 * Declared as <code>tl.core:String</code> in configuration.
	 * </p>
	 */
	String NAME_ATTR = "name";

	/**
	 * Part <code>phone</code> of <code>Company</code>
	 * 
	 * <p>
	 * Declared as <code>tl.core:String</code> in configuration.
	 * </p>
	 */
	String PHONE_ATTR = "phone";

	/**
	 * Part <code>structure</code> of <code>Company</code>
	 * 
	 * <p>
	 * Declared as <code>fma:CompanyStructure</code> in configuration.
	 * </p>
	 */
	String STRUCTURE_ATTR = "structure";

	/**
	 * Getter for part {@link #CONTACT_PERSON_ATTR}.
	 */
	default String getContactPerson() {
		return (String) tValueByName(CONTACT_PERSON_ATTR);
	}

	/**
	 * Setter for part {@link #CONTACT_PERSON_ATTR}.
	 */
	default void setContactPerson(String newValue) {
		tUpdateByName(CONTACT_PERSON_ATTR, newValue);
	}

	/**
	 * Getter for part {@link #PHONE_ATTR}.
	 */
	default String getPhone() {
		return (String) tValueByName(PHONE_ATTR);
	}

	/**
	 * Setter for part {@link #PHONE_ATTR}.
	 */
	default void setPhone(String newValue) {
		tUpdateByName(PHONE_ATTR, newValue);
	}

	/**
	 * Getter for part {@link #STRUCTURE_ATTR}.
	 */
	default com.top_logic.model.TLClassifier getStructure() {
		return (com.top_logic.model.TLClassifier) tValueByName(STRUCTURE_ATTR);
	}

	/**
	 * Setter for part {@link #STRUCTURE_ATTR}.
	 */
	default void setStructure(com.top_logic.model.TLClassifier newValue) {
		tUpdateByName(STRUCTURE_ATTR, newValue);
	}

}
