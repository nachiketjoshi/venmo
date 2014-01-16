package com.nachiketjoshi;

import com.nachiketjoshi.harness.AbstractTestCase;

public class AddCommandTest extends AbstractTestCase {

	public void testCreditCards() {
		_app.addCreditCard("test", "1234123412341234");
		assertTrue(_app.checkUsersSize(0));
		assertTrue(_app.checkCardsSize(0));

		_app.addUser("test");
		assertTrue(_app.checkUsersSize(1));
		_app.addCreditCard("test", "1234567890123456");
		assertTrue(_app.checkUsersSize(1));
		assertTrue(_app.checkCardsSize(0));

		_app.addCreditCard("test", "4111111111111111");
		assertTrue(_app.checkCardsSize(1));
		_app.addCreditCard("test", "5454545454545454");
		assertTrue(_app.checkCardsSize(1));

		_app.addUser("test2");
		assertTrue(_app.checkUsersSize(2));
		_app.addCreditCard("test2", "4111111111111111");
		assertTrue(_app.checkCardsSize(1));
	}

}
