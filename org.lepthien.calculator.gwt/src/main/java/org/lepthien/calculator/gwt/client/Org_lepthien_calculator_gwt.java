package org.lepthien.calculator.gwt.client;

import java.math.BigInteger;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Org_lepthien_calculator_gwt implements EntryPoint {

	enum Operation {
		CLEAR, CLEAR_ENTRY, CHANGE_SIGN, ADD, SUBTRACT, MULTIPLY, DIVIDE, EQUALS, NONE
	}

	Operation lastOp = Operation.NONE;
	public boolean digitInput;

	public class OpButtonHandler implements ClickHandler {

		private Operation operation;

		public OpButtonHandler(Operation op) {
			this.operation = op;
		}

		@Override
		public void onClick(ClickEvent event) {

			switch (operation) {
			case CHANGE_SIGN:
				calculatorService.changeSign(new CalculatorAsyncCallback());
				lastOp = operation;
				break;
			case CLEAR:
				calculatorService.clear(new CalculatorAsyncCallback());
				lastOp = Operation.NONE;
				break;
			case CLEAR_ENTRY:
				value = BigInteger.ZERO;
				break;
			case ADD:
			case DIVIDE:
			case MULTIPLY:
			case SUBTRACT:
				execLastOp();
				lastOp = operation;
				digitInput = false;
				break;
			case EQUALS:
				execLastOp();
				lastOp = Operation.NONE;
				digitInput = false;
				break;
			default:
				break;
			}
			redisplay();
		}

		private void execLastOp() {
			switch (lastOp) {
			case ADD:
				calculatorService.add(value, new CalculatorAsyncCallback());
				break;
			case DIVIDE:
				calculatorService.divide(value, new CalculatorAsyncCallback());
				break;
			case MULTIPLY:
				calculatorService
						.multiply(value, new CalculatorAsyncCallback());
				break;
			case SUBTRACT:
				calculatorService.subtract(value, new CalculatorAsyncCallback());
				break;
			default:
				calculatorService.setRegister(value,
						new CalculatorAsyncCallback());
				break;
			}
		}
	}

	class baseChangeButtonHandler implements ClickHandler {

		private int buttonBase;

		baseChangeButtonHandler(int newBase) {
			this.buttonBase = newBase;
		}

		@Override
		public void onClick(ClickEvent event) {
			if (base == buttonBase) {
				return;
			}
			base = buttonBase;
			redisplay();
		}

	}

	class DigitButton extends Button {
		private int digit;

		DigitButton(int digit) {
			super();
			this.digit = digit;
			setText(getDigitText(digit));

			this.addClickHandler(new NumericPadButtonHandler());
		}

		public int getDigit() {
			return digit;
		}
	}

	public class NumericPadButtonHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			Object source = event.getSource();
			if (source instanceof DigitButton) {
				DigitButton button = (DigitButton) source;
				if (!digitInput) {
					value = BigInteger.ZERO;
				}
				addDigit(button.getDigit());
				digitInput = true;
			}
		}

	}

	private int base = 10;
	final TextBox baseField = new TextBox();
	final String digits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz@$";

	private CalculatorServiceAsync calculatorService = GWT
			.create(CalculatorService.class);

	final Grid numericPadTable = new Grid();

	final DialogBox box = new DialogBox();

	private BigInteger value = BigInteger.ZERO;

	final Label valueLabel = new Label();

	void addDigit(int digit) {
		switch (value.signum()) {
		case -1:
			value = value.multiply(BigInteger.valueOf(base)).subtract(
					BigInteger.valueOf(digit));
			break;
		case 0:
			value = BigInteger.valueOf(digit);
			break;
		case 1:
			value = value.multiply(BigInteger.valueOf(base)).add(
					BigInteger.valueOf(digit));
			break;
		}
		valueLabel.setText(getDigits(value));

	}

	void createNumericPad() {
		Grid grid = numericPadTable;
		int sqrt = (int) Math.sqrt(base);

		int cols = sqrt;
		int rows = base / cols;
		int rem = base - (cols * rows);

		if (rem > 0) {
			rows++;
		}

		grid.clear();
		grid.resize(rows, cols);

		for (int nDigit = 0; nDigit < base; nDigit++) {
			int row = (base - (nDigit + 1)) / cols;
			int col;
			if (nDigit >= rem) {
				col = (nDigit - rem) % cols;
			} else {
				col = nDigit;
			}

			DigitButton buttonDigit = new DigitButton(nDigit);
			buttonDigit.addStyleName("digitButton");
			grid.setWidget(row, col, buttonDigit);
		}

	}

	String getDigits(BigInteger number) {
		StringBuilder s = new StringBuilder();
		BigInteger bBase = BigInteger.valueOf(base);
		int sign = number.signum();
		BigInteger next = number.abs();

		if (sign == 0) {
			return "0";
		}

		while (next.signum() > 0) {
			BigInteger div = next.divide(bBase);
			BigInteger rem = next.remainder(bBase);
			int intRem = rem.intValue();
			String digit = getDigitText(intRem);
			s.insert(0, digit);
			next = div;
		}
		if (sign < 0) {
			s.insert(0, "-");
		}
		return s.toString();
	}

	public String getDigitText(int digit) {
		if (base <= 64) {
			if (digit < base) {
				return digits.substring(digit, digit + 1);
			}
		}
		String hexDigit = Integer.toHexString(digit);
		if (hexDigit.length() < 2) {
			hexDigit = "0" + hexDigit;
		}
		return "'" + hexDigit + "'";
	}

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final Button octButton = new Button("OCT");
		final Button decButton = new Button("DEC");
		final Button hexButton = new Button("HEX");
		baseField.setText(Integer.toString(base));
		valueLabel.setText(getDigits(value));
		final Button baseChangeButton = new Button("Change\nBase");
		final Button clearButton = new Button("C");
		final Button clearEntryButton = new Button("CE");
		final Button changeSignButton = new Button(
				new Character((char) 0xb1).toString()); //
		final Button addButton = new Button("+");
		final Button subtractButton = new Button("-");
		final Button multiplyButton = new Button("*");
		final Button divideButton = new Button("/");
		final Button equalsButton = new Button("=");

		// We can add style names to widgets
		octButton.addStyleName("baseButton");
		decButton.addStyleName("baseButton");
		hexButton.addStyleName("baseButton");
		valueLabel.addStyleName("valueStyle");
		baseField.addStyleName("baseField");
		baseChangeButton.addStyleName("baseChangeButton");

		changeSignButton.addStyleName("opButton");
		addButton.addStyleName("opButton");
		subtractButton.addStyleName("opButton");
		multiplyButton.addStyleName("opButton");
		divideButton.addStyleName("opButton");
		equalsButton.addStyleName("opButton");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("valueLabelContainer").add(valueLabel);
		RootPanel.get("octButtonContainer").add(octButton);
		RootPanel.get("decButtonContainer").add(decButton);
		RootPanel.get("hexButtonContainer").add(hexButton);
		RootPanel.get("baseFieldContainer").add(baseField);
		RootPanel.get("baseChangeButtonContainer").add(baseChangeButton);
		RootPanel.get("clearButtonContainer").add(clearButton);
		RootPanel.get("clearEntryButtonContainer").add(clearEntryButton);
		RootPanel.get("changeSignButtonContainer").add(changeSignButton);
		RootPanel.get("addButtonContainer").add(addButton);
		RootPanel.get("subtractButtonContainer").add(subtractButton);
		RootPanel.get("multiplyButtonContainer").add(multiplyButton);
		RootPanel.get("divideButtonContainer").add(divideButton);
		RootPanel.get("equalsButtonContainer").add(equalsButton);
		RootPanel.get("numericPadContainer").add(numericPadTable);

		// Focus the cursor on the name field when the app loads
		decButton.setFocus(true);

		octButton.addClickHandler(new baseChangeButtonHandler(8));
		decButton.addClickHandler(new baseChangeButtonHandler(10));
		hexButton.addClickHandler(new baseChangeButtonHandler(16));

		changeSignButton.addClickHandler(new OpButtonHandler(
				Operation.CHANGE_SIGN));
		addButton.addClickHandler(new OpButtonHandler(Operation.ADD));
		subtractButton.addClickHandler(new OpButtonHandler(Operation.SUBTRACT));
		multiplyButton.addClickHandler(new OpButtonHandler(Operation.MULTIPLY));
		divideButton.addClickHandler(new OpButtonHandler(Operation.DIVIDE));
		equalsButton.addClickHandler(new OpButtonHandler(Operation.EQUALS));
		clearButton.addClickHandler(new OpButtonHandler(Operation.CLEAR));
		clearEntryButton.addClickHandler(new OpButtonHandler(
				Operation.CLEAR_ENTRY));

		createNumericPad();

		baseField.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				String stringBase = event.getValue();
				System.out.println("baseChanged:" + stringBase);
				try {
					base = Integer.parseInt(stringBase);
				} catch (Exception x) {
					Object source = event.getSource();
					if (source instanceof TextBox) {
						box.clear();
						box.setText("Error setting base:");
						box.setAnimationEnabled(true);
						Button closeButton = new Button("Close");
						VerticalPanel dialogVPanel = new VerticalPanel();
						dialogVPanel.addStyleName("dialogVPanel");
						dialogVPanel.add(new HTML(
								"<p>Attempt to set base to invalid value <b>"
										+ stringBase
										+ "</b> failed, old base value <b>"
										+ base + "</b> restored"));
						dialogVPanel
								.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
						dialogVPanel.add(closeButton);
						box.setWidget(dialogVPanel);

						// Add a handler to close the DialogBox
						closeButton.addClickHandler(new ClickHandler() {
							public void onClick(ClickEvent dialogEvent) {
								box.hide();
							}
						});
					}
				}
				redisplay();
			}

		});

	}

	void redisplay() {
		createNumericPad();
		valueLabel.setText(getDigits(value));
		baseField.setText(Integer.toString(base));
	}

	class CalculatorAsyncCallback implements AsyncCallback<BigInteger> {

		@Override
		public void onFailure(Throwable caught) {
			box.clear();
			box.setText("Input Error");
			box.setAnimationEnabled(true);
			Button closeButton = new Button("Close");
			VerticalPanel dialogVPanel = new VerticalPanel();
			dialogVPanel.addStyleName("dialogVPanel");
			dialogVPanel.add(new HTML("<p>Error" + caught.toString()
					+ "<b/>Old value <b>" + value + "</b> kept"));
			dialogVPanel
					.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
			dialogVPanel.add(closeButton);
			box.setWidget(dialogVPanel);

			// Add a handler to close the DialogBox
			closeButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					box.hide();
				}
			});
		}

		@Override
		public void onSuccess(BigInteger result) {
			value = result;
			redisplay();
		}

	}

}
