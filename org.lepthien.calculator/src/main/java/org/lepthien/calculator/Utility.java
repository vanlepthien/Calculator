package org.lepthien.calculator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.function.Function;

public class Utility {

	static String alphaset = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz@$";

	static public String getBaseDigit(int number, int base) {
		Function<Integer, String> baseCalc;
		if (number >= base) {
			throw new IllegalArgumentException("Number " + number + " must be less than base " + base + ".");
		}
		if(base == 60) {
			baseCalc = (it) -> {
				String string = it.toString();
				if(string.length()==2) {
					return string+"'";
				}
				return "0"+string+ "'";
			};
		} else if (base <= 64) {
			baseCalc = (it) -> alphaset.substring(it, it + 1);
		} else {
			baseCalc = (it) -> {
				int maxLen = Integer.toHexString(base - 1).length();
				String val = Integer.toHexString(it);
				int valLen = val.length();
				String retval;
				if (valLen == maxLen) {
					retval = "'" + val + "'";
				} else {
					retval = "'" + "0000".substring(0, maxLen - valLen) + val + "'";
				}
				return retval;
			};
		}
		return baseCalc.apply(number);
	}

	public static String getStringPosition(int i, int j) {
		return Integer.toString(i) + " " + Integer.toString(j);
	}

	public static String getBaseDigit(BigInteger number, int base) {
		BigInteger bBase = BigInteger.valueOf(base);
		if (number.compareTo(bBase) >= 0) {
			throw new IllegalArgumentException("Number " + number + " must be less than base " + base + ".");
		}
		int iNumber = number.intValue();
		return getBaseDigit(iNumber, base);
	}

	static public String formatFixed(BigInteger bigInt) {

		int base = DisplayConfiguration.getBase();

		StringBuilder s = new StringBuilder();

		switch (bigInt.signum()) {
		case 0:
			s.append(getBaseDigit(0, base));
			return s.toString();
		default:
		}
		
		BigInteger bBase = BigInteger.valueOf(base);
		BigInteger posRegister = bigInt.abs();

		while (posRegister.signum() != 0) {
			BigInteger[] quo = posRegister.divideAndRemainder(bBase);
			String digit = getBaseDigit(quo[1], base);
			// System.out.println(quo[0]+":"+quo[1]);
			s.insert(0, digit);
			posRegister = quo[0];
		}
		if (bigInt.signum() < 0) {
			s.insert(0, "-");
		}

		return s.toString();
	}

	static public String formatFloat(BigDecimal bigDec) {

		int base = DisplayConfiguration.getBase();

		StringBuilder s = new StringBuilder();

		BigInteger intPart = bigDec.toBigInteger();
		if (intPart.equals(BigInteger.ZERO)) {
			if (bigDec.compareTo(BigDecimal.ZERO) < 0) {
				s.append('-');
			}
			s.append(getBaseDigit(0, base));
		} else {
			s.append(formatFixed(intPart));
		}
		s.append(".");
		BigDecimal bBase = BigDecimal.valueOf(base);
		BigDecimal posRegister = bigDec.abs();
		BigDecimal frac = posRegister.remainder(BigDecimal.ONE);

		int scale = DisplayConfiguration.getScale();

		while (scale > 0 && !frac.equals(BigDecimal.ZERO)) {
			scale--;
			BigDecimal next = frac.multiply(bBase);
			intPart = next.toBigInteger();
			frac = next.remainder(BigDecimal.ONE);
			String digit = getBaseDigit(intPart, base);
			s.append(digit);
		}
		return s.toString();
	}

}
