package org.lepthien.calculator.gwt;

import org.lepthien.calculator.gwt.client.Org_lepthien_calculator_gwtTest;
import com.google.gwt.junit.tools.GWTTestSuite;
import junit.framework.Test;
import junit.framework.TestSuite;

public class Org_lepthien_calculator_gwtSuite extends GWTTestSuite {
	public static Test suite() {
		TestSuite suite = new TestSuite("Tests for Org_lepthien_calculator_gwt");
		suite.addTestSuite(Org_lepthien_calculator_gwtTest.class);
		return suite;
	}
}
