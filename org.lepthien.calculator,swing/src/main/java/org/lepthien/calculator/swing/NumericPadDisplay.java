package org.lepthien.calculator.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.lepthien.calculator.DisplayConfiguration;
import org.lepthien.calculator.NumericCalculator;
import org.lepthien.calculator.Utility;

public class NumericPadDisplay extends Display {

	public class DecimalActionListener implements ActionListener {

		private NumericCalculator<?> calculator;

		public DecimalActionListener(NumericCalculator<?> calculator) {
			this.calculator = calculator;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			CalculatorManager manager = CalculatorManager.getManager();
			calculator.setDecimal();
			manager.redrawDisplay();
		}

	}

	class DigitActionListener implements ActionListener {

		private int digit;
		private NumericCalculator<? extends Number> calculator;

		DigitActionListener(int i, int base,
				NumericCalculator<? extends Number> calculator) {
			this.digit = i;
			this.calculator = calculator;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			calculator.addDigit(digit);
			CalculatorManager manager = CalculatorManager.getManager();
			manager.redrawDisplay();
		}

	}

	private int cols;
	private int rows;
	private int rem;

	public NumericPadDisplay(JPanel panel) {
		super(panel);
	}

	@Override
	public void draw() {

		panel.removeAll();

		int base = DisplayConfiguration.getBase();
		boolean fixed = DisplayConfiguration.isFixed();
		NumericCalculator<?> calculator = CalculatorManager.getManager()
				.getCalculator();

		int nBase = fixed ? base : base + 1;
		int sqrt = (int) Math.sqrt(nBase);

		this.cols = sqrt;
		this.rows = base / cols;
		this.rem = base - (cols * rows);

		System.out.println("cols " + cols);

		for (int number = 0; number < base; number++) {
			int rowIx = (base - (number + 1)) / cols;
			int colIx;
			if (number >= rem) {
				colIx = (number - rem) % cols;
			} else {
				colIx = number;
			}
			// System.out.println("number " + number);
			// System.out.println(" rowIx " + rowIx + "; colIx " + colIx);
			// System.out.println(" base " + base + "; number " + number);
			String string = Utility.getNumericString(number, base);
			JButton button = new JButton(string);
			button.addActionListener(new DigitActionListener(number, base,
					calculator));
			String stringPosition = Utility.getStringPosition(colIx, rowIx);
			System.out.println("number " + number + " :position "
					+ stringPosition);
			panel.add(button, "cell " + stringPosition);
		}
		if (!fixed) {
			int rowIx = base / cols;
			int colIx;
			if (base >= rem) {
				colIx = (nBase - rem) % cols;
			} else {
				colIx = nBase;
			}
			String stringPosition = Utility.getStringPosition(colIx, rowIx);

			JButton decimalButton = new JButton(".");
			decimalButton.addActionListener(new DecimalActionListener(
					calculator));
			System.out.println("decimal :position " + stringPosition);
			panel.add(decimalButton, "cell " + stringPosition);
		}
	}

	public void redraw() {
		draw();
	}

}
