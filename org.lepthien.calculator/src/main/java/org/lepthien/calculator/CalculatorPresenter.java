package org.lepthien.calculator;

public interface CalculatorPresenter {
	
	public void setBase(int base);

	public void and();

	public void or();

	public void xor();

	public void add();

	public void subtract();

	public void multiply();

	public void divide();

	public void clear();
	
	public String formatRegister();
	
	public String formatValue();
	
	public void zeroValue();

}
