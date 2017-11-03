package org.lepthien.calculator.groovy

import groovy.beans.Bindable
import groovy.swing.*

import javax.swing.*
import javax.swing.border.*
import javax.swing.text.NumberFormatter;

import java.awt.*
import java.awt.BorderLayout
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener
import java.text.DecimalFormat;

import net.miginfocom.swing.MigLayout


def calc = new CalculatorInfo()

def swing = new SwingBuilder()

//JPanel calcPanel

def displayCalcPad = {
	def pad = swing."calcPanel"
	pad.removeAll()
	int sqrt = (int) Math.sqrt(calc.base);

	int cols = sqrt;
	int rows = calc.base / cols;
	int rem = calc.base - (cols * rows);

	System.out.println("cols " + cols);
	for(int nDigit = 0; nDigit <calc.base; nDigit++) {
		int rowIx = (calc.base - (nDigit + 1)) / cols;
		int colIx;
		if (nDigit >= rem) {
			colIx = (nDigit - rem) % cols;
		} else {
			colIx = nDigit;
		}
		String digit = calc.getBaseDigit(nDigit)
		String pos = "cell ${colIx} ${rowIx}"
		String tDigit = "b${digit}"
		JButton digitButton = new JButton(digit);
		pad.add(digitButton,pos)
		digitButton.addActionListener(new DigitActionListener(nDigit,calc));
	}
	calc.redisplay()
	pad.invalidate();
}

def frame = swing.frame(id:"top", defaultCloseOperation: JFrame.EXIT_ON_CLOSE, size:[200, 100]){
	vbox{
		hbox{
			hglue()
			Border border = BorderFactory.createLineBorder(Color.BLACK);
			Dimension displayDimension  = new Dimension(160,18)
			label(
					text:bind({calc.string}),
					border:border,
					horizontalAlignment: SwingConstants.RIGHT,
					horizontalTextPosition: SwingConstants.RIGHT,
					minimumSize:displayDimension
					)
			rigidArea( size:[60, 0] as Dimension)
		}
		rigidArea( size:[0, 10] as Dimension)

		hbox{
			rigidArea( size:[10, 0] as Dimension)
			button(
					text: "CE",
					action: action(
					name:"CE",
					closure:{
						calc.zeroValue();
						calc.redisplay()
					}))
			button(
					text: "C",
					action: action(
					name:"C",
					closure:{
						calc.clear()
						calc.redisplay()
					}))
			hglue()
		}
		rigidArea( size:[0, 10] as Dimension)
		hbox{
			rigidArea( size:[10, 0] as Dimension)
			button(
					text: "OCT",
					action: action(name:"OCT",closure:{
						calc.base = 8
						calc.redisplay()
						displayCalcPad()
						swing."baseField".value = calc.base
						swing."top".pack()
						swing."top".revalidate()
						swing."top".repaint()
					}))

			button(text: "DEC",
			action: action(name:"DEC",
			closure:{
				calc.base = 10
				calc.redisplay()
				displayCalcPad()
				swing."baseField".value = calc.base
				swing."top".pack()
				swing."top".revalidate()
				swing."top".repaint()
			}))
			button(text: "HEX",
			action: action(name:"HEX",
			closure:{
				calc.base = 16
				calc.redisplay()
				displayCalcPad()
				swing."baseField".value = calc.base
				swing."top".pack()
				swing."top".revalidate()
				swing."top".repaint()
			}))
			rigidArea( size:[10, 0] as Dimension)

			def baseSize = [36, 18] as Dimension

			DecimalFormat format = new DecimalFormat("#000");
			NumberFormatter formatter = new NumberFormatter(format);

			formattedTextField(
					id: "baseField",
					value: calc.base,
					formatter: formatter,
					columns:4,
					horizontalAlignment:SwingConstants.RIGHT,
					preferredSize: baseSize,
					maximumSize:baseSize,
					action: action(closure:{
						def source = it.getSource()
						def text = source.text
						println text
						int newBase = Integer.parseInt(text);
						calc.base = newBase <= 256 ? newBase : 256
						swing."baseField".value = calc.base
						calc.redisplay()
						displayCalcPad()
						swing."top".pack()
						swing."top".revalidate()
						swing."top".repaint()
					}))
			rigidArea( size:[10, 0] as Dimension)		
			hglue()
		}
		hbox{
			rigidArea( size:[10, 0] as Dimension)
			panel(id:"calcPanel",layout:new MigLayout(),alignmentY:Component.TOP_ALIGNMENT){
			}

			panel(alignmentY:Component.TOP_ALIGNMENT){
				def buttonSize = [18, 18] as Dimension
				def insets = [1, 1, 1, 1] as Insets
				vbox{
					rigidArea( size:[0, 10] as Dimension)

					button(text:"±",
					maximumSize: buttonSize,
					minimumSize: buttonSize,
					margin: insets,
					action: action(name:"±",closure:{
						calc.changeSign()
					}))
					button(text:"-",
					maximumSize: buttonSize,
					minimumSize: buttonSize,
					margin: insets,
					action: action(name:"-",closure:{
						calc.dash()
					}))
					button(text:"+",
					maximumSize: buttonSize,
					minimumSize: buttonSize,
					margin: insets,
					action: action(name:"+",closure:{
						calc.plus()
					}))
					button(text:"/",
					maximumSize: buttonSize,
					minimumSize: buttonSize,
					margin: insets,
					action: action(name:"/",closure:{
						calc.slash()
					}))
					button(text:"*",
					maximumSize: buttonSize,
					minimumSize: buttonSize,
					margin: insets,
					action: action(name:"*",closure:{
						calc.star()
					}))
					button(text:"=",
					maximumSize: buttonSize,
					minimumSize: buttonSize,
					margin: insets,
					action: action(name:"=",closure:{
						calc.equal()
					}))
				}
			}
		}
		displayCalcPad()
	}
}

swing.doLater{
	frame.pack()
	frame.setVisible(true)
}



