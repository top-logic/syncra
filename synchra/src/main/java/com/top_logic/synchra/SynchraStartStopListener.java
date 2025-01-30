package com.top_logic.synchra;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletContext;

import com.top_logic.basic.Logger;
import com.top_logic.basic.col.Provider;
import com.top_logic.element.ElementStartStop;
import com.top_logic.model.TLClass;
import com.top_logic.model.TLObject;
import com.top_logic.synchra.risk.RiskFactory;
import com.top_logic.synchra.risk.interfaces.AmountOfLoss;
import com.top_logic.synchra.risk.interfaces.Color;
import com.top_logic.synchra.risk.interfaces.Probability;
import com.top_logic.synchra.risk.interfaces.RiskClass;
import com.top_logic.synchra.util.AttributedUtil;
import com.top_logic.util.model.ModelService;

/**
 * Initializes some base data for synchra
 */
public class SynchraStartStopListener extends ElementStartStop {

	private static final String RED = "red";

	private static final String YELLOW = "yellow";

	private static final String GREEN = "green";

	/** Overridden to setup some element specific factories */
	@Override
	protected void initSubClassHook(ServletContext aContext) throws Exception {
		super.initSubClassHook(aContext);

		Map<String, Color> colors = setup(Color.class, Color.COLOR_TYPE, this::createColors);
		Map<String, AmountOfLoss> losses =
			setup(AmountOfLoss.class, AmountOfLoss.AMOUNT_OF_LOSS_TYPE, this::createAmountOfLosses);
		Map<String, Probability> probas =
			setup(Probability.class, Probability.PROBABILITY_TYPE, this::createProbabilities);
		initRiskClasses(colors, losses, probas);

	}

	private void initRiskClasses(Map<String, Color> colors, Map<String, AmountOfLoss> losses,
			Map<String, Probability> probas) {
		RiskClassIniter initer = new RiskClassIniter(colors, losses, probas);
		initer.init();
	}

	/**
	 * @param action
	 *        the action to perform for creating new objects
	 */
	private <T extends TLObject> Map<String, T> setup(Class<T> expectedType, String tlType,
			Provider<Map<String, T>> action) {
		List<T> objects = AttributedUtil.getAllInstancesOfType(getTlClass(tlType), expectedType);
		if (objects.isEmpty()) {
			Logger.info("Create initial " + tlType, SynchraStartStopListener.class);
			return action.get();
		}
		return new HashMap<>();
	}

	private TLClass getTlClass(String type) {
		return (TLClass) ModelService.getApplicationModel().getModule(RiskFactory.RISK_STRUCTURE).getType(type);
	}

	private Map<String, Color> createColors() {
		Map<String, Color> res = new HashMap<>();
		res.put(RED, AttributedUtil.createColor("Rot", "Red", 255, 0, 0));
		res.put(YELLOW, AttributedUtil.createColor("Gelb", "Yellow", 255, 255, 0));
		res.put(GREEN, AttributedUtil.createColor("Grün", "Green", 0, 255, 0));
		return res;
	}

	private Map<String, AmountOfLoss> createAmountOfLosses() {
		Map<String, AmountOfLoss> res = new HashMap<>();
		res.put("0", AttributedUtil.createAmountOfLoss("niedring", "low", 0, 5000));
		res.put("1", AttributedUtil.createAmountOfLoss("mittel", "medium", 5000, 10000));
		res.put("2", AttributedUtil.createAmountOfLoss("hoch", "high", 10000, 100000));
		res.put("3", AttributedUtil.createAmountOfLoss("sehr hoch", "very high", 100000, 1000000000));
		return res;
	}


	private Map<String, Probability> createProbabilities() {
		Map<String, Probability> res = new HashMap<>();
		res.put("0", AttributedUtil.createProbability("sehr niedring", "very low", 0, 20));
		res.put("1", AttributedUtil.createProbability("niedring", "low", 20, 40));
		res.put("2", AttributedUtil.createProbability("mittel", "medium", 40, 60));
		res.put("3", AttributedUtil.createProbability("hoch", "high", 60, 80));
		res.put("4", AttributedUtil.createProbability("sehr hoch", "very high", 80, 100));
		return res;
	}

	/**
	 * Initializes the {@link RiskClass}es
	 */
	private static class RiskClassIniter {

		private Map<String, Color> _colors;

		private Map<String, AmountOfLoss> _losses;

		private Map<String, Probability> _probas;

		public RiskClassIniter(Map<String, Color> colors, Map<String, AmountOfLoss> losses,
				Map<String, Probability> probas) {
			_colors = colors;
			_losses = losses;
			_probas = probas;
		}

		public void init() {
			if (_colors.isEmpty() || _losses.isEmpty() || _probas.isEmpty()) {
				return;
			}
			init(3, 0, YELLOW);
			init(2, 0, YELLOW);
			init(1, 0, GREEN);
			init(0, 0, GREEN);

			init(3, 1, YELLOW);
			init(2, 1, YELLOW);
			init(1, 1, YELLOW);
			init(0, 1, GREEN);

			init(3, 2, RED);
			init(2, 2, YELLOW);
			init(1, 2, YELLOW);
			init(0, 2, YELLOW);

			init(3, 3, RED);
			init(2, 3, YELLOW);
			init(1, 3, YELLOW);
			init(0, 3, YELLOW);

			init(3, 4, RED);
			init(2, 4, RED);
			init(1, 4, YELLOW);
			init(0, 4, YELLOW);

		}

		private void init(int loss, int prob, String color) {
			RiskClass riskClass = RiskFactory.getInstance().createRiskClass();
			riskClass.setAmountOfLoss(_losses.get("" + loss));
			riskClass.setProbability(_probas.get("" + prob));
			riskClass.setColor(_colors.get(color));
		}

	}

}
