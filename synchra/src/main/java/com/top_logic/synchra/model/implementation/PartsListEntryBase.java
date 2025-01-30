/*
 * SPDX-FileCopyrightText: 2025 (c) Business Operation Systems GmbH <info@top-logic.com>
 * 
 * SPDX-License-Identifier: AGPL-3.0-only OR LicenseRef-BOS-TopLogic-1.0
 */
package com.top_logic.synchra.model.implementation;

/**
 * Basic interface for {@link #PARTS_LIST_ENTRY_TYPE} business objects.
 * 
 * @author Automatically generated by {@link com.top_logic.element.model.generate.InterfaceGenerator}
 */
public interface PartsListEntryBase extends com.top_logic.model.TLObject {

	/**
	 * Name of type <code>PartsListEntry</code>
	 */
	String PARTS_LIST_ENTRY_TYPE = "PartsListEntry";

	/**
	 * Part <code>connections</code> of <code>PartsListEntry</code>
	 * 
	 * <p>
	 * Declared as <code>fma:Connection</code> in configuration.
	 * </p>
	 */
	String CONNECTIONS_ATTR = "connections";

	/**
	 * Part <code>costs</code> of <code>PartsListEntry</code>
	 * 
	 * <p>
	 * Declared as <code>tl.core:Double</code> in configuration.
	 * </p>
	 */
	String COSTS_ATTR = "costs";

	/**
	 * Part <code>count</code> of <code>PartsListEntry</code>
	 * 
	 * <p>
	 * Declared as <code>tl.core:Integer</code> in configuration.
	 * </p>
	 */
	String COUNT_ATTR = "count";

	/**
	 * Part <code>materials</code> of <code>PartsListEntry</code>
	 * 
	 * <p>
	 * Declared as <code>fma:Material</code> in configuration.
	 * </p>
	 */
	String MATERIALS_ATTR = "materials";

	/**
	 * Part <code>part</code> of <code>PartsListEntry</code>
	 * 
	 * <p>
	 * Declared as <code>fma:SinglePart</code> in configuration.
	 * </p>
	 */
	String PART_ATTR = "part";

	/**
	 * Part <code>producer</code> of <code>PartsListEntry</code>
	 * 
	 * <p>
	 * Declared as <code>fma:Company</code> in configuration.
	 * </p>
	 */
	String PRODUCER_ATTR = "producer";

	/**
	 * Part <code>singlePrice</code> of <code>PartsListEntry</code>
	 * 
	 * <p>
	 * Declared as <code>tl.core:Double</code> in configuration.
	 * </p>
	 */
	String SINGLE_PRICE_ATTR = "singlePrice";

	/**
	 * Getter for part {@link #CONNECTIONS_ATTR}.
	 */
	@SuppressWarnings("unchecked")
	default java.util.Set<? extends com.top_logic.synchra.model.interfaces.Connection> getConnections() {
		return (java.util.Set<? extends com.top_logic.synchra.model.interfaces.Connection>) tValueByName(CONNECTIONS_ATTR);
	}

	/**
	 * Live view of the {@link #CONNECTIONS_ATTR} part.
	 * <p>
	 * Changes to this {@link java.util.Collection} change directly the attribute value.
	 * The caller has to take care of the transaction handling.
	 * </p>
	 */
	default java.util.Set<com.top_logic.synchra.model.interfaces.Connection> getConnectionsModifiable() {
		com.top_logic.model.TLStructuredTypePart attribute = tType().getPart(CONNECTIONS_ATTR);
		@SuppressWarnings("unchecked")
		java.util.Set<com.top_logic.synchra.model.interfaces.Connection> result = (java.util.Set<com.top_logic.synchra.model.interfaces.Connection>) com.top_logic.element.meta.kbbased.WrapperMetaAttributeUtil.getLiveCollection(this, attribute);
		return result;
	}

	/**
	 * Setter for part {@link #CONNECTIONS_ATTR}.
	 */
	default void setConnections(java.util.Set<com.top_logic.synchra.model.interfaces.Connection> newValue) {
		tUpdateByName(CONNECTIONS_ATTR, newValue);
	}

	/**
	 * Adds a value to the {@link #CONNECTIONS_ATTR} reference.
	 */
	default void addConnection(com.top_logic.synchra.model.interfaces.Connection newValue) {
		tAddByName(CONNECTIONS_ATTR, newValue);
	}

	/**
	 * Removes the given value from the {@link #CONNECTIONS_ATTR} reference.
	 */
	default void removeConnection(com.top_logic.synchra.model.interfaces.Connection oldValue) {
		tRemoveByName(CONNECTIONS_ATTR, oldValue);
	}

	/**
	 * Getter for part {@link #COSTS_ATTR}.
	 */
	default Double getCosts() {
		return (Double) tValueByName(COSTS_ATTR);
	}

	/**
	 * Getter for part {@link #COUNT_ATTR}.
	 */
	default Integer getCount() {
		return (Integer) tValueByName(COUNT_ATTR);
	}

	/**
	 * Getter for part {@link #MATERIALS_ATTR}.
	 */
	@SuppressWarnings("unchecked")
	default java.util.Set<? extends com.top_logic.synchra.model.interfaces.Material> getMaterials() {
		return (java.util.Set<? extends com.top_logic.synchra.model.interfaces.Material>) tValueByName(MATERIALS_ATTR);
	}

	/**
	 * Getter for part {@link #PART_ATTR}.
	 */
	default com.top_logic.synchra.model.interfaces.SinglePart getPart() {
		return (com.top_logic.synchra.model.interfaces.SinglePart) tValueByName(PART_ATTR);
	}

	/**
	 * Setter for part {@link #PART_ATTR}.
	 */
	default void setPart(com.top_logic.synchra.model.interfaces.SinglePart newValue) {
		tUpdateByName(PART_ATTR, newValue);
	}

	/**
	 * Getter for part {@link #PRODUCER_ATTR}.
	 */
	default com.top_logic.synchra.model.interfaces.Company getProducer() {
		return (com.top_logic.synchra.model.interfaces.Company) tValueByName(PRODUCER_ATTR);
	}

	/**
	 * Getter for part {@link #SINGLE_PRICE_ATTR}.
	 */
	default Double getSinglePrice() {
		return (Double) tValueByName(SINGLE_PRICE_ATTR);
	}

}
