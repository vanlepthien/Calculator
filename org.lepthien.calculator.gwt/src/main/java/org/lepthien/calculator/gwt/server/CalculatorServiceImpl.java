package org.lepthien.calculator.gwt.server;

import java.math.BigInteger;

import javax.servlet.http.HttpSession;

import org.lepthien.calculator.gwt.shared.CalculatorService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class CalculatorServiceImpl extends RemoteServiceServlet implements CalculatorService {

	@Override
	public BigInteger add(BigInteger value) throws IllegalArgumentException {
		BigInteger register = getRegister();
		register = register.add(value);
		setRegister(register);
		return register;
	}

	@Override
	public BigInteger changeSign() throws IllegalArgumentException {
		BigInteger register = getRegister();
		register = register.negate();
		setRegister(register);
		return register;
	}

	@Override
	public BigInteger clear() throws IllegalArgumentException {
		BigInteger register = BigInteger.ZERO;
		setRegister(register);
		return register;
	}

	@Override
	public BigInteger divide(BigInteger value){
		BigInteger register = getRegister();
		register = register.divide(value);
		setRegister(register);
		return register;
	}

	public BigInteger getRegister() {
		HttpSession session = getSession();
		Object registerObject = session.getAttribute("register");
		if (registerObject == null) {
			setRegister(BigInteger.ZERO);
			return BigInteger.ZERO;
		}
		if (registerObject instanceof String) {
			try {
				String registerString = (String) registerObject;
				return new BigInteger(registerString);
			} catch (Exception x) {
				setRegister(BigInteger.ZERO);
				return BigInteger.ZERO;
			}
		}
		return BigInteger.ZERO;
	}

	HttpSession getSession() {
		return this.getThreadLocalRequest().getSession();
	}

	@Override
	public BigInteger multiply(BigInteger value) throws IllegalArgumentException {
		BigInteger register = getRegister();
		register = register.multiply(value);
		setRegister(register);
		return register;
	}

	public BigInteger setRegister(BigInteger value) {
		setRegisterString(value);
		return value;
	}

	private String setRegisterString(BigInteger register) {
		HttpSession session = getSession();
		String registerString = register.toString();
		session.setAttribute("register", registerString);
		return registerString;

	}

	@Override
	public BigInteger subtract(BigInteger value) throws IllegalArgumentException {
		BigInteger register = getRegister();
		register = register.subtract(value);
		setRegister(register);
		return register;
	}
}
