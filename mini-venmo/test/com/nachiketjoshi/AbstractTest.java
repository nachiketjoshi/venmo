package com.nachiketjoshi;

import junit.framework.TestCase;

public class AbstractTest extends TestCase {

	protected Venmo _app;

	public void setUp() {
		_app = new MiniVenmo();
	}
}
