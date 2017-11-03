package org.lepthien.calculator;

import java.math.BigDecimal;
import java.math.BigInteger;

public class FixedCalculator extends NumericCalculator<BigInteger> {
	
	private BigInteger value;

	{
		value = BigInteger.ZERO;
		register = BigInteger.ZERO;
	}

	@Override
	public void addDigit(int digit) {
		int base = DisplayConfiguration.getBase();
		if (digit >= base) {
			throw new IllegalArgumentException("Digit value " + digit
					+ " >= base " + base + ".");
		}
		if (digit < 0) {
			throw new IllegalArgumentException("Digit value " + digit
					+ " < zero.");
		}
		switch (getLastInput()) {
		case DIGIT:
			System.out.println("Old " + value);
			System.out.println("  I " + digit);

			BigInteger v = value;
			v = v.multiply(BigInteger.valueOf(base));

			if (v.signum() >= 0) {
				v = v.add(BigInteger.valueOf(digit));
			} else {
				v = v.add(BigInteger.valueOf(-digit));
			}

			value = v;
			break;
		default:
			value = BigInteger.valueOf(digit);
		}
		
		System.out.println("  v " + value);

		setLastInput(Input.DIGIT);
	}

	@Override
	public void add() {
		add(value);
	}

	@Override
	public void multiply() {
		multiply(value);
	}

	@Override
	public void subtract() {
		subtract(value);
	}

	@Override
	public void divide() {
		divide(value);
	}

	@Override
	public void add(BigInteger val) {
		register = register.add(val);
		value = register;
		setLastOperation(Operation.ADD);
	}

	@Override
	public void multiply(BigInteger val) {
		register = register.multiply(val);
		value = register;
		setLastOperation(Operation.MULTIPLY);
	}

	@Override
	public void subtract(BigInteger val) {
		register = register.subtract(val);
		value = register;
		setLastOperation(Operation.SUBTRACT);
	}

	@Override
	public void divide(BigInteger val) {
		register = register.divide(val);
		value = register;
		setLastOperation(Operation.DIVIDE);
	}

	@Override
	public void zeroRegister() {
		register = BigInteger.ZERO;
	}

	@Override
	public void zeroValue() {
		value = BigInteger.ZERO;
	}

	@Override
	public void negateValue() {
		value = value.negate();
	}
	
	@Override
	public void setRegisterFromNumber(Number number) {
			if (number instanceof BigDecimal) {
				register =  ((BigDecimal) number).setScale(0, BigDecimal.ROUND_HALF_EVEN).toBigInteger();
			} else if (number instanceof BigInteger) {
				register =  (BigInteger) number;
			} else {
				register =  (new BigDecimal(number.doubleValue())).setScale(0, BigDecimal.ROUND_HALF_EVEN)
						.toBigInteger();
			}
	}

	@Override
	public void setValueFromNumber(Number number)  {
			value =  BigInteger.ZERO;
			if (number instanceof BigDecimal) {
				value =  ((BigDecimal) number).setScale(0, BigDecimal.ROUND_HALF_EVEN).toBigInteger();
			} else if (number instanceof BigInteger) {
				value =  (BigInteger) number;
			} else {
				value =  (new BigDecimal(number.doubleValue())).setScale(0, BigDecimal.ROUND_HALF_EVEN)
						.toBigInteger();
			}
	}

	@Override
	public String formatRegister() {
		return Utility.formatFixed((BigInteger) register);
	}

	@Override
	public String formatValue() {
		return Utility.formatFixed((BigInteger) value);
	}

	@Override
	public void setRegisterToValue() {
		register = value;
	}

	@Override
	public void setValueToRegister() {
		value = register;
	}

	@Override
	public void setDecimal() {
		// no-op for integer calculator
	}

	void setRegister(BigInteger number) {
		register = number;
	}

	public BigInteger getValue() {
		return value;
	}

	void setValue(BigInteger number) {
		value = number;		
	}

	@Override
	public void and() {
		register = register.and(value);
		value = register;
		setLastOperation(Operation.AND);
	}

	@Override
	public void or() {
		register = register.or(value);
		value = register;
		setLastOperation(Operation.OR);
	}

	@Override
	public void clear() {
		value = BigInteger.ZERO;
		register = BigInteger.ZERO;
		setLastOperation(Operation.NONE);
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

	@Override
	public void xor() {
		register = register.xor(value);
		value = register;
		setLastOperation(Operation.OR);
	}
}
