/*
 * SPDX-FileCopyrightText: 2025 (c) Business Operation Systems GmbH <info@top-logic.com>
 * 
 * SPDX-License-Identifier: AGPL-3.0-only OR LicenseRef-BOS-TopLogic-1.0
 */
package com.top_logic.synchra.risk.implementation;

/**
 * Basic interface for {@link #AMOUNT_OF_LOSS_TYPE} business objects.
 * 
 * @author Automatically generated by {@link com.top_logic.element.model.generate.InterfaceGenerator}
 */
public interface AmountOfLossBase extends com.top_logic.model.TLNamed {

	/**
	 * Name of type <code>AmountOfLoss</code>
	 */
	String AMOUNT_OF_LOSS_TYPE = "AmountOfLoss";

	/**
	 * Part <code>description</code> of <code>AmountOfLoss</code>
	 * 
	 * <p>
	 * Declared as <code>tl.model.i18n:I18NHtml</code> in configuration.
	 * </p>
	 */
	String DESCRIPTION_ATTR = "description";

	/**
	 * Part <code>max</code> of <code>AmountOfLoss</code>
	 * 
	 * <p>
	 * Declared as <code>tl.core:Integer</code> in configuration.
	 * </p>
	 */
	String MAX_ATTR = "max";

	/**
	 * Part <code>min</code> of <code>AmountOfLoss</code>
	 * 
	 * <p>
	 * Declared as <code>tl.core:Integer</code> in configuration.
	 * </p>
	 */
	String MIN_ATTR = "min";

	/**
	 * Part <code>name</code> of <code>AmountOfLoss</code>
	 * 
	 * <p>
	 * Declared as <code>tl.model.i18n:I18NString</code> in configuration.
	 * </p>
	 */
	String NAME_ATTR = "name";

	/**
	 * Getter for part {@link #DESCRIPTION_ATTR}.
	 */
	default com.top_logic.layout.wysiwyg.ui.i18n.I18NStructuredText getDescription() {
		return (com.top_logic.layout.wysiwyg.ui.i18n.I18NStructuredText) tValueByName(DESCRIPTION_ATTR);
	}

	/**
	 * Setter for part {@link #DESCRIPTION_ATTR}.
	 */
	default void setDescription(com.top_logic.layout.wysiwyg.ui.i18n.I18NStructuredText newValue) {
		tUpdateByName(DESCRIPTION_ATTR, newValue);
	}

	/**
	 * Getter for part {@link #MAX_ATTR}.
	 */
	default Integer getMax() {
		return (Integer) tValueByName(MAX_ATTR);
	}

	/**
	 * Setter for part {@link #MAX_ATTR}.
	 */
	default void setMax(Integer newValue) {
		tUpdateByName(MAX_ATTR, newValue);
	}

	/**
	 * Getter for part {@link #MIN_ATTR}.
	 */
	default Integer getMin() {
		return (Integer) tValueByName(MIN_ATTR);
	}

	/**
	 * Setter for part {@link #MIN_ATTR}.
	 */
	default void setMin(Integer newValue) {
		tUpdateByName(MIN_ATTR, newValue);
	}

}
