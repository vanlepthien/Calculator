package org.lepthien.calculator;

import java.util.function.Function;

/**
 * @author William Van Lepthien
 *
 * @param <T>
 */
abstract public class NumericCalculator<T extends Number> implements CalculatorPresenter {
	private Operation lastOperation = Operation.NONE;
	private Input lastInput = Input.INIT;
	protected T register;
	Function<Void, String> zeroDivideFunction;

	/**
	 * Adds another digit to the input value field
	 * 
	 * @param digit
	 *            an integer number between 0 and (base - 1)
	 */
	public abstract void addDigit(int digit);

	/**
	 * Called when a decimal point is added to the input value field.
	 * 
	 * For integer calculators, this method should be a no-op.
	 */
	abstract public void setDecimal();

	/**
	 * Retrieve the number base for the current calculator configuration.
	 * 
	 * @return the base as an integer.
	 */
	public int getBase() {
		return DisplayConfiguration.getBase();
	}

	/**
	 * Sets the base for the current calculator configuration.
	 * 
	 * The maximum base is implementation dependent, but in all cases, bases between
	 * 2 and 256 (inclusive) should be valid.
	 * 
	 * @param base
	 *            the base to be used for numeric representations and key
	 *            generation.
	 */
	public void setBase(int base) {
		DisplayConfiguration.setBase(base);
	}

	/**
	 * @param number The number whose numeral representation is to be returned
	 * @return The displayable representation of the base digit
	 */
	public String getBaseDigit(int number) {
		return Utility.getBaseDigit(number, DisplayConfiguration.getBase());
	}

	/**
	 * Add the input value field to the register field
	 */
	abstract public void add();

	/**
	 * Multiply the register field by the value field
	 */
	abstract public void multiply();

	/**
	 * Subtract the input value field from the register field.
	 */
	abstract public void subtract();

	/**
	 * Divide the register field by the input value field.
	 * 
	 * If the input value field contains a zero value, the function defined by the
	 * onZeroDivide method is called. If onZeroDivide has not defined such a
	 * function, the division operation is ignored. Results may be scaled, rounded,
	 * or truncated, depending on the implementation.
	 */
	abstract public void divide();

	/**
	 * Set a lambda to be called if division by zero is detected.
	 * 
	 * @param zeroDivide
	 */
	public void onZeroDivide(Function<Void, String> zeroDivide) {
		this.zeroDivideFunction = zeroDivide;
	}

	/**
	 * Add a value of the type defined by the concrete class to the register value.
	 * 
	 * @param val
	 *            a value of the type defined by the concrete class.
	 */
	abstract public void add(T val);

	/**
	 * Multiply the register field by a value of the type defined by the concrete
	 * class.
	 * 
	 * @param val
	 *            a value of the type defined by the concrete class.
	 */
	abstract public void multiply(T val);

	/**
	 * Subtract a value of the type defined by the concrete class from the register
	 * value.
	 * 
	 * @param val
	 *            a value of the type defined by the concrete class.
	 */
	abstract public void subtract(T val);

	/**
	 * Divide the register field by a value of the type defined by the concrete
	 * class.
	 * 
	 * If the value is zero, the function defined by the onZeroDivide method is
	 * called. If onZeroDivide has not defined such a function, the division
	 * operation is ignored. Results may be scaled, rounded, or truncated, depending
	 * on the implementation.
	 * 
	 * @param val
	 *            a value of the type defined by the concrete class.
	 */
	abstract public void divide(T val);

	/**
	 * Sets the register field to zero.
	 */
	abstract public void zeroRegister();

	/**
	 * Sets the input value field to zero.
	 */
	abstract public void zeroValue();

	/**
	 * Changes the sign of the input value field
	 */
	abstract public void negateValue();

	/**
	 * Returns the member of the Operation Enum that reflects the last successfully
	 * executed operation.
	 * 
	 * The last operation is set to NONE after the value field is updated or an
	 * error is detected.
	 * 
	 * @return the last Operation
	 */
	public Operation getLastOperation() {
		return lastOperation;
	}

	/**
	 * Sets the last operation.
	 * 
	 * @param lastOperation
	 *            a member of the Operation Enum.
	 */
	public void setLastOperation(Operation lastOperation) {
		this.lastOperation = lastOperation;
	}

	/**
	 * Gets the last Input type
	 * 
	 * @return the last Input type
	 */
	public Input getLastInput() {
		return lastInput;
	}

	/**
	 * Set the last Input type.
	 * 
	 * @param lastInput
	 *            a member of the Input Enum.
	 */
	public void setLastInput(Input lastInput) {
		this.lastInput = lastInput;
	}

	/**
	 * Set the register field to the value in the input value field
	 */
	abstract public void setRegisterToValue();

	/**
	 * Set the input value field to the register field value.
	 * 
	 * The value may be changed depending on the type specified in the concrete
	 * class definition, the register value scale, configuration scaling, and
	 * rounding.
	 */
	abstract public void setValueToRegister();

	/**
	 * Set the register field to a Number value.
	 * 
	 * The value may be changed depending on the type specified in the concrete
	 * class definition, the register value scale, configuration scaling, and
	 * rounding.
	 * 
	 * @param number
	 *            a Number value
	 */
	abstract public void setRegisterFromNumber(Number number);

	/**
	 * Set the input value field from a Number.
	 * 
	 * The value may be changed depending on the type specified in the concrete
	 * class definition, the register value scale, configuration scaling, and
	 * rounding.
	 * 
	 * @param number
	 */
	abstract public void setValueFromNumber(Number number);

	/**
	 * Gets the String representation of the register field.
	 * 
	 * Reflects the base.
	 * 
	 * @return a String value of the numeric field
	 */
	abstract public String formatRegister();

	/**
	 * Gets the String representation of the input value field.
	 * 
	 * Reflects the base.
	 * 
	 * @return
	 */
	abstract public String formatValue();

	abstract public void setRegisterFromRegister(NumericCalculator<?> oldCalculator);

	abstract public void setValueFromValue(NumericCalculator<?> oldCalculator);

	abstract Number getValue();

	abstract public void xor();

}
