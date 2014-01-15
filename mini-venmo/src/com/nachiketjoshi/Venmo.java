package com.nachiketjoshi;

public interface Venmo {

	void process(String line);

	void addUser(String name);

	void addCreditCard(String name, String digits);

	void addPayment(String[] parameters);

	void displayFeed(String name);

	void displayBalance(String name);

}
