package org.lepthien.calculator.gwt.client;

import java.math.BigInteger;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CalculatorServiceAsync {

	void add(BigInteger value, AsyncCallback<BigInteger> callback);

	void subtract(BigInteger name, AsyncCallback<BigInteger> callback);

	void multiply(BigInteger name, AsyncCallback<BigInteger> callback);

	void divide(BigInteger name, AsyncCallback<BigInteger> callback);

	void clear(AsyncCallback<BigInteger> callback);

	void setRegister(BigInteger value,
			AsyncCallback<BigInteger> callback);

	void changeSign(AsyncCallback<BigInteger> callback);

}
