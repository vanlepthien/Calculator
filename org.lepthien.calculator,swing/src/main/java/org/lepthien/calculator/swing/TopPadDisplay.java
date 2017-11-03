package org.lepthien.calculator.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.lepthien.calculator.DisplayConfiguration;
import org.lepthien.calculator.NumericCalculator;
import org.lepthien.calculator.Operation;

public class TopPadDisplay extends Display {

	public TopPadDisplay(JPanel panel) {
		super(panel);
	}

	@Override
	public void draw() {
		CalculatorManager manager = CalculatorManager.getManager();
		JButton btn_ChangeSign = new JButton("�");
		btn_ChangeSign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NumericCalculator<?> calculator = manager.getCalculator();
				calculator.negateValue();
				manager.redrawDisplay();
			}

		});
		panel.add(btn_ChangeSign);

		JButton btn_ClearEntry = new JButton("CE");
		btn_ClearEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NumericCalculator<?> calculator = manager.getCalculator();
				calculator.zeroValue();
				manager.redrawDisplay();
			}
		});
		panel.add(btn_ClearEntry);

		JButton btn_Clear = new JButton("C");
		btn_Clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NumericCalculator<?> calculator = manager.getCalculator();
				calculator.zeroValue();
				calculator.zeroRegister();
				calculator.setLastOperation(Operation.NONE);
				manager.redrawDisplay();
			}
		});
		panel.add(btn_Clear);

		JButton btn_FixedFloat = new JButton("");

		if (DisplayConfiguration.isFixed()) {
			btn_FixedFloat.setText("Float");
		} else {
			btn_FixedFloat.setText("Fixed");
		}

		btn_FixedFloat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DisplayConfiguration.setFixed(!DisplayConfiguration.isFixed());
				if (DisplayConfiguration.isFixed()) {
					btn_FixedFloat.setText("Float");
				} else {
					btn_FixedFloat.setText("Fixed");
				}
				NumericCalculator<?> oldCalculator = manager.getCalculator();

				NumericCalculator<?> newCalculator = CalculatorFactory
						.newCalculator();

				manager.setCalculator(newCalculator);

				NumericCalculator<?> calculator = manager.getCalculator();

				calculator.setRegisterFromRegister(oldCalculator);
				calculator.setValueFromValue(oldCalculator);

				calculator.setLastOperation(Operation.NONE);
				manager.redrawBase();
			}

		});
		panel.add(btn_FixedFloat);

	}

}
