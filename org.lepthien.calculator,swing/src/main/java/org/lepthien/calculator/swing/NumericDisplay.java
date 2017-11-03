package org.lepthien.calculator.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import org.lepthien.calculator.FixedCalculator;
import org.lepthien.calculator.NumericCalculator;

public class NumericDisplay extends Display {

	static JLabel display = new JLabel();

	static final Border border = BorderFactory.createLineBorder(Color.BLACK);

	public NumericDisplay(JPanel panel) {
		super(panel);
	}

	public static NumericDisplay createDisplay(JPanel panel) {
		return new NumericDisplay(panel);
	}

	public void redraw() {
		NumericCalculator<?> calculator = CalculatorManager.getManager()
				.getCalculator();
		String formatted = calculator.formatValue();
		System.out.println("redraw " + formatted);
		Font font = display.getFont();
		FontMetrics metrics = display.getFontMetrics(font);
		int height = metrics.getHeight();
		int textWidth = Math.max(metrics.stringWidth(formatted),metrics.stringWidth("0")*20);
		System.out.println(textWidth);
		Dimension dimValue = new Dimension(textWidth + 2, height + 2);
		display.setMinimumSize(dimValue);
		display.setPreferredSize(dimValue);
		display.setText(formatted);
		display.validate();
	}

	public void draw() {
		FixedCalculator calculator = (FixedCalculator) CalculatorManager
				.getManager().getCalculator();
		display.setHorizontalAlignment(SwingConstants.RIGHT);
		display.setBorder(border);
		String formatted = calculator.formatValue();
		Font font = display.getFont();
		FontMetrics metrics = display.getFontMetrics(font);
		int height = metrics.getHeight();
		int textWidth = Math.max(metrics.stringWidth(formatted),metrics.stringWidth("0")*20);
		System.out.println(textWidth);
		Dimension dimValue = new Dimension(textWidth + 2, height + 2);
		display.setMinimumSize(dimValue);
		display.setPreferredSize(dimValue);
		display.setText(formatted);
		panel.add(display);
	}

}
