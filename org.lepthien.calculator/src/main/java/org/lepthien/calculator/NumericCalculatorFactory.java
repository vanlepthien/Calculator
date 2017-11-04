package org.lepthien.calculator;

public class NumericCalculatorFactory {

	public NumericCalculator<? extends Number> newCalculatorModel(CalculatorPresenter presenter) {
		// Presenter passed in case the factory needs information to select a calculator.
		if (DisplayConfiguration.isFixed()) {
			return new FixedCalculator();
		}
		return new DecimalCalculator();
	}

}
