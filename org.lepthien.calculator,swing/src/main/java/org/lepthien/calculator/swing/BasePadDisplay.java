package org.lepthien.calculator.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.text.NumberFormatter;

import org.lepthien.calculator.DisplayConfiguration;

public class BasePadDisplay extends Display {

	public class BaseChangeActionListener implements ActionListener {

		private int base;

		public BaseChangeActionListener(int i) {
			this.base = i;
		}

		public BaseChangeActionListener() {
			this.base = -1;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if (source instanceof JButton) {
				DisplayConfiguration.setBase(base);
			} else if (source instanceof JFormattedTextField) {
				JFormattedTextField fSource = (JFormattedTextField) source;
				this.base = ((Long) fSource.getValue()).intValue();
				DisplayConfiguration.setBase(base);
			}

			CalculatorManager manager = CalculatorManager.getManager();
			manager.redrawBase();

		}

	}

	private JFormattedTextField fld_base;

	public BasePadDisplay(JPanel panel) {
		super(panel);
	}

	@Override
	public void draw() {

		JButton btn_Octal = new JButton("oct");
		btn_Octal.addActionListener(new BaseChangeActionListener(8));
		panel.add(btn_Octal);

		JButton btn_Decimal = new JButton("dec");
		btn_Decimal.addActionListener(new BaseChangeActionListener(10));
		panel.add(btn_Decimal);

		JButton btn_Hexadecimal = new JButton("hex");
		btn_Hexadecimal.addActionListener(new BaseChangeActionListener(16));
		panel.add(btn_Hexadecimal);

		DecimalFormat format = new DecimalFormat("#000");
		NumberFormatter formatter = new NumberFormatter(format);
		fld_base = new JFormattedTextField(formatter);
		fld_base.setValue(DisplayConfiguration.getBase());
		fld_base.addActionListener(new BaseChangeActionListener());
		panel.add(fld_base);

	}
	
	public void redraw(){
		fld_base.setValue(DisplayConfiguration.getBase());
	}

}
