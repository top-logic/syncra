package com.top_logic.synchra.util;

import java.util.List;
import java.util.Locale;

import com.top_logic.basic.util.ResKey;
import com.top_logic.element.meta.MetaElementUtil;
import com.top_logic.model.TLClass;
import com.top_logic.model.TLNamed;
import com.top_logic.model.TLObject;
import com.top_logic.synchra.risk.RiskFactory;
import com.top_logic.synchra.risk.interfaces.AmountOfLoss;
import com.top_logic.synchra.risk.interfaces.Color;
import com.top_logic.synchra.risk.interfaces.Probability;

/**
 * gets type save all instances of types
 */
public class AttributedUtil {

	/**
	 * @return all instances of given type.
	 */
	public static <T extends TLObject> List<T> getAllInstancesOfType(TLClass tlClass, Class<T> type) {
		return MetaElementUtil.getAllInstancesOf(tlClass, type);
	}

	/**
	 * @return all known {@link Color}s.
	 */
	public static List<Color> getAllColors() {
		return getAllInstancesOfType(RiskFactory.getColorType(), Color.class);
	}

	/**
	 * @return all known {@link AmountOfLoss}s.
	 */
	public static List<AmountOfLoss> getAllAmountOfLoss() {
		return getAllInstancesOfType(RiskFactory.getAmountOfLossType(), AmountOfLoss.class);
	}

	/**
	 * @return all known {@link Probability}s.
	 */
	public static List<Probability> getProbabilities() {
		return getAllInstancesOfType(RiskFactory.getProbabilityType(), Probability.class);
	}

	/**
	 * 
	 * @param deName
	 *        german name
	 * @param enName
	 *        english name
	 * @param r
	 *        red
	 * @param g
	 *        green
	 * @param b
	 *        blue
	 * @return the new {@link Color}
	 */
	public static Color createColor(String deName, String enName, int r, int g, int b) {
		Color color = RiskFactory.getInstance().createColor();
		color.tUpdateByName(TLNamed.NAME, asKey(deName, enName));
		color.setColor(new java.awt.Color(r, g, b));
		color.setRgb("rgb(" + r + "," + g + "," + b + ")");
		return color;
	}

	/**
	 * @param deName
	 *        german name
	 * @param enName
	 *        english name
	 * @param min
	 *        min amount value
	 * @param max
	 *        max amount value
	 * @return the new {@link AmountOfLoss}
	 */
	public static AmountOfLoss createAmountOfLoss(String deName, String enName, int min, int max) {
		AmountOfLoss loss = RiskFactory.getInstance().createAmountOfLoss();
		loss.tUpdateByName(TLNamed.NAME, asKey(deName, enName));
		loss.setMin(min);
		loss.setMax(max);
		return loss;
	}

	/**
	 * @param deName
	 *        german name
	 * @param enName
	 *        english name
	 * @param min
	 *        min probability value
	 * @param max
	 *        max probability value
	 * @return the new {@link Probability}
	 */
	public static Probability createProbability(String deName, String enName, int min, int max) {
		Probability probability = RiskFactory.getInstance().createProbability();
		probability.tUpdateByName(TLNamed.NAME, asKey(deName, enName));
		probability.setMin(min);
		probability.setMax(max);
		return probability;
	}

	private static ResKey asKey(String de, String en) {
		return ResKey.builder(TLNamed.NAME).add(Locale.GERMAN, de).add(Locale.ENGLISH, en).build();
	}
}
