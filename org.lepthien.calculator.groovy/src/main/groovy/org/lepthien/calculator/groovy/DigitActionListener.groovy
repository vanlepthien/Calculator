package org.lepthien.calculator.groovy

import java.awt.event.ActionEvent
import java.awt.event.ActionListener;

class DigitActionListener implements ActionListener {

	int digit
	CalculatorInfo calc

	DigitActionListener(int digit, CalculatorInfo calc){
		this.digit=digit
		this.calc = calc
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		calc.addDigit(digit)
		calc.redisplay()
	}

}
