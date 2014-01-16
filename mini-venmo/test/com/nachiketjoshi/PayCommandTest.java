package com.nachiketjoshi;

import com.nachiketjoshi.harness.AbstractTestCase;

public class PayCommandTest extends AbstractTestCase {

	public void testPayments() {
		_app.addUser("actor");
		_app.addUser("target");
		assertTrue(_app.checkUsersSize(2));

		_app.addPayment(new String[] { "actor2", "target", "$1", "credit check" });
		assertTrue(_app.getUser("target").checkFeedSize(0));

		_app.addPayment(new String[] { "actor", "target2", "$1", "credit check" });
		assertTrue(_app.getUser("actor").checkFeedSize(0));

		_app.addPayment(new String[] { "actor", "actor", "$1", "credit check" });
		assertTrue(_app.getUser("actor").checkFeedSize(0));

		_app.addPayment(new String[] { "actor", "target", "$1", "credit check" });
		assertTrue(_app.getUser("actor").checkFeedSize(0));

		_app.addCreditCard("actor", "4111111111111111");
		_app.addPayment(new String[] { "actor", "target", "1", "credit check" });
		assertTrue(_app.getUser("actor").checkFeedSize(0));

		_app.addPayment(new String[] { "actor", "target", "$1", "credit check" });
		assertTrue(_app.getUser("actor").checkFeedSize(1));
		assertTrue(_app.getUser("target").checkFeedSize(1));
		assertTrue(_app.getUser("target").getBalance() == 1.0);
	}
}
