package org.lepthien.calculator.swing;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.lepthien.calculator.Input;
import org.lepthien.calculator.NumericCalculator;
import org.lepthien.calculator.Operation;

public class OperationsPadDisplay extends Display {

	public OperationsPadDisplay(JPanel panel) {
		super(panel);
	}

	@Override
	public void draw() {
		CalculatorManager manager = CalculatorManager.getManager();
		NumericCalculator<?> calculator = manager.getCalculator();
		Dimension buttonSize = new Dimension(18, 18);
		Insets buttonInsets = new Insets(1,1,1,1);

		JButton btn_asterisk = new JButton("*");
		btn_asterisk.setMaximumSize(buttonSize);
		btn_asterisk.setMinimumSize(buttonSize);
		btn_asterisk.setMargin(buttonInsets);
		btn_asterisk.addActionListener(new OperationActionListener(
				Operation.MULTIPLY, calculator));
		panel.add(btn_asterisk);

		JButton btn_slash = new JButton("/");
		btn_slash.setMaximumSize(buttonSize);
		btn_slash.setMinimumSize(buttonSize);
		btn_slash.setMargin(buttonInsets);
		btn_slash.addActionListener(new OperationActionListener(
				Operation.DIVIDE, calculator));
		panel.add(btn_slash);

		JButton btn_plus = new JButton("+");
		btn_plus.setMaximumSize(buttonSize);
		btn_plus.setMinimumSize(buttonSize);
		btn_plus.setMargin(buttonInsets);
		btn_plus.addActionListener(new OperationActionListener(Operation.ADD,
				calculator));
		panel.add(btn_plus);

		JButton btn_dash = new JButton("-");
		btn_dash.setMinimumSize(buttonSize);
		btn_dash.setMaximumSize(buttonSize);
		btn_dash.setMargin(buttonInsets);
		btn_dash.addActionListener(new OperationActionListener(
				Operation.SUBTRACT, calculator));
		panel.add(btn_dash);

		JButton btn_equals = new JButton("=");
		btn_equals.setMaximumSize(buttonSize);
		btn_equals.setMinimumSize(buttonSize);
		btn_equals.setMargin(buttonInsets);
		btn_equals.addActionListener(new OperationActionListener(
				Operation.EQUALS, calculator));
		panel.add(btn_equals);

	}

	class OperationActionListener implements ActionListener {

		private Operation operation;
		@SuppressWarnings("rawtypes")
		private NumericCalculator calculator;

		public OperationActionListener(Operation operation,
				@SuppressWarnings("rawtypes") NumericCalculator calculator) {
			this.operation = operation;
			this.calculator = calculator;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			CalculatorManager manager = CalculatorManager.getManager();
			calculator.setLastInput(Input.OPERATION);

			// System.out.println(operation);
			// System.out.println("   register:" + calculator.getRegister());
			// System.out.println("      value:" + calculator.getValue());
			// System.out.println("previous op:" +
			// calculator.getLastOperation());

			switch (calculator.getLastOperation()) {
			case ADD:
				calculator.add();
				break;
			case DIVIDE:
				try {
					calculator.divide();
				} catch (ArithmeticException x) {
					calculator.zeroRegister();
					calculator.zeroValue();
					calculator.setLastOperation(Operation.NONE);
					manager.redrawDisplay();
					return;
				}
				break;
			case MULTIPLY:
				calculator.multiply();
				break;
			case NONE:
				calculator.setRegisterToValue();
				break;
			case SUBTRACT:
				calculator.subtract();
				break;
			case EQUALS:
				break;
			case AND:
				calculator.and();
				break;
			case OR:
				calculator.or();
				break;
			case XOR:
				calculator.xor();
				break;
			}
			calculator.setValueToRegister();
			calculator.setLastOperation(operation);
			System.out.println("  register:" + calculator.formatRegister());
			System.out.println("     value:" + calculator.formatValue());
			manager.redrawDisplay();
		}
	}

}
