package org.lepthien.calculator.gwt.client;

import java.math.BigInteger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

/**
 * GWT JUnit tests must extend GWTTestCase.
 */
public class Org_lepthien_calculator_gwtTest extends GWTTestCase {

	/**
	 * Must refer to a valid module that sources this class.
	 */
	public String getModuleName() {
		return "org.lepthien.calculator.gwt.Org_lepthien_calculator_gwtJUnit";
	}

	/**
	 * This test will send a request to the server using the clear method in
	 * CalculatorService and verify the response.
	 */
	public void testCalculatorServiceClear() {
		// Create the service that we will test.
		CalculatorServiceAsync CalculatorService = GWT.create(CalculatorService.class);
		ServiceDefTarget target = (ServiceDefTarget) CalculatorService;
		target.setServiceEntryPoint(GWT.getModuleBaseURL() + "org_lepthien_calculator_gwt/calculator");

		// Since RPC calls are asynchronous, we will need to wait for a response
		// after this test method returns. This line tells the test runner to wait
		// up to 10 seconds before timing out.
		delayTestFinish(10000);

		// Send a request to the server.
		CalculatorService.clear(new AsyncCallback<BigInteger>() {
			public void onFailure(Throwable caught) {
				// The request resulted in an unexpected error.
				fail("Request failure: " + caught.getMessage());
			}

			public void onSuccess(BigInteger result) {
				// Verify that the response is correct.
				assertTrue(result.longValue() == 0l);

				// Now that we have received a response, we need to tell the test runner
				// that the test is complete. You must call finishTest() after an
				// asynchronous test finishes successfully, or the test will time out.
				finishTest();
			}
		});
	}

	/**
	 * This test will send a request to the server using the add method in
	 * CalculatorService and verify the response.
	 */
	public void testCalculatorServiceAdd() {
		// Create the service that we will test.
		CalculatorServiceAsync CalculatorService = GWT.create(CalculatorService.class);
		ServiceDefTarget target = (ServiceDefTarget) CalculatorService;
		target.setServiceEntryPoint(GWT.getModuleBaseURL() + "org_lepthien_calculator_gwt/calculator");

		// Since RPC calls are asynchronous, we will need to wait for a response
		// after this test method returns. This line tells the test runner to wait
		// up to 10 seconds before timing out.
		delayTestFinish(10000);

		// Send a request to the server.
		BigInteger addend = BigInteger.valueOf(10);
		CalculatorService.add(addend, new AsyncCallback<BigInteger>() {
			public void onFailure(Throwable caught) {
				// The request resulted in an unexpected error.
				fail("Request failure: " + caught.getMessage());
			}

			public void onSuccess(BigInteger result) {
				// Verify that the response is correct.
				assertEquals(10, result.longValue());

				// Now that we have received a response, we need to tell the test runner
				// that the test is complete. You must call finishTest() after an
				// asynchronous test finishes successfully, or the test will time out.
				finishTest();
			}
		});
	}

	/**
	 * This test will send a request to the server using the multiply method in
	 * CalculatorService and verify the response.
	 */
	public void testCalculatorServiceAdd2() {
		// Create the service that we will test.
		CalculatorServiceAsync CalculatorService = GWT.create(CalculatorService.class);
		ServiceDefTarget target = (ServiceDefTarget) CalculatorService;
		target.setServiceEntryPoint(GWT.getModuleBaseURL() + "org_lepthien_calculator_gwt/calculator");

		// Since RPC calls are asynchronous, we will need to wait for a response
		// after this test method returns. This line tells the test runner to wait
		// up to 10 seconds before timing out.
		delayTestFinish(10000);

		// Send a request to the server.
		BigInteger multiplicand = BigInteger.valueOf(10);
		CalculatorService.multiply(multiplicand, new AsyncCallback<BigInteger>() {
			public void onFailure(Throwable caught) {
				// The request resulted in an unexpected error.
				fail("Request failure: " + caught.getMessage());
			}

			public void onSuccess(BigInteger result) {
				// Verify that the response is correct.
				assertEquals(100, result.longValue());

				// Now that we have received a response, we need to tell the test runner
				// that the test is complete. You must call finishTest() after an
				// asynchronous test finishes successfully, or the test will time out.
				finishTest();
			}
		});
	}

	/**
	 * This test will send a request to the server using the changeSign method in
	 * CalculatorService and verify the response.
	 */
	public void testCalculatorServiceChangeSign() {
		// Create the service that we will test.
		CalculatorServiceAsync CalculatorService = GWT.create(CalculatorService.class);
		ServiceDefTarget target = (ServiceDefTarget) CalculatorService;
		target.setServiceEntryPoint(GWT.getModuleBaseURL() + "org_lepthien_calculator_gwt/calculator");

		// Since RPC calls are asynchronous, we will need to wait for a response
		// after this test method returns. This line tells the test runner to wait
		// up to 10 seconds before timing out.
		delayTestFinish(10000);

		// Send a request to the server.
		CalculatorService.changeSign(new AsyncCallback<BigInteger>() {
			public void onFailure(Throwable caught) {
				// The request resulted in an unexpected error.
				fail("Request failure: " + caught.getMessage());
			}

			public void onSuccess(BigInteger result) {
				// Verify that the response is correct.
				assertEquals(-100, result.longValue());

				// Now that we have received a response, we need to tell the test runner
				// that the test is complete. You must call finishTest() after an
				// asynchronous test finishes successfully, or the test will time out.
				finishTest();
			}
		});
	}

	/**
	 * This test will send a request to the server using the divide method in
	 * CalculatorService and verify the response.
	 */
	public void testCalculatorServiceDivide() {
		// Create the service that we will test.
		CalculatorServiceAsync CalculatorService = GWT.create(CalculatorService.class);
		ServiceDefTarget target = (ServiceDefTarget) CalculatorService;
		target.setServiceEntryPoint(GWT.getModuleBaseURL() + "org_lepthien_calculator_gwt/calculator");

		// Since RPC calls are asynchronous, we will need to wait for a response
		// after this test method returns. This line tells the test runner to wait
		// up to 10 seconds before timing out.
		delayTestFinish(10000);

		// Send a request to the server.
		BigInteger dividend = BigInteger.valueOf(10);
		CalculatorService.divide(dividend, new AsyncCallback<BigInteger>() {
			public void onFailure(Throwable caught) {
				// The request resulted in an unexpected error.
				fail("Request failure: " + caught.getMessage());
			}

			public void onSuccess(BigInteger result) {
				// Verify that the response is correct.
				assertEquals(-10, result.longValue());

				// Now that we have received a response, we need to tell the test runner
				// that the test is complete. You must call finishTest() after an
				// asynchronous test finishes successfully, or the test will time out.
				finishTest();
			}
		});
	}

	/**
	 * This test will send a request to the server attempting to divide by zero with
	 * the divide method in CalculatorService and verify the response.
	 */
	public void testCalculatorServiceDivideByZero() {
		// Create the service that we will test.
		CalculatorServiceAsync CalculatorService = GWT.create(CalculatorService.class);
		ServiceDefTarget target = (ServiceDefTarget) CalculatorService;
		target.setServiceEntryPoint(GWT.getModuleBaseURL() + "org_lepthien_calculator_gwt/calculator");

		// Since RPC calls are asynchronous, we will need to wait for a response
		// after this test method returns. This line tells the test runner to wait
		// up to 10 seconds before timing out.
		delayTestFinish(10000);

		// Send a request to the server.
		BigInteger dividend = BigInteger.ZERO;
		CalculatorService.divide(dividend, new AsyncCallback<BigInteger>() {
			public void onFailure(Throwable caught) {
				// The request resulted in an expected error.
			}

			public void onSuccess(BigInteger result) {
				// Verify that the response is correct.
				fail("ArithmeticException expected");

				// Now that we have received a response, we need to tell the test runner
				// that the test is complete. You must call finishTest() after an
				// asynchronous test finishes successfully, or the test will time out.
				finishTest();
			}
		});
	}

}
