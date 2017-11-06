package org.lepthien.calculator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class InputValue {

	boolean positive = true;
	List<Item> items = new ArrayList<>();
	private boolean hasDecimal = false;
	private int base;

	public static final InputValue ZERO;
	
	static {
		ZERO = new InputValue();
		ZERO.addDigit(0);
	}

	public InputValue() {
		this.base = DisplayConfiguration.getBase();
	}

	public void addDigit(int digit) {
		items.add(Digit.getDigit(digit));
	}

	public boolean hasDecimal() {
		return hasDecimal;
	}

	public void addDecimal() {
		if (hasDecimal) {
			return;
		}
		items.add(new Decimal());
		hasDecimal = true;
	}

	public void negate() {
		positive = !positive;
	}

	public static InputValue valueOf(long v) {
		long base = DisplayConfiguration.getBase();
		InputValue value = new InputValue();
		value.positive = v >= 0;
		long work = Math.abs(v);
		Deque<Item> backwards = new ArrayDeque<>();
		while (work > 0) {
			// as work and base are > 0, we can use % instead of mod
			int rem = (int) (work % base);
			backwards.addFirst(Digit.getDigit(rem));
			work /= base;
		}
		value.items.addAll(backwards);
		return value;
	}

	public static InputValue valueOf(double v) {
		BigDecimal bd_v = BigDecimal.valueOf(v);
		return valueOf(bd_v);
	}

	public static InputValue valueOf(BigInteger v) {
		BigDecimal bd_v = new BigDecimal(v);
		return valueOf(bd_v);
	}

	public static InputValue valueOf(BigDecimal v) {
		BigDecimal base = BigDecimal.valueOf(DisplayConfiguration.getBase());
		InputValue value = new InputValue();
		value.positive = v.signum() >= 0;
		BigDecimal work = v.abs();
		if(work.compareTo(BigDecimal.ONE) < 0){
			value.items.add(Digit.getDigit(0));
		}
		Deque<Item> backwards = new ArrayDeque<>();
		BigDecimal bd_int = work.setScale(0, BigDecimal.ROUND_DOWN);
		BigDecimal bd_frac = work.remainder(BigDecimal.ONE);
		while (bd_int.compareTo(BigDecimal.ZERO) > 0) {
			BigDecimal rem = bd_int.remainder(base);
			backwards.addFirst(Digit.getDigit(rem.intValue()));
			bd_int = bd_int.divide(base, 0, BigDecimal.ROUND_DOWN);
		}
		backwards.addLast(new Decimal());
		int scale = DisplayConfiguration.getScale();

		while (scale > 0 && !bd_frac.equals(BigDecimal.ZERO)) {
			scale--;
			BigDecimal next = bd_frac.multiply(base);
			BigInteger intPart = next.toBigInteger();
			bd_frac = next.remainder(BigDecimal.ONE);
			backwards.addLast(Digit.getDigit(intPart.intValue()));
		}
		value.items.addAll(backwards);
		return value;
	}

	public static InputValue changeBase(InputValue old) {
		if (old.base == DisplayConfiguration.getBase()) {
			return old;
		}
		BigDecimal bd_value = old.toBigDecimal();
		InputValue replace = valueOf(bd_value);
		return replace;
	}

	public String format() {
		StringBuilder s = new StringBuilder();
		if (!positive) {
			s.append('-');
		}
		boolean hasDigit = false;
		for (Item item : items) {
			if (item instanceof Digit) {
				hasDigit = true;
				Digit dItem = (Digit) item;
				s.append(Utility.getBaseDigit(dItem.getValue(), base));
				continue;
			}
			if (item instanceof Decimal) {
				if(!hasDigit) {
					s.append(Utility.getBaseDigit(0, base));
					hasDigit = true;
				}
				s.append('.');
			}
		}
		return s.toString();
	}
	
	public String toString() {
		return format();
	}

	public BigDecimal toBigDecimal() {
		BigDecimal retVal = BigDecimal.ZERO;
		BigDecimal bd_base = BigDecimal.valueOf(base);
		boolean no_decimal = true;
		int scale = DisplayConfiguration.getScale();
		BigDecimal pow = BigDecimal.ONE;
		for (Item item : items) {
			if (item instanceof Digit) {
				Digit dItem = (Digit) item;
				BigDecimal digit = BigDecimal.valueOf(dItem.getValue());
				if (no_decimal) {
					retVal = retVal.multiply(bd_base);
					retVal = retVal.add(digit);
				} else {
					pow = pow.multiply(bd_base);
					BigDecimal delta = digit.divide(pow, scale, BigDecimal.ROUND_HALF_EVEN);
					retVal = retVal.add(delta);
				}
				continue;
			}
			if (item instanceof Decimal) {
				no_decimal = false;
			}
		}
		if (positive) {
			return retVal;
		}
		return retVal.negate();
	}

}

interface Item {

}

class Digit implements Item {
	static private Map<Integer,Digit> digits = new TreeMap<>();
	private int digit;

	private Digit(int digit) {
		this.digit = digit;
		digits.put(digit, this);
	}

	static public Digit getDigit(int i) {
		Digit d = digits.get(i);
		if(d == null) {
			d = new Digit(i);
		}
		return d;
	}

	int getValue() {
		return digit;
	}

}

class Decimal implements Item {
}
