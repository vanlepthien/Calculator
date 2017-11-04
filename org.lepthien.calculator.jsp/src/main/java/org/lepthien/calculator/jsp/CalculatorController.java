package org.lepthien.calculator.jsp;

import org.lepthien.calculator.CalculatorPresenter;
import org.lepthien.calculator.NumericCalculator;
import org.lepthien.calculator.NumericCalculatorFactory;

public class CalculatorController implements CalculatorPresenter {

	NumericCalculatorFactory calculatorFactory = new NumericCalculatorFactory();
	NumericCalculator<? extends Number> calculator = calculatorFactory.newCalculatorModel(this);

	@Override
	public void setBase(int base) {
		calculator.setBase(base);
	}

	@Override
	public void and() {
		calculator.and();
	}

	@Override
	public void or() {
		calculator.or();
	}

	@Override
	public void xor() {
		calculator.xor();
	}

	@Override
	public void add() {
		calculator.add();
	}

	@Override
	public void subtract() {
		calculator.subtract();
	}

	@Override
	public void multiply() {
		calculator.multiply();
	}

	@Override
	public void divide() {
		calculator.divide();
	}

	@Override
	public void clear() {
		calculator.clear();
	}

	@Override
	public String formatRegister() {
		return calculator.formatRegister();
	}

	@Override
	public String formatValue() {
		return calculator.formatValue();
	}

	@Override
	public void zeroValue() {
		calculator.zeroValue();
	}

	@Override
	public int getBase() {
		return calculator.getBase();
	}

	@Override
	public void negateValue() {
		calculator.negateValue();
	}

	@Override
	public void addDigit(int digit) {
		calculator.addDigit(digit);
	}

	@Override
	public void setDecimal() {
		calculator.setDecimal();
	}

}
