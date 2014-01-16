package com.nachiketjoshi;

import com.nachiketjoshi.harness.AbstractTestCase;

public class UserCommandTest extends AbstractTestCase {

	public void testUserNames() {
		_app.process("user a");
		assertFalse(_app.checkUserExists("a"));
		assertTrue(_app.checkUsersSize(0));

		_app.process("user abc;");
		assertFalse(_app.checkUserExists("abc;"));
		assertTrue(_app.checkUsersSize(0));

		_app.process("user abcdefghijklmnop");
		assertFalse(_app.checkUserExists("abcdefghijklmnop"));
		assertTrue(_app.checkUsersSize(0));

		_app.process("user venm0");
		assertTrue(_app.checkUserExists("venm0"));
		assertTrue(_app.checkUsersSize(1));
		_app.process("user venm0");
		assertTrue(_app.checkUsersSize(1));
	}

}
