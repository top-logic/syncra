/*
 * SPDX-FileCopyrightText: 2025 (c) Business Operation Systems GmbH <info@top-logic.com>
 * 
 * SPDX-License-Identifier: AGPL-3.0-only OR LicenseRef-BOS-TopLogic-1.0
 */
package com.top_logic.synchra.risk;

/**
 * Factory for <code>risk</code> objects.
 * 
 * <p>
 * Note: this is generated code. Do not modify. Instead, create a subclass and register this in the module system.
 * </p>
 * 
 * @author Automatically generated by {@link com.top_logic.element.model.generate.FactoryGenerator}
 */
public class RiskFactory extends com.top_logic.element.meta.kbbased.AbstractElementFactory {

	/**
	 * Name of the structure <code>risk</code> defined by {@link RiskFactory}.
	 */
	public static final String RISK_STRUCTURE = "risk";

	/**
	 * Lookup {@link com.top_logic.synchra.risk.interfaces.AmountOfLoss} type.
	 */
	public static com.top_logic.model.TLClass getAmountOfLossType() {
		return (com.top_logic.model.TLClass) com.top_logic.util.model.ModelService.getApplicationModel().getModule(RISK_STRUCTURE).getType(com.top_logic.synchra.risk.interfaces.AmountOfLoss.AMOUNT_OF_LOSS_TYPE);
	}

	/**
	 * Lookup {@link com.top_logic.synchra.risk.interfaces.AmountOfLoss#DESCRIPTION_ATTR} of {@link com.top_logic.synchra.risk.interfaces.AmountOfLoss}.
	 */
	public static com.top_logic.model.TLProperty getDescriptionAmountOfLossAttr() {
		return (com.top_logic.model.TLProperty) getAmountOfLossType().getPart(com.top_logic.synchra.risk.interfaces.AmountOfLoss.DESCRIPTION_ATTR);
	}

	/**
	 * Lookup {@link com.top_logic.synchra.risk.interfaces.AmountOfLoss#MAX_ATTR} of {@link com.top_logic.synchra.risk.interfaces.AmountOfLoss}.
	 */
	public static com.top_logic.model.TLProperty getMaxAmountOfLossAttr() {
		return (com.top_logic.model.TLProperty) getAmountOfLossType().getPart(com.top_logic.synchra.risk.interfaces.AmountOfLoss.MAX_ATTR);
	}

	/**
	 * Lookup {@link com.top_logic.synchra.risk.interfaces.AmountOfLoss#MIN_ATTR} of {@link com.top_logic.synchra.risk.interfaces.AmountOfLoss}.
	 */
	public static com.top_logic.model.TLProperty getMinAmountOfLossAttr() {
		return (com.top_logic.model.TLProperty) getAmountOfLossType().getPart(com.top_logic.synchra.risk.interfaces.AmountOfLoss.MIN_ATTR);
	}

	/**
	 * Lookup {@link com.top_logic.synchra.risk.interfaces.AmountOfLoss#NAME_ATTR} of {@link com.top_logic.synchra.risk.interfaces.AmountOfLoss}.
	 */
	public static com.top_logic.model.TLProperty getNameAmountOfLossAttr() {
		return (com.top_logic.model.TLProperty) getAmountOfLossType().getPart(com.top_logic.synchra.risk.interfaces.AmountOfLoss.NAME_ATTR);
	}

	/**
	 * Lookup {@link com.top_logic.synchra.risk.interfaces.Color} type.
	 */
	public static com.top_logic.model.TLClass getColorType() {
		return (com.top_logic.model.TLClass) com.top_logic.util.model.ModelService.getApplicationModel().getModule(RISK_STRUCTURE).getType(com.top_logic.synchra.risk.interfaces.Color.COLOR_TYPE);
	}

	/**
	 * Lookup {@link com.top_logic.synchra.risk.interfaces.Color#COLOR_ATTR} of {@link com.top_logic.synchra.risk.interfaces.Color}.
	 */
	public static com.top_logic.model.TLProperty getColorColorAttr() {
		return (com.top_logic.model.TLProperty) getColorType().getPart(com.top_logic.synchra.risk.interfaces.Color.COLOR_ATTR);
	}

	/**
	 * Lookup {@link com.top_logic.synchra.risk.interfaces.Color#NAME_ATTR} of {@link com.top_logic.synchra.risk.interfaces.Color}.
	 */
	public static com.top_logic.model.TLProperty getNameColorAttr() {
		return (com.top_logic.model.TLProperty) getColorType().getPart(com.top_logic.synchra.risk.interfaces.Color.NAME_ATTR);
	}

	/**
	 * Lookup {@link com.top_logic.synchra.risk.interfaces.Color#RGB_ATTR} of {@link com.top_logic.synchra.risk.interfaces.Color}.
	 */
	public static com.top_logic.model.TLProperty getRgbColorAttr() {
		return (com.top_logic.model.TLProperty) getColorType().getPart(com.top_logic.synchra.risk.interfaces.Color.RGB_ATTR);
	}

	/**
	 * Lookup {@link com.top_logic.synchra.risk.interfaces.Probability} type.
	 */
	public static com.top_logic.model.TLClass getProbabilityType() {
		return (com.top_logic.model.TLClass) com.top_logic.util.model.ModelService.getApplicationModel().getModule(RISK_STRUCTURE).getType(com.top_logic.synchra.risk.interfaces.Probability.PROBABILITY_TYPE);
	}

	/**
	 * Lookup {@link com.top_logic.synchra.risk.interfaces.Probability#DESCRIPTION_ATTR} of {@link com.top_logic.synchra.risk.interfaces.Probability}.
	 */
	public static com.top_logic.model.TLProperty getDescriptionProbabilityAttr() {
		return (com.top_logic.model.TLProperty) getProbabilityType().getPart(com.top_logic.synchra.risk.interfaces.Probability.DESCRIPTION_ATTR);
	}

	/**
	 * Lookup {@link com.top_logic.synchra.risk.interfaces.Probability#MAX_ATTR} of {@link com.top_logic.synchra.risk.interfaces.Probability}.
	 */
	public static com.top_logic.model.TLProperty getMaxProbabilityAttr() {
		return (com.top_logic.model.TLProperty) getProbabilityType().getPart(com.top_logic.synchra.risk.interfaces.Probability.MAX_ATTR);
	}

	/**
	 * Lookup {@link com.top_logic.synchra.risk.interfaces.Probability#MIN_ATTR} of {@link com.top_logic.synchra.risk.interfaces.Probability}.
	 */
	public static com.top_logic.model.TLProperty getMinProbabilityAttr() {
		return (com.top_logic.model.TLProperty) getProbabilityType().getPart(com.top_logic.synchra.risk.interfaces.Probability.MIN_ATTR);
	}

	/**
	 * Lookup {@link com.top_logic.synchra.risk.interfaces.Probability#NAME_ATTR} of {@link com.top_logic.synchra.risk.interfaces.Probability}.
	 */
	public static com.top_logic.model.TLProperty getNameProbabilityAttr() {
		return (com.top_logic.model.TLProperty) getProbabilityType().getPart(com.top_logic.synchra.risk.interfaces.Probability.NAME_ATTR);
	}

	/**
	 * Lookup {@link com.top_logic.synchra.risk.interfaces.Risk} type.
	 */
	public static com.top_logic.model.TLClass getRiskType() {
		return (com.top_logic.model.TLClass) com.top_logic.util.model.ModelService.getApplicationModel().getModule(RISK_STRUCTURE).getType(com.top_logic.synchra.risk.interfaces.Risk.RISK_TYPE);
	}

	/**
	 * Lookup {@link com.top_logic.synchra.risk.interfaces.Risk#ACTIVITIES_ATTR} of {@link com.top_logic.synchra.risk.interfaces.Risk}.
	 */
	public static com.top_logic.model.TLReference getActivitiesRiskAttr() {
		return (com.top_logic.model.TLReference) getRiskType().getPart(com.top_logic.synchra.risk.interfaces.Risk.ACTIVITIES_ATTR);
	}

	/**
	 * Lookup {@link com.top_logic.synchra.risk.interfaces.Risk#AMOUNT_OF_LOSS_ATTR} of {@link com.top_logic.synchra.risk.interfaces.Risk}.
	 */
	public static com.top_logic.model.TLReference getAmountOfLossRiskAttr() {
		return (com.top_logic.model.TLReference) getRiskType().getPart(com.top_logic.synchra.risk.interfaces.Risk.AMOUNT_OF_LOSS_ATTR);
	}

	/**
	 * Lookup {@link com.top_logic.synchra.risk.interfaces.Risk#COLOR_ATTR} of {@link com.top_logic.synchra.risk.interfaces.Risk}.
	 */
	public static com.top_logic.model.TLReference getColorRiskAttr() {
		return (com.top_logic.model.TLReference) getRiskType().getPart(com.top_logic.synchra.risk.interfaces.Risk.COLOR_ATTR);
	}

	/**
	 * Lookup {@link com.top_logic.synchra.risk.interfaces.Risk#COLOR_AFTER_ACTIVITIES_ATTR} of {@link com.top_logic.synchra.risk.interfaces.Risk}.
	 */
	public static com.top_logic.model.TLReference getColorAfterActivitiesRiskAttr() {
		return (com.top_logic.model.TLReference) getRiskType().getPart(com.top_logic.synchra.risk.interfaces.Risk.COLOR_AFTER_ACTIVITIES_ATTR);
	}

	/**
	 * Lookup {@link com.top_logic.synchra.risk.interfaces.Risk#COLOR_SPOT_ATTR} of {@link com.top_logic.synchra.risk.interfaces.Risk}.
	 */
	public static com.top_logic.model.TLProperty getColorSpotRiskAttr() {
		return (com.top_logic.model.TLProperty) getRiskType().getPart(com.top_logic.synchra.risk.interfaces.Risk.COLOR_SPOT_ATTR);
	}

	/**
	 * Lookup {@link com.top_logic.synchra.risk.interfaces.Risk#COLOR_SPOT_AFTER_ACTIVITIES_ATTR} of {@link com.top_logic.synchra.risk.interfaces.Risk}.
	 */
	public static com.top_logic.model.TLProperty getColorSpotAfterActivitiesRiskAttr() {
		return (com.top_logic.model.TLProperty) getRiskType().getPart(com.top_logic.synchra.risk.interfaces.Risk.COLOR_SPOT_AFTER_ACTIVITIES_ATTR);
	}

	/**
	 * Lookup {@link com.top_logic.synchra.risk.interfaces.Risk#COMPONENTS_ATTR} of {@link com.top_logic.synchra.risk.interfaces.Risk}.
	 */
	public static com.top_logic.model.TLReference getComponentsRiskAttr() {
		return (com.top_logic.model.TLReference) getRiskType().getPart(com.top_logic.synchra.risk.interfaces.Risk.COMPONENTS_ATTR);
	}

	/**
	 * Lookup {@link com.top_logic.synchra.risk.interfaces.Risk#CREATED_AT_ATTR} of {@link com.top_logic.synchra.risk.interfaces.Risk}.
	 */
	public static com.top_logic.model.TLProperty getCreatedAtRiskAttr() {
		return (com.top_logic.model.TLProperty) getRiskType().getPart(com.top_logic.synchra.risk.interfaces.Risk.CREATED_AT_ATTR);
	}

	/**
	 * Lookup {@link com.top_logic.synchra.risk.interfaces.Risk#DAMAGE_AFTER_ACTIVITIES_ATTR} of {@link com.top_logic.synchra.risk.interfaces.Risk}.
	 */
	public static com.top_logic.model.TLProperty getDamageAfterActivitiesRiskAttr() {
		return (com.top_logic.model.TLProperty) getRiskType().getPart(com.top_logic.synchra.risk.interfaces.Risk.DAMAGE_AFTER_ACTIVITIES_ATTR);
	}

	/**
	 * Lookup {@link com.top_logic.synchra.risk.interfaces.Risk#DESCRIPTION_ATTR} of {@link com.top_logic.synchra.risk.interfaces.Risk}.
	 */
	public static com.top_logic.model.TLProperty getDescriptionRiskAttr() {
		return (com.top_logic.model.TLProperty) getRiskType().getPart(com.top_logic.synchra.risk.interfaces.Risk.DESCRIPTION_ATTR);
	}

	/**
	 * Lookup {@link com.top_logic.synchra.risk.interfaces.Risk#ESTIMATED_DAMAGE_ATTR} of {@link com.top_logic.synchra.risk.interfaces.Risk}.
	 */
	public static com.top_logic.model.TLProperty getEstimatedDamageRiskAttr() {
		return (com.top_logic.model.TLProperty) getRiskType().getPart(com.top_logic.synchra.risk.interfaces.Risk.ESTIMATED_DAMAGE_ATTR);
	}

	/**
	 * Lookup {@link com.top_logic.synchra.risk.interfaces.Risk#KINDS_ATTR} of {@link com.top_logic.synchra.risk.interfaces.Risk}.
	 */
	public static com.top_logic.model.TLReference getKindsRiskAttr() {
		return (com.top_logic.model.TLReference) getRiskType().getPart(com.top_logic.synchra.risk.interfaces.Risk.KINDS_ATTR);
	}

	/**
	 * Lookup {@link com.top_logic.synchra.risk.interfaces.Risk#LOSS_AFTER_ACTIVITIES_ATTR} of {@link com.top_logic.synchra.risk.interfaces.Risk}.
	 */
	public static com.top_logic.model.TLReference getLossAfterActivitiesRiskAttr() {
		return (com.top_logic.model.TLReference) getRiskType().getPart(com.top_logic.synchra.risk.interfaces.Risk.LOSS_AFTER_ACTIVITIES_ATTR);
	}

	/**
	 * Lookup {@link com.top_logic.synchra.risk.interfaces.Risk#NAME_ATTR} of {@link com.top_logic.synchra.risk.interfaces.Risk}.
	 */
	public static com.top_logic.model.TLProperty getNameRiskAttr() {
		return (com.top_logic.model.TLProperty) getRiskType().getPart(com.top_logic.synchra.risk.interfaces.Risk.NAME_ATTR);
	}

	/**
	 * Lookup {@link com.top_logic.synchra.risk.interfaces.Risk#ORIGINATOR_ATTR} of {@link com.top_logic.synchra.risk.interfaces.Risk}.
	 */
	public static com.top_logic.model.TLReference getOriginatorRiskAttr() {
		return (com.top_logic.model.TLReference) getRiskType().getPart(com.top_logic.synchra.risk.interfaces.Risk.ORIGINATOR_ATTR);
	}

	/**
	 * Lookup {@link com.top_logic.synchra.risk.interfaces.Risk#PROB_AFTER_ACTIVITIES_ATTR} of {@link com.top_logic.synchra.risk.interfaces.Risk}.
	 */
	public static com.top_logic.model.TLReference getProbAfterActivitiesRiskAttr() {
		return (com.top_logic.model.TLReference) getRiskType().getPart(com.top_logic.synchra.risk.interfaces.Risk.PROB_AFTER_ACTIVITIES_ATTR);
	}

	/**
	 * Lookup {@link com.top_logic.synchra.risk.interfaces.Risk#PROBABILITY_ATTR} of {@link com.top_logic.synchra.risk.interfaces.Risk}.
	 */
	public static com.top_logic.model.TLReference getProbabilityRiskAttr() {
		return (com.top_logic.model.TLReference) getRiskType().getPart(com.top_logic.synchra.risk.interfaces.Risk.PROBABILITY_ATTR);
	}

	/**
	 * Lookup {@link com.top_logic.synchra.risk.interfaces.Risk#RISK_CLASS_ATTR} of {@link com.top_logic.synchra.risk.interfaces.Risk}.
	 */
	public static com.top_logic.model.TLReference getRiskClassRiskAttr() {
		return (com.top_logic.model.TLReference) getRiskType().getPart(com.top_logic.synchra.risk.interfaces.Risk.RISK_CLASS_ATTR);
	}

	/**
	 * Lookup {@link com.top_logic.synchra.risk.interfaces.Risk#RISK_CLASS_AFTER_ACTIVITIES_ATTR} of {@link com.top_logic.synchra.risk.interfaces.Risk}.
	 */
	public static com.top_logic.model.TLReference getRiskClassAfterActivitiesRiskAttr() {
		return (com.top_logic.model.TLReference) getRiskType().getPart(com.top_logic.synchra.risk.interfaces.Risk.RISK_CLASS_AFTER_ACTIVITIES_ATTR);
	}

	/**
	 * Lookup {@link com.top_logic.synchra.risk.interfaces.RiskClass} type.
	 */
	public static com.top_logic.model.TLClass getRiskClassType() {
		return (com.top_logic.model.TLClass) com.top_logic.util.model.ModelService.getApplicationModel().getModule(RISK_STRUCTURE).getType(com.top_logic.synchra.risk.interfaces.RiskClass.RISK_CLASS_TYPE);
	}

	/**
	 * Lookup {@link com.top_logic.synchra.risk.interfaces.RiskClass#AMOUNT_OF_LOSS_ATTR} of {@link com.top_logic.synchra.risk.interfaces.RiskClass}.
	 */
	public static com.top_logic.model.TLReference getAmountOfLossRiskClassAttr() {
		return (com.top_logic.model.TLReference) getRiskClassType().getPart(com.top_logic.synchra.risk.interfaces.RiskClass.AMOUNT_OF_LOSS_ATTR);
	}

	/**
	 * Lookup {@link com.top_logic.synchra.risk.interfaces.RiskClass#COLOR_ATTR} of {@link com.top_logic.synchra.risk.interfaces.RiskClass}.
	 */
	public static com.top_logic.model.TLReference getColorRiskClassAttr() {
		return (com.top_logic.model.TLReference) getRiskClassType().getPart(com.top_logic.synchra.risk.interfaces.RiskClass.COLOR_ATTR);
	}

	/**
	 * Lookup {@link com.top_logic.synchra.risk.interfaces.RiskClass#PROBABILITY_ATTR} of {@link com.top_logic.synchra.risk.interfaces.RiskClass}.
	 */
	public static com.top_logic.model.TLReference getProbabilityRiskClassAttr() {
		return (com.top_logic.model.TLReference) getRiskClassType().getPart(com.top_logic.synchra.risk.interfaces.RiskClass.PROBABILITY_ATTR);
	}

	/**
	 * Lookup {@link com.top_logic.synchra.risk.interfaces.RiskKind} type.
	 */
	public static com.top_logic.model.TLClass getRiskKindType() {
		return (com.top_logic.model.TLClass) com.top_logic.util.model.ModelService.getApplicationModel().getModule(RISK_STRUCTURE).getType(com.top_logic.synchra.risk.interfaces.RiskKind.RISK_KIND_TYPE);
	}

	/**
	 * Lookup {@link com.top_logic.synchra.risk.interfaces.RiskKind#DESCRIPTION_ATTR} of {@link com.top_logic.synchra.risk.interfaces.RiskKind}.
	 */
	public static com.top_logic.model.TLProperty getDescriptionRiskKindAttr() {
		return (com.top_logic.model.TLProperty) getRiskKindType().getPart(com.top_logic.synchra.risk.interfaces.RiskKind.DESCRIPTION_ATTR);
	}

	/**
	 * Lookup {@link com.top_logic.synchra.risk.interfaces.RiskKind#NAME_ATTR} of {@link com.top_logic.synchra.risk.interfaces.RiskKind}.
	 */
	public static com.top_logic.model.TLProperty getNameRiskKindAttr() {
		return (com.top_logic.model.TLProperty) getRiskKindType().getPart(com.top_logic.synchra.risk.interfaces.RiskKind.NAME_ATTR);
	}

	/**
	 * Name of type <code>AmountOfLoss</code> in structure {@link #RISK_STRUCTURE}.
	 * 
	 * @deprecated Use {@link com.top_logic.synchra.risk.interfaces.AmountOfLoss#AMOUNT_OF_LOSS_TYPE}.
	 */
	@Deprecated
	public static final String AMOUNT_OF_LOSS_NODE = com.top_logic.synchra.risk.interfaces.AmountOfLoss.AMOUNT_OF_LOSS_TYPE;

	/**
	 * Storage table name of {@link #AMOUNT_OF_LOSS_NODE} objects.
	 */
	public static final String KO_NAME_AMOUNT_OF_LOSS = "GenericObject";

	/**
	 * Name of type <code>Color</code> in structure {@link #RISK_STRUCTURE}.
	 * 
	 * @deprecated Use {@link com.top_logic.synchra.risk.interfaces.Color#COLOR_TYPE}.
	 */
	@Deprecated
	public static final String COLOR_NODE = com.top_logic.synchra.risk.interfaces.Color.COLOR_TYPE;

	/**
	 * Storage table name of {@link #COLOR_NODE} objects.
	 */
	public static final String KO_NAME_COLOR = "GenericObject";

	/**
	 * Name of type <code>Probability</code> in structure {@link #RISK_STRUCTURE}.
	 * 
	 * @deprecated Use {@link com.top_logic.synchra.risk.interfaces.Probability#PROBABILITY_TYPE}.
	 */
	@Deprecated
	public static final String PROBABILITY_NODE = com.top_logic.synchra.risk.interfaces.Probability.PROBABILITY_TYPE;

	/**
	 * Storage table name of {@link #PROBABILITY_NODE} objects.
	 */
	public static final String KO_NAME_PROBABILITY = "GenericObject";

	/**
	 * Name of type <code>Risk</code> in structure {@link #RISK_STRUCTURE}.
	 * 
	 * @deprecated Use {@link com.top_logic.synchra.risk.interfaces.Risk#RISK_TYPE}.
	 */
	@Deprecated
	public static final String RISK_NODE = com.top_logic.synchra.risk.interfaces.Risk.RISK_TYPE;

	/**
	 * Storage table name of {@link #RISK_NODE} objects.
	 */
	public static final String KO_NAME_RISK = "GenericObject";

	/**
	 * Name of type <code>RiskClass</code> in structure {@link #RISK_STRUCTURE}.
	 * 
	 * @deprecated Use {@link com.top_logic.synchra.risk.interfaces.RiskClass#RISK_CLASS_TYPE}.
	 */
	@Deprecated
	public static final String RISK_CLASS_NODE = com.top_logic.synchra.risk.interfaces.RiskClass.RISK_CLASS_TYPE;

	/**
	 * Storage table name of {@link #RISK_CLASS_NODE} objects.
	 */
	public static final String KO_NAME_RISK_CLASS = "GenericObject";

	/**
	 * Name of type <code>RiskKind</code> in structure {@link #RISK_STRUCTURE}.
	 * 
	 * @deprecated Use {@link com.top_logic.synchra.risk.interfaces.RiskKind#RISK_KIND_TYPE}.
	 */
	@Deprecated
	public static final String RISK_KIND_NODE = com.top_logic.synchra.risk.interfaces.RiskKind.RISK_KIND_TYPE;

	/**
	 * Storage table name of {@link #RISK_KIND_NODE} objects.
	 */
	public static final String KO_NAME_RISK_KIND = "GenericObject";


	/**
	 * Create an instance of {@link com.top_logic.synchra.risk.interfaces.AmountOfLoss} type.
	 */
	public final com.top_logic.synchra.risk.interfaces.AmountOfLoss createAmountOfLoss(com.top_logic.model.TLObject context) {
		return (com.top_logic.synchra.risk.interfaces.AmountOfLoss) createObject(getAmountOfLossType(), context);
	}

	/**
	 * Create an instance of {@link com.top_logic.synchra.risk.interfaces.AmountOfLoss} type.
	 */
	public final com.top_logic.synchra.risk.interfaces.AmountOfLoss createAmountOfLoss() {
		return createAmountOfLoss(null);
	}

	/**
	 * Create an instance of {@link com.top_logic.synchra.risk.interfaces.Color} type.
	 */
	public final com.top_logic.synchra.risk.interfaces.Color createColor(com.top_logic.model.TLObject context) {
		return (com.top_logic.synchra.risk.interfaces.Color) createObject(getColorType(), context);
	}

	/**
	 * Create an instance of {@link com.top_logic.synchra.risk.interfaces.Color} type.
	 */
	public final com.top_logic.synchra.risk.interfaces.Color createColor() {
		return createColor(null);
	}

	/**
	 * Create an instance of {@link com.top_logic.synchra.risk.interfaces.Probability} type.
	 */
	public final com.top_logic.synchra.risk.interfaces.Probability createProbability(com.top_logic.model.TLObject context) {
		return (com.top_logic.synchra.risk.interfaces.Probability) createObject(getProbabilityType(), context);
	}

	/**
	 * Create an instance of {@link com.top_logic.synchra.risk.interfaces.Probability} type.
	 */
	public final com.top_logic.synchra.risk.interfaces.Probability createProbability() {
		return createProbability(null);
	}

	/**
	 * Create an instance of {@link com.top_logic.synchra.risk.interfaces.Risk} type.
	 */
	public final com.top_logic.synchra.risk.interfaces.Risk createRisk(com.top_logic.model.TLObject context) {
		return (com.top_logic.synchra.risk.interfaces.Risk) createObject(getRiskType(), context);
	}

	/**
	 * Create an instance of {@link com.top_logic.synchra.risk.interfaces.Risk} type.
	 */
	public final com.top_logic.synchra.risk.interfaces.Risk createRisk() {
		return createRisk(null);
	}

	/**
	 * Create an instance of {@link com.top_logic.synchra.risk.interfaces.RiskClass} type.
	 */
	public final com.top_logic.synchra.risk.interfaces.RiskClass createRiskClass(com.top_logic.model.TLObject context) {
		return (com.top_logic.synchra.risk.interfaces.RiskClass) createObject(getRiskClassType(), context);
	}

	/**
	 * Create an instance of {@link com.top_logic.synchra.risk.interfaces.RiskClass} type.
	 */
	public final com.top_logic.synchra.risk.interfaces.RiskClass createRiskClass() {
		return createRiskClass(null);
	}

	/**
	 * Create an instance of {@link com.top_logic.synchra.risk.interfaces.RiskKind} type.
	 */
	public final com.top_logic.synchra.risk.interfaces.RiskKind createRiskKind(com.top_logic.model.TLObject context) {
		return (com.top_logic.synchra.risk.interfaces.RiskKind) createObject(getRiskKindType(), context);
	}

	/**
	 * Create an instance of {@link com.top_logic.synchra.risk.interfaces.RiskKind} type.
	 */
	public final com.top_logic.synchra.risk.interfaces.RiskKind createRiskKind() {
		return createRiskKind(null);
	}

	/**
	 * The singleton instance of {@link RiskFactory}.
	 */
	public static RiskFactory getInstance() {
		return (RiskFactory) com.top_logic.element.model.DynamicModelService.getFactoryFor(RISK_STRUCTURE);
	}
}
