/*
 * SPDX-FileCopyrightText: 2025 (c) Business Operation Systems GmbH <info@top-logic.com>
 * 
 * SPDX-License-Identifier: AGPL-3.0-only OR LicenseRef-BOS-TopLogic-1.0
 */
package com.top_logic.synchra.model.implementation;

/**
 * Basic interface for {@link #ACTIVITY_TYPE} business objects.
 * 
 * @author Automatically generated by {@link com.top_logic.element.model.generate.InterfaceGenerator}
 */
public interface ActivityBase extends com.top_logic.model.TLNamed {

	/**
	 * Name of type <code>Activity</code>
	 */
	String ACTIVITY_TYPE = "Activity";

	/**
	 * Part <code>activityId</code> of <code>Activity</code>
	 * 
	 * <p>
	 * Declared as <code>tl.core:String</code> in configuration.
	 * </p>
	 */
	String ACTIVITY_ID_ATTR = "activityId";

	/**
	 * Part <code>costs</code> of <code>Activity</code>
	 * 
	 * <p>
	 * Declared as <code>tl.core:Integer</code> in configuration.
	 * </p>
	 */
	String COSTS_ATTR = "costs";

	/**
	 * Part <code>description</code> of <code>Activity</code>
	 * 
	 * <p>
	 * Declared as <code>tl.model.wysiwyg:Html</code> in configuration.
	 * </p>
	 */
	String DESCRIPTION_ATTR = "description";

	/**
	 * Part <code>kind</code> of <code>Activity</code>
	 * 
	 * <p>
	 * Declared as <code>syn:ActivityKind</code> in configuration.
	 * </p>
	 */
	String KIND_ATTR = "kind";

	/**
	 * Part <code>name</code> of <code>Activity</code>
	 * 
	 * <p>
	 * Declared as <code>tl.core:String</code> in configuration.
	 * </p>
	 */
	String NAME_ATTR = "name";

	/**
	 * Part <code>riskCostImpact</code> of <code>Activity</code>
	 * 
	 * <p>
	 * Declared as <code>tl.core:Integer</code> in configuration.
	 * </p>
	 */
	String RISK_COST_IMPACT_ATTR = "riskCostImpact";

	/**
	 * Part <code>riskProbImpact</code> of <code>Activity</code>
	 * 
	 * <p>
	 * Declared as <code>tl.core:Integer</code> in configuration.
	 * </p>
	 */
	String RISK_PROB_IMPACT_ATTR = "riskProbImpact";

	/**
	 * Getter for part {@link #ACTIVITY_ID_ATTR}.
	 */
	default String getActivityId() {
		return (String) tValueByName(ACTIVITY_ID_ATTR);
	}

	/**
	 * Setter for part {@link #ACTIVITY_ID_ATTR}.
	 */
	default void setActivityId(String newValue) {
		tUpdateByName(ACTIVITY_ID_ATTR, newValue);
	}

	/**
	 * Getter for part {@link #COSTS_ATTR}.
	 */
	default Integer getCosts() {
		return (Integer) tValueByName(COSTS_ATTR);
	}

	/**
	 * Setter for part {@link #COSTS_ATTR}.
	 */
	default void setCosts(Integer newValue) {
		tUpdateByName(COSTS_ATTR, newValue);
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
	 * Getter for part {@link #KIND_ATTR}.
	 */
	default com.top_logic.model.TLClassifier getKind() {
		return (com.top_logic.model.TLClassifier) tValueByName(KIND_ATTR);
	}

	/**
	 * Setter for part {@link #KIND_ATTR}.
	 */
	default void setKind(com.top_logic.model.TLClassifier newValue) {
		tUpdateByName(KIND_ATTR, newValue);
	}

	/**
	 * Getter for part {@link #RISK_COST_IMPACT_ATTR}.
	 */
	default Integer getRiskCostImpact() {
		return (Integer) tValueByName(RISK_COST_IMPACT_ATTR);
	}

	/**
	 * Setter for part {@link #RISK_COST_IMPACT_ATTR}.
	 */
	default void setRiskCostImpact(Integer newValue) {
		tUpdateByName(RISK_COST_IMPACT_ATTR, newValue);
	}

	/**
	 * Getter for part {@link #RISK_PROB_IMPACT_ATTR}.
	 */
	default Integer getRiskProbImpact() {
		return (Integer) tValueByName(RISK_PROB_IMPACT_ATTR);
	}

	/**
	 * Setter for part {@link #RISK_PROB_IMPACT_ATTR}.
	 */
	default void setRiskProbImpact(Integer newValue) {
		tUpdateByName(RISK_PROB_IMPACT_ATTR, newValue);
	}

}
