package org.lepthien.calculator.gwt.shared;

import java.math.BigInteger;

/**
 * The shared interface for the RPC service.
 */

public interface CalculatorService  {
	BigInteger changeSign() throws IllegalArgumentException;

	BigInteger add(BigInteger value) throws IllegalArgumentException;

	BigInteger subtract(BigInteger value) throws IllegalArgumentException;

	BigInteger multiply(BigInteger value) throws IllegalArgumentException;

	BigInteger divide(BigInteger value) throws IllegalArgumentException;

	BigInteger clear() throws IllegalArgumentException;

	BigInteger setRegister(BigInteger value) throws IllegalArgumentException;
}
