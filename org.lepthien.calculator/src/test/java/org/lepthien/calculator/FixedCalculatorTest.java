/**
 * 
 */
package org.lepthien.calculator;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author William Van Lepthien
 *
 */
class FixedCalculatorTest {

	private FixedCalculator calculator;

	@BeforeEach
	void setUp() {
		calculator = new FixedCalculator();
		DisplayConfiguration.setBase(10);
	}

	@AfterEach
	void tearDown() {
		calculator = null;
	}

	/**
	 * Test method for
	 * {@link org.lepthien.calculator.DecimalCalculator#addDigit(int)}.
	 */
	@Test
	void testNewValue() {
		calculator.setBase(16);
		int i_value = 15;
		calculator.addDigit(i_value);
		String value = Integer.toHexString(i_value).toUpperCase();
		String result = calculator.formatValue();

		assertEquals(value, result);
	}

	/**
	 * Test method for
	 * {@link org.lepthien.calculator.DecimalCalculator#addDigit(int)}.
	 */
	@Test
	void testNewValue2() {
		int base = 16;
		calculator.setBase(base);
		int value = base + 1;
		Throwable e = assertThrows(IllegalArgumentException.class, () -> {
			calculator.addDigit(value);
		});
		assertEquals("Digit value " + value + " >= base " + base + ".", e.getMessage());
	}

	/**
	 * Test method for {@link org.lepthien.calculator.DecimalCalculator#add()}.
	 */
	@Test
	void testAdd() {
		BigInteger value1 = BigInteger.valueOf((long)( 100 * Math.random()));
		BigInteger value2 = BigInteger.valueOf((long)( 100 * Math.random()));
		BigInteger value = value1.add(value2);
		calculator.setRegisterFromNumber(value1);
		calculator.setValueFromNumber(value2);
		calculator.add();
		String result = calculator.formatRegister();
		assertEquals(value.toString(), result);
	}

	/**
	 * Test method for {@link org.lepthien.calculator.DecimalCalculator#multiply()}.
	 */
	@Test
	void testMultiply() {
		BigInteger value1 = BigInteger.valueOf((long)( 100 * Math.random()));
		BigInteger value2 = BigInteger.valueOf((long)( 100 * Math.random()));
		BigInteger value = value1.multiply(value2);
		calculator.setRegisterFromNumber(value1);
		calculator.setValueFromNumber(value2);
		calculator.multiply();
		String result = calculator.formatRegister();
		assertEquals(value.toString(), result);
	}

	/**
	 * Test method for {@link org.lepthien.calculator.DecimalCalculator#subtract()}.
	 */
	@Test
	void testSubtract() {
		BigInteger value1 = BigInteger.valueOf((long)( 100 * Math.random()));
		BigInteger value2 = BigInteger.valueOf((long)( 100 * Math.random()));
		BigInteger value = value1.subtract(value2);
		calculator.setRegisterFromNumber(value1);
		calculator.setValueFromNumber(value2);
		calculator.subtract();
		String result = calculator.formatRegister();
		assertEquals(value.toString(), result);
	}

	/**
	 * Test method for {@link org.lepthien.calculator.DecimalCalculator#subtract()}.
	 */
	@Test
	void testSubtractPositive() {
		BigInteger value1 = BigInteger.valueOf((long)( 100 * Math.random()));
		BigInteger value2 = value1.divide(BigInteger.valueOf(2));
		BigInteger value = value1.subtract(value2);
		calculator.setRegisterFromNumber(value1);
		calculator.setValueFromNumber(value2);
		calculator.subtract();
		String result = calculator.formatRegister();
		assertEquals(value.toString(), result);
	}

	/**
	 * Test method for {@link org.lepthien.calculator.DecimalCalculator#subtract()}.
	 */
	@Test
	void testSubtractNegative() {
		BigInteger value1 = BigInteger.valueOf((long)( 100 * Math.random()));
		BigInteger value2 = value1.multiply(BigInteger.valueOf(2));
		BigInteger value = value1.subtract(value2);
		calculator.setRegisterFromNumber(value1);
		calculator.setValueFromNumber(value2);
		calculator.subtract();
		String result = calculator.formatRegister();
		assertEquals(value.toString(), result);
	}

	/**
	 * Test method for {@link org.lepthien.calculator.DecimalCalculator#subtract()}.
	 */
	@Test
	void testSubtractZero() {
		BigInteger value1 = BigInteger.valueOf((long)( 100 * Math.random()));
		BigInteger value2 = value1;
		BigInteger value = value1.subtract(value2);
		calculator.setRegisterFromNumber(value1);
		calculator.setValueFromNumber(value2);
		calculator.subtract();
		String result = calculator.formatRegister();
		assertEquals(value.toString(), result);
	}

	/**
	 * Test method for {@link org.lepthien.calculator.DecimalCalculator#divide()}.
	 */
	@Test
	void testDivide() {
		BigInteger value1 = BigInteger.valueOf((long)( 100 * Math.random()));
		BigInteger value2 = BigInteger.valueOf((long)( 100 * Math.random()));
		BigInteger value = value1.divide(value2);
		calculator.setRegisterFromNumber(value1);
		calculator.setValueFromNumber(value2);
		calculator.divide();
		String result = calculator.formatRegister();
		assertEquals(value.toString(), result);
	}

	/**
	 * Test method for
	 * {@link org.lepthien.calculator.DecimalCalculator#zeroRegister()}.
	 */
	@Test
	void testZeroRegister() {
		calculator.zeroRegister();
		String formatted = calculator.formatRegister();
		BigInteger result = new BigInteger(formatted);
		assertEquals(BigInteger.ZERO, result);
	}

	/**
	 * Test method for
	 * {@link org.lepthien.calculator.DecimalCalculator#zeroValue()}.
	 */
	@Test
	void testZeroValue() {
		calculator.zeroValue();
		String result = calculator.formatValue();
		assertEquals(BigInteger.ZERO.toString(), result);
	}

	/**
	 * Test method for
	 * {@link org.lepthien.calculator.DecimalCalculator#negateValue()}.
	 */
	@Test
	void testNegateValue() {
		BigInteger v0 = BigInteger.valueOf((long)( 100 * Math.random()));
		BigInteger value = v0.negate();
		calculator.setValueFromNumber(v0);
		calculator.negateValue();
		String result = calculator.formatValue();
		assertEquals(value.toString(), result);
	}

	/**
	 * Test method for
	 * {@link org.lepthien.calculator.DecimalCalculator#add(java.math.BigInteger)}.
	 */
	@Test
	void testAddBigInteger() {
		BigInteger value1 = BigInteger.valueOf((long)( 100 * Math.random()));
		BigInteger value2 = BigInteger.valueOf((long)( 100 * Math.random()));
		BigInteger value = value1.add(value2);
		calculator.setRegisterFromNumber(value1);
		calculator.add(value2);
		String result = calculator.formatRegister();
		assertEquals(value.toString(), result);
	}

	/**
	 * Test method for
	 * {@link org.lepthien.calculator.DecimalCalculator#multiply(java.math.BigInteger)}.
	 */
	@Test
	void testMultiplyBigInteger() {
		BigInteger value1 = BigInteger.valueOf((long)( 100 * Math.random()));
		BigInteger value2 = BigInteger.valueOf((long)( 100 * Math.random()));
		BigInteger value = value1.multiply(value2);
		calculator.setRegisterFromNumber(value1);
		calculator.multiply(value2);
		String result = calculator.formatRegister();
		assertEquals(value.toString(), result);
	}

	/**
	 * Test method for
	 * {@link org.lepthien.calculator.DecimalCalculator#subtract(java.math.BigInteger)}.
	 */
	@Test
	void testSubtractBigInteger() {
		BigInteger value1 = BigInteger.valueOf((long)( 100 * Math.random()));
		BigInteger value2 = BigInteger.valueOf((long)( 100 * Math.random()));
		BigInteger value = value1.subtract(value2);
		calculator.setRegisterFromNumber(value1);
		calculator.subtract(value2);
		String result = calculator.formatRegister();
		assertEquals(value.toString(), result);
	}

	/**
	 * Test method for
	 * {@link org.lepthien.calculator.DecimalCalculator#divide(java.math.BigInteger)}.
	 */
	@Test
	void testDivideBigInteger() {
		BigInteger value1 = BigInteger.valueOf((long)( 100 * Math.random()));
		BigInteger value2 = BigInteger.valueOf((long)( 100 * Math.random()));
		BigInteger value = value1.divide(value2);
		calculator.setRegisterFromNumber(value1);
		calculator.divide(value2);
		String result = calculator.formatRegister();
		assertEquals(value.toString(), result);
	}

	/**
	 * Test method for
	 * {@link org.lepthien.calculator.NumericCalculator#setDecimal()}.
	 * 
	 * Not clear if this method should exist. Not really calculator function. Will
	 * fail until determined what to do with NumericCalculator
	 * 
	 */
	@Test
	void testSetDecimal() {

		// fail("Perhaps should be removed.");
	}

	/**
	 * Test method for
	 * {@link org.lepthien.calculator.NumericCalculator#formatRegister()}.
	 */
	@Test
	void testGetRegister() {
		BigInteger value = BigInteger.valueOf((long)( 100 * Math.random()) / (long)( 100 * Math.random()));
		calculator.setRegisterFromNumber(value);
		String result = calculator.formatRegister();
		assertEquals(value.toString(), result);
	}

	/**
	 * Test method for
	 * {@link org.lepthien.calculator.NumericCalculator#setRegisterFromNumber(java.lang.Number)}.
	 */
	@Test
	void testsetRegisterFromNumber() {
		testGetRegister();
	}

	/**
	 * Test method for
	 * {@link org.lepthien.calculator.NumericCalculator#formatValue()}.
	 */
	@Test
	void testGetValue() {
		BigInteger value = BigInteger.valueOf((long)( 100 * Math.random()) / (long)( 100 * Math.random()));
		calculator.setValueFromNumber(value);
		String result = calculator.formatValue();
		assertEquals(value.toString(), result);
	}

	/**
	 * Test method for
	 * {@link org.lepthien.calculator.NumericCalculator#setValueFromNumber(java.lang.Number)}.
	 */
	@Test
	void testsetValueFromNumber() {
		testGetValue();
	}

	/**
	 * Test method for {@link org.lepthien.calculator.NumericCalculator#getBase()}.
	 */
	@Test
	void testGetBase() {
		int base = calculator.getBase();
		assertEquals(10, base);
	}

	/**
	 * Test method for
	 * {@link org.lepthien.calculator.NumericCalculator#setBase(int)}.
	 */
	@Test
	void testSetBase() {
		calculator.setBase(256);
		int base = calculator.getBase();
		assertEquals(256, base);
	}

	/**
	 * Test method for
	 * {@link org.lepthien.calculator.NumericCalculator#getLastOperation()}.
	 * 
	 * Unknown if this test is needed
	 */
	@Test
	void testGetLastOperation() {
		Operation op = calculator.getLastOperation();
		assertEquals(Operation.NONE, op);
		BigInteger value = BigInteger.valueOf(10);
		calculator.add(value);
		op = calculator.getLastOperation();
		assertEquals(Operation.ADD, op);
	}

	/**
	 * Test method for
	 * {@link org.lepthien.calculator.NumericCalculator#setLastOperation(org.lepthien.calculator.Operation)}.
	 */
	@Test
	void testSetLastOperation() {
		calculator.setLastOperation(Operation.EQUALS);
		Operation op = calculator.getLastOperation();
		assertEquals(Operation.EQUALS, op);
	}

	/**
	 * Test method for
	 * {@link org.lepthien.calculator.NumericCalculator#getLastInput()}.
	 */
	@Test
	void testGetLastInput() {
		Input input = calculator.getLastInput();
		assertEquals(Input.INIT, input);
	}

	/**
	 * Test method for
	 * {@link org.lepthien.calculator.NumericCalculator#setLastInput(org.lepthien.calculator.Input)}.
	 */
	@Test
	void testSetLastInput() {
		calculator.setLastInput(Input.DIGIT);
		Input input = calculator.getLastInput();
		assertEquals(Input.DIGIT, input);
	}

	/**
	 * Test method for
	 * {@link org.lepthien.calculator.NumericCalculator#setRegisterToValue()}.
	 */
	@Test
	void testSetRegisterToValue() {
		BigInteger value = BigInteger.valueOf((long)( 100 * Math.random()) / (long)( 100 * Math.random()));
		calculator.setValueFromNumber(value);
		calculator.setRegisterToValue();
		String bd_value = calculator.formatValue();
		String bd_register = calculator.formatRegister();
		assertEquals(bd_value, bd_register);
	}

	/**
	 * Test method for
	 * {@link org.lepthien.calculator.NumericCalculator#setValueToRegister()}.
	 */
	@Test
	void testSetValueToRegister() {
		BigInteger value = BigInteger.valueOf((long)( 100 * Math.random()) / (long)( 100 * Math.random()));
		calculator.setRegisterFromNumber(value);
		calculator.setValueToRegister();
		String result = calculator.formatValue();
		assertEquals(value.toString(), result);
	}

	/**
	 * Test method for
	 * {@link org.lepthien.calculator.NumericCalculator#setRegisterFromNumber(java.lang.Number)}.
	 */
	@Test
	void testSetRegisterFromNumber() {
		int iValue = (int) (2048 * (long)( 100 * Math.random()));
		BigInteger value = BigInteger.valueOf(iValue);
		calculator.setRegisterFromNumber(iValue);
		String result = calculator.formatRegister();
		assertEquals(value.toString(), result);
		long dValue = (long)( 100 * Math.random());
		value = BigInteger.valueOf(dValue);
		calculator.setRegisterFromNumber(dValue);
		result = calculator.formatRegister();
		assertEquals(value.toString(), result);
	}

	/**
	 * Test method for
	 * {@link org.lepthien.calculator.NumericCalculator#setValueFromNumber(java.lang.Number)}.
	 * 
	 * @throws Exception
	 */
	@Test
	void testSetValueFromNumber() throws Exception {
		int iValue = (int) (2048 * (long)( 100 * Math.random()));
		BigInteger value = BigInteger.valueOf(iValue);
		calculator.setValueFromNumber(iValue);
		String result = calculator.formatValue();
		assertEquals(value.toString(), result);
		long dValue = (long) (100 * Math.random());
		value = BigInteger.valueOf(dValue);
		calculator.setValueFromNumber(dValue);
		result = calculator.formatValue();
		assertEquals(value.toString(), result);
	}

	/**
	 * Test method for
	 * {@link org.lepthien.calculator.NumericCalculator#formatRegister()}.
	 */
	@Test
	void testFormatRegister() {
		BigInteger value = BigInteger.valueOf((long)( (255 + (100 * Math.random()) / (100 * Math.random()))));
		calculator.setRegisterFromNumber(value);
		String result = calculator.formatRegister();
		assertEquals(value.toString(), result);
	}

	/**
	 * Test method for
	 * {@link org.lepthien.calculator.NumericCalculator#formatValue()}.
	 */
	@Test
	void testFormatValue() {
		BigInteger value = BigInteger.valueOf(255 + (long)( 100 * Math.random()) / (long)( 100 * Math.random()));
		calculator.setValueFromNumber(value);
		String result = calculator.formatValue();
		assertEquals(value.toString(), result);
	}

}
