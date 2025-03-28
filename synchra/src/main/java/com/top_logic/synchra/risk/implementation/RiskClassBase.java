/*
 * SPDX-FileCopyrightText: 2025 (c) Business Operation Systems GmbH <info@top-logic.com>
 * 
 * SPDX-License-Identifier: AGPL-3.0-only OR LicenseRef-BOS-TopLogic-1.0
 */
package com.top_logic.synchra.risk.implementation;

/**
 * Basic interface for {@link #RISK_CLASS_TYPE} business objects.
 * 
 * @author Automatically generated by {@link com.top_logic.element.model.generate.InterfaceGenerator}
 */
public interface RiskClassBase extends com.top_logic.model.TLObject {

	/**
	 * Name of type <code>RiskClass</code>
	 */
	String RISK_CLASS_TYPE = "RiskClass";

	/**
	 * Part <code>amountOfLoss</code> of <code>RiskClass</code>
	 * 
	 * <p>
	 * Declared as <code>risk:AmountOfLoss</code> in configuration.
	 * </p>
	 */
	String AMOUNT_OF_LOSS_ATTR = "amountOfLoss";

	/**
	 * Part <code>color</code> of <code>RiskClass</code>
	 * 
	 * <p>
	 * Declared as <code>risk:Color</code> in configuration.
	 * </p>
	 */
	String COLOR_ATTR = "color";

	/**
	 * Part <code>probability</code> of <code>RiskClass</code>
	 * 
	 * <p>
	 * Declared as <code>risk:Probability</code> in configuration.
	 * </p>
	 */
	String PROBABILITY_ATTR = "probability";

	/**
	 * Getter for part {@link #AMOUNT_OF_LOSS_ATTR}.
	 */
	default com.top_logic.synchra.risk.interfaces.AmountOfLoss getAmountOfLoss() {
		return (com.top_logic.synchra.risk.interfaces.AmountOfLoss) tValueByName(AMOUNT_OF_LOSS_ATTR);
	}

	/**
	 * Setter for part {@link #AMOUNT_OF_LOSS_ATTR}.
	 */
	default void setAmountOfLoss(com.top_logic.synchra.risk.interfaces.AmountOfLoss newValue) {
		tUpdateByName(AMOUNT_OF_LOSS_ATTR, newValue);
	}

	/**
	 * Getter for part {@link #COLOR_ATTR}.
	 */
	default com.top_logic.synchra.risk.interfaces.Color getColor() {
		return (com.top_logic.synchra.risk.interfaces.Color) tValueByName(COLOR_ATTR);
	}

	/**
	 * Setter for part {@link #COLOR_ATTR}.
	 */
	default void setColor(com.top_logic.synchra.risk.interfaces.Color newValue) {
		tUpdateByName(COLOR_ATTR, newValue);
	}

	/**
	 * Getter for part {@link #PROBABILITY_ATTR}.
	 */
	default com.top_logic.synchra.risk.interfaces.Probability getProbability() {
		return (com.top_logic.synchra.risk.interfaces.Probability) tValueByName(PROBABILITY_ATTR);
	}

	/**
	 * Setter for part {@link #PROBABILITY_ATTR}.
	 */
	default void setProbability(com.top_logic.synchra.risk.interfaces.Probability newValue) {
		tUpdateByName(PROBABILITY_ATTR, newValue);
	}

}
