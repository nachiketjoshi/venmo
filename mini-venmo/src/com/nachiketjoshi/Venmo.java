package com.nachiketjoshi;

public interface Venmo {

	void process(String line);

	void addUser(User user);

	User getUser(String name);

	void addCreditCard(User user, CreditCard card);

	void addPayment(User actor, User target, double amount, String note);

	void displayFeed(User user);

	void displayBalance(User user);

}
