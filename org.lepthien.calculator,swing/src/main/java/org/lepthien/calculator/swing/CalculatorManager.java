package org.lepthien.calculator.swing;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.lepthien.calculator.DisplayConfiguration;
import org.lepthien.calculator.NumericCalculator;

import net.miginfocom.swing.MigLayout;

public class CalculatorManager {

	static private CalculatorManager manager;
	private JFrame frame;
	private BasePadDisplay basePadDisplay;
	private NumericDisplay valueDisplay;
	private Display topPadDisplay;
	private NumericPadDisplay numericPadDisplay;
	private OperationsPadDisplay operationsPadDisplay;
	private NumericCalculator<?> calculator;
	private JPanel panelValue;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new CalculatorManager();
					manager.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CalculatorManager() {
		manager = this;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 200, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(
				new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

		panelValue = new JPanel();
		frame.getContentPane().add(panelValue);

		JPanel panelTopPanel = new JPanel();
		frame.getContentPane().add(panelTopPanel);

		panelTopPanel.setLayout(new BoxLayout(panelTopPanel, BoxLayout.Y_AXIS));

		JPanel panelBasePad = new JPanel();
		panelTopPanel.add(panelBasePad);

		JPanel panelTopPad = new JPanel();
		panelTopPanel.add(panelTopPad);

		JPanel panelMidPanel = new JPanel();
		frame.getContentPane().add(panelMidPanel);

		panelMidPanel.setLayout(new BoxLayout(panelMidPanel, BoxLayout.X_AXIS));

		JPanel panelNumericPad = new JPanel();
		panelNumericPad.setAlignmentY(Component.TOP_ALIGNMENT);
		panelMidPanel.add(panelNumericPad);
		panelNumericPad.setLayout(new MigLayout("", "[]", "[]"));

		JPanel panelOpsPad = new JPanel();
		panelOpsPad.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelMidPanel.add(panelOpsPad);
		panelOpsPad.setLayout(new BoxLayout(panelOpsPad, BoxLayout.Y_AXIS));

		DisplayConfiguration.setBase(10);
		DisplayConfiguration.setFixed(true);

		valueDisplay = NumericDisplay.createDisplay(panelValue);

		topPadDisplay = new TopPadDisplay(panelTopPad);

		basePadDisplay = new BasePadDisplay(panelBasePad);

		numericPadDisplay = new NumericPadDisplay(panelNumericPad);

		operationsPadDisplay = new OperationsPadDisplay(panelOpsPad);

		setCalculator(CalculatorFactory.newCalculator());

		valueDisplay.draw();
		topPadDisplay.draw();
		basePadDisplay.draw();
		numericPadDisplay.draw();
		operationsPadDisplay.draw();

		frame.pack();
		frame.repaint();
	}

	public void redrawDisplay() {
		valueDisplay.redraw();
		frame.pack();
		frame.repaint();
	}

	public void redrawBase() {
		valueDisplay.redraw();
		numericPadDisplay.redraw();
		basePadDisplay.redraw();
		frame.pack();
		frame.repaint();
	}

	public NumericCalculator<?> getCalculator() {
		return calculator;
	}

	public void setCalculator(NumericCalculator<?> calculator) {
		this.calculator = calculator;
	}

	public static CalculatorManager getManager() {
		return manager;
	}

}
