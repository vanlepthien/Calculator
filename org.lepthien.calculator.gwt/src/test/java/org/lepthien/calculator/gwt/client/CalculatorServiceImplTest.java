/**
 * 
 */
package org.lepthien.calculator.gwt.client;

import java.math.BigInteger;

import org.junit.Test;
import org.lepthien.calculator.gwt.server.CalculatorServiceImpl;

import com.google.gwt.junit.client.GWTTestCase;

/**
 * @author William Van Lepthien
 *
 */
public class CalculatorServiceImplTest extends GWTTestCase {

	/**
	 * Test method for {@link org.lepthien.calculator.gwt.server.CalculatorServiceImpl#add(java.math.BigInteger)}.
	 */
	@Test
	public void testAdd() {
		CalculatorServiceImpl csi = new CalculatorServiceImpl();
		BigInteger addend1 = BigInteger.valueOf(1024);
		BigInteger addend2 = BigInteger.valueOf(512);
		BigInteger sum = BigInteger.valueOf(1024+512);
		csi.setRegister(addend1);
		BigInteger result = csi.add(addend2);
		assertEquals(sum,result);
	}

	/**
	 * Test method for {@link org.lepthien.calculator.gwt.server.CalculatorServiceImpl#changeSign()}.
	 */
	@Test
	public void testChangeSign() {
		CalculatorServiceImpl csi = new CalculatorServiceImpl();
		BigInteger value = BigInteger.valueOf(1024);
		csi.setRegister(value);
		BigInteger result = csi.changeSign();
		assertEquals(value.negate(),result);
	}

	/**
	 * Test method for {@link org.lepthien.calculator.gwt.server.CalculatorServiceImpl#clear()}.
	 */
	@Test
	public void testClear() {
		CalculatorServiceImpl csi = new CalculatorServiceImpl();
		BigInteger value = BigInteger.valueOf(0);
		BigInteger result = csi.clear();
		assertEquals(value,result);
	}

	/**
	 * Test method for {@link org.lepthien.calculator.gwt.server.CalculatorServiceImpl#divide(java.math.BigInteger)}.
	 */
	@Test
	public void testDivide() {
		CalculatorServiceImpl csi = new CalculatorServiceImpl();
		BigInteger dividend = BigInteger.valueOf(1024);
		BigInteger divisor = BigInteger.valueOf(8);
		csi.setRegister(dividend);
		BigInteger result = csi.divide(divisor);
		assertEquals(128,result.longValue());
	}

	/**
	 * Test method for {@link org.lepthien.calculator.gwt.server.CalculatorServiceImpl#getRegister()}.
	 */
	@Test
	public void testGetRegister() {
		CalculatorServiceImpl csi = new CalculatorServiceImpl();
		BigInteger value = BigInteger.valueOf(Math.round(Math.random()*(1024*1024)));
		csi.setRegister(value);
		BigInteger result = csi.getRegister();
		assertEquals(value,result);
	}

	/**
	 * Test method for {@link org.lepthien.calculator.gwt.server.CalculatorServiceImpl#getSession()}.
	 */
	@Test
	public void testGetSession() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.lepthien.calculator.gwt.server.CalculatorServiceImpl#multiply(java.math.BigInteger)}.
	 */
	@Test
	public void testMultiply() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.lepthien.calculator.gwt.server.CalculatorServiceImpl#setRegister(java.math.BigInteger)}.
	 */
	@Test
	public void testSetRegister() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.lepthien.calculator.gwt.server.CalculatorServiceImpl#subtract(java.math.BigInteger)}.
	 */
	@Test
	public void testSubtract() {
		fail("Not yet implemented");
	}

	@Override
	public String getModuleName() {
		return null;
	}

}
