package org.lepthien.calculator.swing;

import org.lepthien.calculator.DecimalCalculator;
import org.lepthien.calculator.DisplayConfiguration;
import org.lepthien.calculator.FixedCalculator;
import org.lepthien.calculator.NumericCalculator;

public class CalculatorFactory {

	public static NumericCalculator<?> newCalculator() {
		if (DisplayConfiguration.isFixed()) {
			return new FixedCalculator();
		}

		return new DecimalCalculator();
	}

}
