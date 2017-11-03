package org.lepthien.calculator;

import java.math.BigDecimal;
import java.math.BigInteger;

public class DecimalCalculator extends NumericCalculator<BigDecimal> {
	private InputValue value = new InputValue();

	{
		register = BigDecimal.ZERO;
	}

	@Override
	public void addDigit(int digit) {
		int base = DisplayConfiguration.getBase();
		if (digit >= base) {
			throw new IllegalArgumentException("Digit value " + digit + " >= base " + base + ".");
		}
		if (digit < 0) {
			throw new IllegalArgumentException("Digit value " + digit + " < zero.");
		}
		switch (getLastInput()) {
		case DIGIT:
		case DECIMAL:
			value.addDigit(digit);
			break;
		default:
			// no previous input into value
			value = new InputValue();
			value.addDigit(digit);
		}

		setLastInput(Input.DIGIT);
	}

	@Override
	public void setDecimal() {

		if (value.hasDecimal()) {
			// ignore second decimal
			return;
		}

		switch (getLastInput()) {
		case DIGIT:
			value.addDecimal();
			break;
		default:
			// no previous input into value
			value = new InputValue();
			value.addDecimal();
		}
		setLastInput(Input.DECIMAL);
	}

	@Override
	public void add() {
		add(value.toBigDecimal());
	}

	@Override
	public void multiply() {
		multiply(value.toBigDecimal());
	}

	@Override
	public void subtract() {
		subtract(value.toBigDecimal());
	}

	@Override
	public void divide() {
		divide(value.toBigDecimal());
	}

	@Override
	public void add(BigDecimal val) {
		setLastOperation(Operation.ADD);
		register = register.add(val);
	}

	@Override
	public void multiply(BigDecimal val) {
		setLastOperation(Operation.MULTIPLY);
		register = register.multiply(val);
	}

	@Override
	public void subtract(BigDecimal val) {
		setLastOperation(Operation.SUBTRACT);
		register = register.subtract(val);
	}

	@Override
	public void divide(BigDecimal val) {
		setLastOperation(Operation.DIVIDE);
		int rscale = Math.max(register.scale(), 0);
		int vscale = Math.max(val.scale(), 0);
		int nscale = Math.min(20, rscale + vscale);
		int scale = Math.max(8, nscale);
		register = register.divide(val, scale, BigDecimal.ROUND_HALF_EVEN);
	}

	@Override
	public void zeroRegister() {
		setLastOperation(Operation.NONE);
		register = BigDecimal.ZERO;
	}

	@Override
	public void zeroValue() {
		value = InputValue.ZERO;
	}

	@Override
	public void negateValue() {
		value.negate();
	}

	@Override
	public void setRegisterFromNumber(Number number) {
		register = BigDecimal.ZERO;
		if (number instanceof Integer || number instanceof Long) {
			register = BigDecimal.valueOf(number.longValue());
		} else if (number instanceof Double || number instanceof Float) {
			register = BigDecimal.valueOf(number.doubleValue()).setScale(DisplayConfiguration.getScale(),
					BigDecimal.ROUND_HALF_EVEN);
		} else if (number instanceof BigInteger) {
			register = new BigDecimal((BigInteger) number);
		} else if (number instanceof BigDecimal) {
			register = ((BigDecimal) number).setScale(DisplayConfiguration.getScale(), BigDecimal.ROUND_HALF_EVEN);
		}
	}

	@Override
	public void setValueFromNumber(Number number) {
		value = InputValue.ZERO;
		if (number instanceof Integer || number instanceof Long) {
			value = InputValue.valueOf(number.longValue());
		} else if (number instanceof Double || number instanceof Float) {
			value = InputValue.valueOf(number.doubleValue());
		} else if (number instanceof BigInteger) {
			value = InputValue.valueOf((BigInteger) number);
		} else if (number instanceof BigDecimal) {
			value = InputValue.valueOf((BigDecimal) number);
		}
	}

	@Override
	public String formatRegister() {
		return Utility.formatFloat(register);
	}

	@Override
	public String formatValue() {
		return value.format();
	}

	@Override
	public void setRegisterToValue() {
		register = value.toBigDecimal();
	}

	@Override
	public void setValueToRegister() {
		value = InputValue.valueOf(register);
	}

	// void setRegister(BigDecimal number) {
	// register = number;
	// }

	// void setValue(BigDecimal number) {
	// value = InputValue.valueOf(number);
	// }

	@Override
	public BigDecimal getValue() {
		return value.toBigDecimal();
	}

	@Override
	public void and() {
		// Not implemented for this class
		throw new UnsupportedOperationException("'and' operation not supported by " + this.getClass().getName());
	}

	@Override
	public void or() {
		// Not implemented for this class
		throw new UnsupportedOperationException("'or' operation not supported by " + this.getClass().getName());
	}

	@Override
	public void xor() {
		// Not implemented for this class
		throw new UnsupportedOperationException("'xor' operation not supported by " + this.getClass().getName());
	}

	@Override
	public void clear() {
		zeroRegister();
		zeroValue();
	}

	@Override
	public void setRegisterFromRegister(NumericCalculator<?> calculator) {
		Number number = calculator.register;
		setRegisterFromNumber(number);
	}

	@Override
	public void setValueFromValue(NumericCalculator<?> calculator) {
		Number number = calculator.getValue();
		setValueFromNumber(number);
	}
}
