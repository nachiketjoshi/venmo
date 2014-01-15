package com.nachiketjoshi;

public class UserCommandTest extends AbstractTest {

	public void testUserNames() {
		_app.process("user a");
		assertTrue(_app.getUser("a") == null);

		_app.process("user abc;");
		assertTrue(_app.getUser("abc;") == null);

		_app.process("user abcdefghijklmnop");
		assertTrue(_app.getUser("abcdefghijklmnop") == null);

		_app.process("user venm0");
		assertTrue(_app.getUser("venm0") != null);
	}

}
