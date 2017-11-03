package org.lepthien.calculator.swing;

import javax.swing.JPanel;

public abstract class Display {

	JPanel panel;

	public Display(JPanel panel) {
		this.panel = panel;
	}

	public abstract void draw();

}
