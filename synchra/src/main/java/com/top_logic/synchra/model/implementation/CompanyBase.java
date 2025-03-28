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
	 * Part <code>canDeliver</code> of <code>Company</code>
	 * 
	 * <p>
	 * Declared as <code>syn:Component</code> in configuration.
	 * </p>
	 */
	String CAN_DELIVER_ATTR = "canDeliver";

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
	 * Declared as <code>syn:CompanyStructure</code> in configuration.
	 * </p>
	 */
	String STRUCTURE_ATTR = "structure";

	/**
	 * Getter for part {@link #CAN_DELIVER_ATTR}.
	 */
	@SuppressWarnings("unchecked")
	default java.util.List<? extends com.top_logic.synchra.model.interfaces.Component> getCanDeliver() {
		return (java.util.List<? extends com.top_logic.synchra.model.interfaces.Component>) tValueByName(CAN_DELIVER_ATTR);
	}

	/**
	 * Live view of the {@link #CAN_DELIVER_ATTR} part.
	 * <p>
	 * Changes to this {@link java.util.Collection} change directly the attribute value.
	 * The caller has to take care of the transaction handling.
	 * </p>
	 */
	default java.util.List<com.top_logic.synchra.model.interfaces.Component> getCanDeliverModifiable() {
		com.top_logic.model.TLStructuredTypePart attribute = tType().getPart(CAN_DELIVER_ATTR);
		@SuppressWarnings("unchecked")
		java.util.List<com.top_logic.synchra.model.interfaces.Component> result = (java.util.List<com.top_logic.synchra.model.interfaces.Component>) com.top_logic.element.meta.kbbased.WrapperMetaAttributeUtil.getLiveCollection(this, attribute);
		return result;
	}

	/**
	 * Setter for part {@link #CAN_DELIVER_ATTR}.
	 */
	default void setCanDeliver(java.util.List<com.top_logic.synchra.model.interfaces.Component> newValue) {
		tUpdateByName(CAN_DELIVER_ATTR, newValue);
	}

	/**
	 * Adds a value to the {@link #CAN_DELIVER_ATTR} reference.
	 */
	default void addCanDeliver(com.top_logic.synchra.model.interfaces.Component newValue) {
		tAddByName(CAN_DELIVER_ATTR, newValue);
	}

	/**
	 * Removes the given value from the {@link #CAN_DELIVER_ATTR} reference.
	 */
	default void removeCanDeliver(com.top_logic.synchra.model.interfaces.Component oldValue) {
		tRemoveByName(CAN_DELIVER_ATTR, oldValue);
	}

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
