/*
 * SPDX-FileCopyrightText: 2025 (c) Business Operation Systems GmbH <info@top-logic.com>
 * 
 * SPDX-License-Identifier: AGPL-3.0-only OR LicenseRef-BOS-TopLogic-1.0
 */
package com.top_logic.synchra.risk.implementation;

/**
 * Basic interface for {@link #RISK_TYPE} business objects.
 * 
 * @author Automatically generated by {@link com.top_logic.element.model.generate.InterfaceGenerator}
 */
public interface RiskBase extends com.top_logic.model.TLNamed {

	/**
	 * Name of type <code>Risk</code>
	 */
	String RISK_TYPE = "Risk";

	/**
	 * Part <code>activities</code> of <code>Risk</code>
	 * 
	 * <p>
	 * Declared as <code>syn:Activity</code> in configuration.
	 * </p>
	 */
	String ACTIVITIES_ATTR = "activities";

	/**
	 * Part <code>amountOfLoss</code> of <code>Risk</code>
	 * 
	 * <p>
	 * Declared as <code>risk:AmountOfLoss</code> in configuration.
	 * </p>
	 */
	String AMOUNT_OF_LOSS_ATTR = "amountOfLoss";

	/**
	 * Part <code>color</code> of <code>Risk</code>
	 * 
	 * <p>
	 * Declared as <code>risk:Color</code> in configuration.
	 * </p>
	 */
	String COLOR_ATTR = "color";

	/**
	 * Part <code>colorAfterActivities</code> of <code>Risk</code>
	 * 
	 * <p>
	 * Declared as <code>risk:Color</code> in configuration.
	 * </p>
	 */
	String COLOR_AFTER_ACTIVITIES_ATTR = "colorAfterActivities";

	/**
	 * Part <code>colorSpot</code> of <code>Risk</code>
	 * 
	 * <p>
	 * Declared as <code>tl.util:Color</code> in configuration.
	 * </p>
	 */
	String COLOR_SPOT_ATTR = "colorSpot";

	/**
	 * Part <code>colorSpotAfterActivities</code> of <code>Risk</code>
	 * 
	 * <p>
	 * Declared as <code>tl.util:Color</code> in configuration.
	 * </p>
	 */
	String COLOR_SPOT_AFTER_ACTIVITIES_ATTR = "colorSpotAfterActivities";

	/**
	 * Part <code>components</code> of <code>Risk</code>
	 * 
	 * <p>
	 * Declared as <code>syn:Component</code> in configuration.
	 * </p>
	 */
	String COMPONENTS_ATTR = "components";

	/**
	 * Part <code>createdAt</code> of <code>Risk</code>
	 * 
	 * <p>
	 * Declared as <code>tl.core:DateTime</code> in configuration.
	 * </p>
	 */
	String CREATED_AT_ATTR = "createdAt";

	/**
	 * Part <code>damageAfterActivities</code> of <code>Risk</code>
	 * 
	 * <p>
	 * Declared as <code>tl.core:Integer</code> in configuration.
	 * </p>
	 */
	String DAMAGE_AFTER_ACTIVITIES_ATTR = "damageAfterActivities";

	/**
	 * Part <code>description</code> of <code>Risk</code>
	 * 
	 * <p>
	 * Declared as <code>tl.model.wysiwyg:Html</code> in configuration.
	 * </p>
	 */
	String DESCRIPTION_ATTR = "description";

	/**
	 * Part <code>estimatedDamage</code> of <code>Risk</code>
	 * 
	 * <p>
	 * Declared as <code>tl.core:Integer</code> in configuration.
	 * </p>
	 */
	String ESTIMATED_DAMAGE_ATTR = "estimatedDamage";

	/**
	 * Part <code>estimatedProbability</code> of <code>Risk</code>
	 * 
	 * <p>
	 * Declared as <code>tl.core:Integer</code> in configuration.
	 * </p>
	 */
	String ESTIMATED_PROBABILITY_ATTR = "estimatedProbability";

	/**
	 * Part <code>kinds</code> of <code>Risk</code>
	 * 
	 * <p>
	 * Declared as <code>risk:RiskKind</code> in configuration.
	 * </p>
	 */
	String KINDS_ATTR = "kinds";

	/**
	 * Part <code>lossAfterActivities</code> of <code>Risk</code>
	 * 
	 * <p>
	 * Declared as <code>risk:AmountOfLoss</code> in configuration.
	 * </p>
	 */
	String LOSS_AFTER_ACTIVITIES_ATTR = "lossAfterActivities";

	/**
	 * Part <code>name</code> of <code>Risk</code>
	 * 
	 * <p>
	 * Declared as <code>tl.core:String</code> in configuration.
	 * </p>
	 */
	String NAME_ATTR = "name";

	/**
	 * Part <code>originator</code> of <code>Risk</code>
	 * 
	 * <p>
	 * Declared as <code>tl.accounts:Person</code> in configuration.
	 * </p>
	 */
	String ORIGINATOR_ATTR = "originator";

	/**
	 * Part <code>probAfterActivities</code> of <code>Risk</code>
	 * 
	 * <p>
	 * Declared as <code>risk:Probability</code> in configuration.
	 * </p>
	 */
	String PROB_AFTER_ACTIVITIES_ATTR = "probAfterActivities";

	/**
	 * Part <code>probability</code> of <code>Risk</code>
	 * 
	 * <p>
	 * Declared as <code>risk:Probability</code> in configuration.
	 * </p>
	 */
	String PROBABILITY_ATTR = "probability";

	/**
	 * Part <code>riskClass</code> of <code>Risk</code>
	 * 
	 * <p>
	 * Declared as <code>risk:RiskClass</code> in configuration.
	 * </p>
	 */
	String RISK_CLASS_ATTR = "riskClass";

	/**
	 * Part <code>riskClassAfterActivities</code> of <code>Risk</code>
	 * 
	 * <p>
	 * Declared as <code>risk:RiskClass</code> in configuration.
	 * </p>
	 */
	String RISK_CLASS_AFTER_ACTIVITIES_ATTR = "riskClassAfterActivities";

	/**
	 * Part <code>riskId</code> of <code>Risk</code>
	 * 
	 * <p>
	 * Declared as <code>tl.core:String</code> in configuration.
	 * </p>
	 */
	String RISK_ID_ATTR = "riskId";

	/**
	 * Getter for part {@link #ACTIVITIES_ATTR}.
	 */
	@SuppressWarnings("unchecked")
	default java.util.Set<? extends com.top_logic.synchra.model.interfaces.Activity> getActivities() {
		return (java.util.Set<? extends com.top_logic.synchra.model.interfaces.Activity>) tValueByName(ACTIVITIES_ATTR);
	}

	/**
	 * Live view of the {@link #ACTIVITIES_ATTR} part.
	 * <p>
	 * Changes to this {@link java.util.Collection} change directly the attribute value.
	 * The caller has to take care of the transaction handling.
	 * </p>
	 */
	default java.util.Set<com.top_logic.synchra.model.interfaces.Activity> getActivitiesModifiable() {
		com.top_logic.model.TLStructuredTypePart attribute = tType().getPart(ACTIVITIES_ATTR);
		@SuppressWarnings("unchecked")
		java.util.Set<com.top_logic.synchra.model.interfaces.Activity> result = (java.util.Set<com.top_logic.synchra.model.interfaces.Activity>) com.top_logic.element.meta.kbbased.WrapperMetaAttributeUtil.getLiveCollection(this, attribute);
		return result;
	}

	/**
	 * Setter for part {@link #ACTIVITIES_ATTR}.
	 */
	default void setActivities(java.util.Set<com.top_logic.synchra.model.interfaces.Activity> newValue) {
		tUpdateByName(ACTIVITIES_ATTR, newValue);
	}

	/**
	 * Adds a value to the {@link #ACTIVITIES_ATTR} reference.
	 */
	default void addActivity(com.top_logic.synchra.model.interfaces.Activity newValue) {
		tAddByName(ACTIVITIES_ATTR, newValue);
	}

	/**
	 * Removes the given value from the {@link #ACTIVITIES_ATTR} reference.
	 */
	default void removeActivity(com.top_logic.synchra.model.interfaces.Activity oldValue) {
		tRemoveByName(ACTIVITIES_ATTR, oldValue);
	}

	/**
	 * Getter for part {@link #AMOUNT_OF_LOSS_ATTR}.
	 */
	default com.top_logic.synchra.risk.interfaces.AmountOfLoss getAmountOfLoss() {
		return (com.top_logic.synchra.risk.interfaces.AmountOfLoss) tValueByName(AMOUNT_OF_LOSS_ATTR);
	}

	/**
	 * Getter for part {@link #COLOR_ATTR}.
	 */
	default com.top_logic.synchra.risk.interfaces.Color getColor() {
		return (com.top_logic.synchra.risk.interfaces.Color) tValueByName(COLOR_ATTR);
	}

	/**
	 * Getter for part {@link #COLOR_AFTER_ACTIVITIES_ATTR}.
	 */
	default com.top_logic.synchra.risk.interfaces.Color getColorAfterActivities() {
		return (com.top_logic.synchra.risk.interfaces.Color) tValueByName(COLOR_AFTER_ACTIVITIES_ATTR);
	}

	/**
	 * Getter for part {@link #COLOR_SPOT_ATTR}.
	 */
	default java.awt.Color getColorSpot() {
		return (java.awt.Color) tValueByName(COLOR_SPOT_ATTR);
	}

	/**
	 * Getter for part {@link #COLOR_SPOT_AFTER_ACTIVITIES_ATTR}.
	 */
	default java.awt.Color getColorSpotAfterActivities() {
		return (java.awt.Color) tValueByName(COLOR_SPOT_AFTER_ACTIVITIES_ATTR);
	}

	/**
	 * Getter for part {@link #COMPONENTS_ATTR}.
	 */
	@SuppressWarnings("unchecked")
	default java.util.Set<? extends com.top_logic.synchra.model.interfaces.Component> getComponents() {
		return (java.util.Set<? extends com.top_logic.synchra.model.interfaces.Component>) tValueByName(COMPONENTS_ATTR);
	}

	/**
	 * Live view of the {@link #COMPONENTS_ATTR} part.
	 * <p>
	 * Changes to this {@link java.util.Collection} change directly the attribute value.
	 * The caller has to take care of the transaction handling.
	 * </p>
	 */
	default java.util.Set<com.top_logic.synchra.model.interfaces.Component> getComponentsModifiable() {
		com.top_logic.model.TLStructuredTypePart attribute = tType().getPart(COMPONENTS_ATTR);
		@SuppressWarnings("unchecked")
		java.util.Set<com.top_logic.synchra.model.interfaces.Component> result = (java.util.Set<com.top_logic.synchra.model.interfaces.Component>) com.top_logic.element.meta.kbbased.WrapperMetaAttributeUtil.getLiveCollection(this, attribute);
		return result;
	}

	/**
	 * Setter for part {@link #COMPONENTS_ATTR}.
	 */
	default void setComponents(java.util.Set<com.top_logic.synchra.model.interfaces.Component> newValue) {
		tUpdateByName(COMPONENTS_ATTR, newValue);
	}

	/**
	 * Adds a value to the {@link #COMPONENTS_ATTR} reference.
	 */
	default void addComponent(com.top_logic.synchra.model.interfaces.Component newValue) {
		tAddByName(COMPONENTS_ATTR, newValue);
	}

	/**
	 * Removes the given value from the {@link #COMPONENTS_ATTR} reference.
	 */
	default void removeComponent(com.top_logic.synchra.model.interfaces.Component oldValue) {
		tRemoveByName(COMPONENTS_ATTR, oldValue);
	}

	/**
	 * Getter for part {@link #CREATED_AT_ATTR}.
	 */
	default java.util.Date getCreatedAt() {
		return (java.util.Date) tValueByName(CREATED_AT_ATTR);
	}

	/**
	 * Getter for part {@link #DAMAGE_AFTER_ACTIVITIES_ATTR}.
	 */
	default Integer getDamageAfterActivities() {
		return (Integer) tValueByName(DAMAGE_AFTER_ACTIVITIES_ATTR);
	}

	/**
	 * Getter for part {@link #DESCRIPTION_ATTR}.
	 */
	default com.top_logic.layout.wysiwyg.ui.StructuredText getDescription() {
		return (com.top_logic.layout.wysiwyg.ui.StructuredText) tValueByName(DESCRIPTION_ATTR);
	}

	/**
	 * Setter for part {@link #DESCRIPTION_ATTR}.
	 */
	default void setDescription(com.top_logic.layout.wysiwyg.ui.StructuredText newValue) {
		tUpdateByName(DESCRIPTION_ATTR, newValue);
	}

	/**
	 * Getter for part {@link #ESTIMATED_DAMAGE_ATTR}.
	 */
	default int getEstimatedDamage() {
		return (Integer) tValueByName(ESTIMATED_DAMAGE_ATTR);
	}

	/**
	 * Setter for part {@link #ESTIMATED_DAMAGE_ATTR}.
	 */
	default void setEstimatedDamage(int newValue) {
		tUpdateByName(ESTIMATED_DAMAGE_ATTR, newValue);
	}

	/**
	 * Getter for part {@link #ESTIMATED_PROBABILITY_ATTR}.
	 */
	default int getEstimatedProbability() {
		return (Integer) tValueByName(ESTIMATED_PROBABILITY_ATTR);
	}

	/**
	 * Setter for part {@link #ESTIMATED_PROBABILITY_ATTR}.
	 */
	default void setEstimatedProbability(int newValue) {
		tUpdateByName(ESTIMATED_PROBABILITY_ATTR, newValue);
	}

	/**
	 * Getter for part {@link #KINDS_ATTR}.
	 */
	@SuppressWarnings("unchecked")
	default java.util.Set<? extends com.top_logic.synchra.risk.interfaces.RiskKind> getKinds() {
		return (java.util.Set<? extends com.top_logic.synchra.risk.interfaces.RiskKind>) tValueByName(KINDS_ATTR);
	}

	/**
	 * Live view of the {@link #KINDS_ATTR} part.
	 * <p>
	 * Changes to this {@link java.util.Collection} change directly the attribute value.
	 * The caller has to take care of the transaction handling.
	 * </p>
	 */
	default java.util.Set<com.top_logic.synchra.risk.interfaces.RiskKind> getKindsModifiable() {
		com.top_logic.model.TLStructuredTypePart attribute = tType().getPart(KINDS_ATTR);
		@SuppressWarnings("unchecked")
		java.util.Set<com.top_logic.synchra.risk.interfaces.RiskKind> result = (java.util.Set<com.top_logic.synchra.risk.interfaces.RiskKind>) com.top_logic.element.meta.kbbased.WrapperMetaAttributeUtil.getLiveCollection(this, attribute);
		return result;
	}

	/**
	 * Setter for part {@link #KINDS_ATTR}.
	 */
	default void setKinds(java.util.Set<com.top_logic.synchra.risk.interfaces.RiskKind> newValue) {
		tUpdateByName(KINDS_ATTR, newValue);
	}

	/**
	 * Adds a value to the {@link #KINDS_ATTR} reference.
	 */
	default void addKind(com.top_logic.synchra.risk.interfaces.RiskKind newValue) {
		tAddByName(KINDS_ATTR, newValue);
	}

	/**
	 * Removes the given value from the {@link #KINDS_ATTR} reference.
	 */
	default void removeKind(com.top_logic.synchra.risk.interfaces.RiskKind oldValue) {
		tRemoveByName(KINDS_ATTR, oldValue);
	}

	/**
	 * Getter for part {@link #LOSS_AFTER_ACTIVITIES_ATTR}.
	 */
	default com.top_logic.synchra.risk.interfaces.AmountOfLoss getLossAfterActivities() {
		return (com.top_logic.synchra.risk.interfaces.AmountOfLoss) tValueByName(LOSS_AFTER_ACTIVITIES_ATTR);
	}

	/**
	 * Getter for part {@link #ORIGINATOR_ATTR}.
	 */
	default com.top_logic.knowledge.wrap.person.Person getOriginator() {
		return (com.top_logic.knowledge.wrap.person.Person) tValueByName(ORIGINATOR_ATTR);
	}

	/**
	 * Getter for part {@link #PROB_AFTER_ACTIVITIES_ATTR}.
	 */
	default com.top_logic.synchra.risk.interfaces.Probability getProbAfterActivities() {
		return (com.top_logic.synchra.risk.interfaces.Probability) tValueByName(PROB_AFTER_ACTIVITIES_ATTR);
	}

	/**
	 * Getter for part {@link #PROBABILITY_ATTR}.
	 */
	default com.top_logic.synchra.risk.interfaces.Probability getProbability() {
		return (com.top_logic.synchra.risk.interfaces.Probability) tValueByName(PROBABILITY_ATTR);
	}

	/**
	 * Getter for part {@link #RISK_CLASS_ATTR}.
	 */
	default com.top_logic.synchra.risk.interfaces.RiskClass getRiskClass() {
		return (com.top_logic.synchra.risk.interfaces.RiskClass) tValueByName(RISK_CLASS_ATTR);
	}

	/**
	 * Getter for part {@link #RISK_CLASS_AFTER_ACTIVITIES_ATTR}.
	 */
	default com.top_logic.synchra.risk.interfaces.RiskClass getRiskClassAfterActivities() {
		return (com.top_logic.synchra.risk.interfaces.RiskClass) tValueByName(RISK_CLASS_AFTER_ACTIVITIES_ATTR);
	}

	/**
	 * Getter for part {@link #RISK_ID_ATTR}.
	 */
	default String getRiskId() {
		return (String) tValueByName(RISK_ID_ATTR);
	}

	/**
	 * Setter for part {@link #RISK_ID_ATTR}.
	 */
	default void setRiskId(String newValue) {
		tUpdateByName(RISK_ID_ATTR, newValue);
	}

}
