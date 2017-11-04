package org.lepthien.calculator.groovy

import groovy.beans.Bindable

import javax.swing.JButton

import org.lepthien.calculator.*

public class CalculatorInfo implements CalculatorPresenter{

	@Bindable
	int base = DisplayConfiguration.base
	@Bindable
	def string

	def lastInput = "INIT"

	def lastOp = ""

	NumericCalculatorFactory factory = new NumericCalculatorFactory()
	NumericCalculator  calcModel = factory.newCalculatorModel(this)

	def redisplay = {
		string = formatValue()
		println "value ${string}"
	}

	def addDigit = {
		println it.getClass()
		if(lastInput == "OPERATION") {
			zeroValue()
		}
		int digit = it
		addDigit(it)
		lastInput = "DIGIT"
		redisplay()
	}

	def getBaseDigit = {int digit -> calcModel.getBaseDigit(digit)}

	def changeSign = {
		calcModel.negateValue()
		redisplay()
	}

	def plus = {
		lastInput = "OPERATION"
		performOp()
		lastOp = "+"
	}

	def dash = {
		lastInput = "OPERATION"
		if(lastOp != ""){
			performOp()
		}
		lastOp = "-"
	}

	def star = {
		lastInput = "OPERATION"
		performOp()
		lastOp = "*"
	}

	def slash = {
		lastInput = "OPERATION"
		performOp()
		lastOp = "/"
	}
	def equal = {
		lastInput = "OPERATION"
		performOp()
		lastOp = "="
	}

	def performOp = {
		println "lastOp = ${lastOp}"
		switch (lastOp){
			case "+":
				add()
				calcModel.setValueToRegister()
				redisplay()
				break
			case "-":
				subtract()
				calcModel.setValueToRegister()
				redisplay()
				break
			case "*":
				multiply()
				calcModel.setValueToRegister()
				redisplay()
				break
			case "/":
				divide()
				calcModel.setValueToRegister()
				redisplay()
				break
			case "=":
				calcModel.setValueToRegister()
				redisplay()
				break
			default:
				println "Unknown lastOp = ${lastOp}"
		}
		calcModel.setRegisterToValue()
	}

	def formatValue = {calcModel.formatValue()}
	@Override
	public void and() {
		calcModel.and()
	}
	@Override
	public void or() {
		calcModel.or()
	}
	@Override
	public void add() {
		calcModel.add()
	}
	@Override
	public void subtract() {
		calcModel.subtract()
	}
	@Override
	public void multiply() {
		calcModel.multiply()
	}
	@Override
	public void divide() {
		calcModel.divide()
	}
	@Override
	public void clear() {
		calcModel.and()
	}
	@Override
	public String formatRegister() {
		return calcModel.formatRegister();
	}
	@Override
	public String formatValue() {
		return calcModel.formatValue();
	}
	@Override
	public void zeroValue() {
		calcModel.zeroValue();
	}
	@Override
	public void xor() {
		calcModel.xor()
	}
	@Override
	public void addDigit(int digit) {
		calcModel.addDigit(digit);
	}
	@Override
	public void setDecimal() {
		calcModel.setDecimal();
	}
	@Override
	public void negateValue() {
		calcModel.negateValue()
	}
}