/**
 * 
 */
package org.lepthien.calculator;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author William Van Lepthien
 *
 */
class DecimalCalculatorTest {

	private DecimalCalculator calculator;

	@BeforeEach
	void setUp() {
		calculator = new DecimalCalculator();
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
		BigDecimal value = BigDecimal.valueOf(i_value).setScale(DisplayConfiguration.getScale(),
				BigDecimal.ROUND_HALF_EVEN);
		String result = calculator.formatValue();
		int len = result.length() - 1;
		assertEquals(value.toString().substring(0, len), result.substring(0, len));
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
		BigDecimal value1 = BigDecimal.valueOf(Math.random()).setScale(DisplayConfiguration.getScale(),
				BigDecimal.ROUND_HALF_EVEN);
		BigDecimal value2 = BigDecimal.valueOf(Math.random()).setScale(DisplayConfiguration.getScale(),
				BigDecimal.ROUND_HALF_EVEN);
		BigDecimal value = value1.add(value2);
		calculator.setRegisterFromNumber(value1);
		calculator.setValueFromNumber(value2);
		calculator.add();
		String result = calculator.formatRegister();
		int len = result.length() - 1;
		assertEquals(value.toString().substring(0, len), result.substring(0, len));
	}

	/**
	 * Test method for {@link org.lepthien.calculator.DecimalCalculator#multiply()}.
	 */
	@Test
	void testMultiply() {
		BigDecimal value1 = BigDecimal.valueOf(Math.random()).setScale(DisplayConfiguration.getScale(),
				BigDecimal.ROUND_HALF_EVEN);
		BigDecimal value2 = BigDecimal.valueOf(Math.random()).setScale(DisplayConfiguration.getScale(),
				BigDecimal.ROUND_HALF_EVEN);
		BigDecimal value = value1.multiply(value2);
		calculator.setRegisterFromNumber(value1);
		calculator.setValueFromNumber(value2);
		calculator.multiply();
		String result = calculator.formatRegister();
		int len = result.length() - 1;
		assertEquals(value.toString().substring(0, len), result.substring(0, len));
	}

	/**
	 * Test method for {@link org.lepthien.calculator.DecimalCalculator#subtract()}.
	 */
	@Test
	void testSubtract() {
		BigDecimal value1 = BigDecimal.valueOf(Math.random()).setScale(DisplayConfiguration.getScale(),
				BigDecimal.ROUND_HALF_EVEN);
		BigDecimal value2 = BigDecimal.valueOf(Math.random()).setScale(DisplayConfiguration.getScale(),
				BigDecimal.ROUND_HALF_EVEN);
		BigDecimal value = value1.subtract(value2);
		calculator.setRegisterFromNumber(value1);
		calculator.setValueFromNumber(value2);
		calculator.subtract();
		String result = calculator.formatRegister();
		int len = result.length() - 1;
		assertEquals(value.toString().substring(0, len), result.substring(0, len));
	}

	/**
	 * Test method for {@link org.lepthien.calculator.DecimalCalculator#subtract()}.
	 */
	@Test
	void testSubtractPositive() {
		BigDecimal value1 = BigDecimal.valueOf(Math.random()).setScale(DisplayConfiguration.getScale(),
				BigDecimal.ROUND_HALF_EVEN);
		BigDecimal value2 = value1.divide(BigDecimal.valueOf(2)).setScale(DisplayConfiguration.getScale(),
				BigDecimal.ROUND_HALF_EVEN);
		BigDecimal value = value1.subtract(value2);
		calculator.setRegisterFromNumber(value1);
		calculator.setValueFromNumber(value2);
		calculator.subtract();
		String result = calculator.formatRegister();
		int len = result.length() - 1;
		assertEquals(value.toString().substring(0, len), result.substring(0, len));
	}

	/**
	 * Test method for {@link org.lepthien.calculator.DecimalCalculator#subtract()}.
	 */
	@Test
	void testSubtractNegative() {
		BigDecimal value1 = BigDecimal.valueOf(Math.random()).setScale(DisplayConfiguration.getScale(),
				BigDecimal.ROUND_HALF_EVEN);
		BigDecimal value2 = value1.multiply(BigDecimal.valueOf(2)).setScale(DisplayConfiguration.getScale(),
				BigDecimal.ROUND_HALF_EVEN);
		BigDecimal value = value1.subtract(value2);
		calculator.setRegisterFromNumber(value1);
		calculator.setValueFromNumber(value2);
		calculator.subtract();
		String result = calculator.formatRegister();
		int len = result.length() - 1;
		assertEquals(value.toString().substring(0, len), result.substring(0, len));
	}

	/**
	 * Test method for {@link org.lepthien.calculator.DecimalCalculator#subtract()}.
	 */
	@Test
	void testSubtractZero() {
		BigDecimal value1 = BigDecimal.valueOf(Math.random()).setScale(DisplayConfiguration.getScale(),
				BigDecimal.ROUND_HALF_EVEN);
		BigDecimal value2 = value1;
		BigDecimal value = value1.subtract(value2);
		calculator.setRegisterFromNumber(value1);
		calculator.setValueFromNumber(value2);
		calculator.subtract();
		String result = calculator.formatRegister();
		assertEquals(value.toPlainString(), result);
	}

	/**
	 * Test method for {@link org.lepthien.calculator.DecimalCalculator#divide()}.
	 */
	@Test
	void testDivide() {
		BigDecimal value1 = BigDecimal.valueOf(Math.random()).setScale(DisplayConfiguration.getScale(),
				BigDecimal.ROUND_HALF_EVEN);
		BigDecimal value2 = BigDecimal.valueOf(Math.random()).setScale(DisplayConfiguration.getScale(),
				BigDecimal.ROUND_HALF_EVEN);
		BigDecimal value = value1.divide(value2, 20, BigDecimal.ROUND_HALF_EVEN);
		calculator.setRegisterFromNumber(value1);
		calculator.setValueFromNumber(value2);
		calculator.divide();
		String result = calculator.formatRegister();
		int len = result.length() - 2;
		assertEquals(value.toString().substring(0, len), result.substring(0, len));
	}

	/**
	 * Test method for
	 * {@link org.lepthien.calculator.DecimalCalculator#zeroRegister()}.
	 */
	@Test
	void testZeroRegister() {
		calculator.zeroRegister();
		String formatted = calculator.formatRegister();
		BigDecimal result = new BigDecimal(formatted);
		assertEquals(BigDecimal.ZERO, result);
	}

	/**
	 * Test method for
	 * {@link org.lepthien.calculator.DecimalCalculator#zeroValue()}.
	 */
	@Test
	void testZeroValue() {
		calculator.zeroValue();
		String result = calculator.formatValue();
		assertEquals(BigDecimal.ZERO.toString(), result);
	}

	/**
	 * Test method for
	 * {@link org.lepthien.calculator.DecimalCalculator#negateValue()}.
	 */
	@Test
	void testNegateValue() {
		BigDecimal v0 = BigDecimal.valueOf(Math.random()).setScale(DisplayConfiguration.getScale(),
				BigDecimal.ROUND_HALF_EVEN);
		BigDecimal value = v0.negate();
		calculator.setValueFromNumber(v0);
		calculator.negateValue();
		String result = calculator.formatValue();
		int len = result.length() - 1;
		assertEquals(value.toString().substring(0, len), result.substring(0, len));
	}

	/**
	 * Test method for
	 * {@link org.lepthien.calculator.DecimalCalculator#add(java.math.BigDecimal)}.
	 */
	@Test
	void testAddBigDecimal() {
		BigDecimal value1 = BigDecimal.valueOf(Math.random()).setScale(DisplayConfiguration.getScale(),
				BigDecimal.ROUND_HALF_EVEN);
		BigDecimal value2 = BigDecimal.valueOf(Math.random()).setScale(DisplayConfiguration.getScale(),
				BigDecimal.ROUND_HALF_EVEN);
		BigDecimal value = value1.add(value2);
		calculator.setRegisterFromNumber(value1);
		calculator.add(value2);
		String result = calculator.formatRegister();
		int len = result.length() - 1;
		assertEquals(value.toString().substring(0, len), result.substring(0, len));
	}

	/**
	 * Test method for
	 * {@link org.lepthien.calculator.DecimalCalculator#multiply(java.math.BigDecimal)}.
	 */
	@Test
	void testMultiplyBigDecimal() {
		BigDecimal value1 = BigDecimal.valueOf(Math.random()).setScale(DisplayConfiguration.getScale(),
				BigDecimal.ROUND_HALF_EVEN);
		BigDecimal value2 = BigDecimal.valueOf(Math.random()).setScale(DisplayConfiguration.getScale(),
				BigDecimal.ROUND_HALF_EVEN);
		BigDecimal value = value1.multiply(value2);
		calculator.setRegisterFromNumber(value1);
		calculator.multiply(value2);
		String result = calculator.formatRegister();
		int len = result.length() - 1;
		assertEquals(value.toString().substring(0, len), result.substring(0, len));
	}

	/**
	 * Test method for
	 * {@link org.lepthien.calculator.DecimalCalculator#subtract(java.math.BigDecimal)}.
	 */
	@Test
	void testSubtractBigDecimal() {
		BigDecimal value1 = BigDecimal.valueOf(100001 / 4);
		BigDecimal value2 = BigDecimal.valueOf(200001 / 3, 20);
		BigDecimal value = value1.subtract(value2);
		calculator.setRegisterFromNumber(value1);
		calculator.subtract(value2);
		String result = calculator.formatRegister();
		int len = result.length() - 1;
		assertEquals(value.toString().substring(0, len), result.substring(0, len));
	}

	/**
	 * Test method for
	 * {@link org.lepthien.calculator.DecimalCalculator#divide(java.math.BigDecimal)}.
	 */
	@Test
	void testDivideBigDecimal() {
		BigDecimal value1 = BigDecimal.valueOf(Math.random()).setScale(DisplayConfiguration.getScale(),
				BigDecimal.ROUND_HALF_EVEN);
		BigDecimal value2 = BigDecimal.valueOf(Math.random()).setScale(DisplayConfiguration.getScale(),
				BigDecimal.ROUND_HALF_EVEN);
		BigDecimal value = value1.divide(value2, 20, BigDecimal.ROUND_HALF_EVEN);
		calculator.setRegisterFromNumber(value1);
		calculator.divide(value2);
		String result = calculator.formatRegister();
		int len = result.length() - 1;
		assertEquals(value.toString().substring(0, len), result.substring(0, len));
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
		BigDecimal value = BigDecimal.valueOf(Math.random() / Math.random()).setScale(DisplayConfiguration.getScale(),
				BigDecimal.ROUND_HALF_EVEN);
		calculator.setRegisterFromNumber(value);
		String result = calculator.formatRegister();
		int len = result.length() - 1;
		assertEquals(value.toString().substring(0, len), result.substring(0, len));
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
		BigDecimal value = BigDecimal.valueOf(Math.random() / Math.random()).setScale(DisplayConfiguration.getScale(),
				BigDecimal.ROUND_HALF_EVEN);
		calculator.setValueFromNumber(value);
		String result = calculator.formatValue();
		int len = result.length() - 1;
		assertEquals(value.toString().substring(0, len), result.substring(0, len));
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
		BigDecimal value = BigDecimal.valueOf(10);
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
		BigDecimal value = BigDecimal.valueOf(Math.random() / Math.random()).setScale(DisplayConfiguration.getScale(),
				BigDecimal.ROUND_HALF_EVEN);
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
		BigDecimal value = BigDecimal.valueOf(Math.random() / Math.random());
		calculator.setRegisterFromNumber(value);
		calculator.setValueToRegister();
		String result = calculator.formatValue();
		int len = result.length() - 1;
		assertEquals(value.toString().substring(0, len), result.substring(0, len));
	}

	/**
	 * Test method for
	 * {@link org.lepthien.calculator.NumericCalculator#setRegisterFromNumber(java.lang.Number)}.
	 */
	@Test
	void testSetRegisterFromNumber() {
		int iValue = (int) (2048 * Math.random());
		BigDecimal value = BigDecimal.valueOf(iValue).setScale(DisplayConfiguration.getScale(),
				BigDecimal.ROUND_HALF_EVEN);
		calculator.setRegisterFromNumber(iValue);
		String result = calculator.formatRegister();
		int len = result.length() - 1;
		assertEquals(value.toString().substring(0, len), result.substring(0, len));
		double dValue = Math.random();
		value = BigDecimal.valueOf(dValue).setScale(DisplayConfiguration.getScale(), BigDecimal.ROUND_HALF_EVEN);
		calculator.setRegisterFromNumber(dValue);
		result = calculator.formatRegister();
		len = result.length() - 1;
		assertEquals(value.toString().substring(0, len), result.substring(0, len));
	}

	/**
	 * Test method for
	 * {@link org.lepthien.calculator.NumericCalculator#setValueFromNumber(java.lang.Number)}.
	 * 
	 * @throws Exception
	 */
	@Test
	void testSetValueFromNumber() throws Exception {
		int iValue = (int) (2048 * Math.random());
		BigDecimal value = BigDecimal.valueOf(iValue).setScale(DisplayConfiguration.getScale(),
				BigDecimal.ROUND_HALF_EVEN);
		calculator.setValueFromNumber(iValue);
		String result = calculator.formatValue();
		int len = result.length() - 1;
		assertEquals(value.toString().substring(0, len), result.substring(0, len));
		double dValue = Math.random();
		value = BigDecimal.valueOf(dValue).setScale(DisplayConfiguration.getScale(), BigDecimal.ROUND_HALF_EVEN);
		calculator.setValueFromNumber(dValue);
		result = calculator.formatValue();
		len = result.length() - 1;
		assertEquals(value.toString().substring(0, len), result.substring(0, len));
	}

	/**
	 * Test method for
	 * {@link org.lepthien.calculator.NumericCalculator#formatRegister()}.
	 */
	@Test
	void testFormatRegister() {
		BigDecimal value = BigDecimal.valueOf(255 + Math.random() / Math.random())
				.setScale(DisplayConfiguration.getScale(), BigDecimal.ROUND_HALF_EVEN);
		calculator.setRegisterFromNumber(value);
		String result = calculator.formatRegister();
		int len = result.length() - 1;
		assertEquals(value.toString().substring(0, len), result.substring(0, len));
	}

	/**
	 * Test method for
	 * {@link org.lepthien.calculator.NumericCalculator#formatValue()}.
	 */
	@Test
	void testFormatValue() {
		BigDecimal value = BigDecimal.valueOf(255 + Math.random() / Math.random())
				.setScale(DisplayConfiguration.getScale(), BigDecimal.ROUND_HALF_EVEN);
		calculator.setValueFromNumber(value);
		String result = calculator.formatValue();
		int len = result.length() - 1;
		assertEquals(value.toString().substring(0, len), result.substring(0, len));
	}

}
