package org.lepthien.calculator;

public class DisplayConfiguration {
	private static DisplayConfiguration configuration;
	private int scale = 10;
	private int base;
	private boolean fixed;

	static {
		configuration = new DisplayConfiguration();
	}

	private DisplayConfiguration() {
		this(10, true);
	}

	private DisplayConfiguration(int base, boolean fixed) {
		this.base = base;
		this.fixed = fixed;
	}

	public static int getBase() {
		return configuration.base;
	}

	public static boolean isFixed() {
		return configuration.fixed;
	}

	public static void setBase(int base) {
		configuration.base = base;
	}

	public static void setFixed(boolean fixed) {
		configuration.fixed = fixed;
	}

	public static int getScale() {
		return configuration.scale;
	}

	public static void setScale(int scale) {
		configuration.scale = scale;
		;
	}

}
