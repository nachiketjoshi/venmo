package com.nachiketjoshi.harness;

import junit.framework.TestCase;

public class AbstractTestCase extends TestCase {

	protected TestMiniVenmo _app;

	public void setUp() {
		_app = new TestMiniVenmo();
	}
}
