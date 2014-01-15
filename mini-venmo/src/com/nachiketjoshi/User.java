package com.nachiketjoshi;

import java.util.ArrayList;
import java.util.List;

public class User {

	private String _name;
	private CreditCard _card;
	private double _balance;
	private final List<Transaction> _feed = new ArrayList<Transaction>();

	public User(String name) {
		_name = name;
	}

	public String getName() {
		return _name;
	}

	public void setCreditCard(CreditCard card) {
		_card = card;
	}

	public CreditCard getCard() {
		return _card;
	}

	public void incrementBalance(double balance) {
		_balance += balance;
	}

	public double getBalance() {
		return _balance;
	}

	public void addTransaction(Transaction t) {
		_feed.add(t);
	}

	public List<Transaction> getFeed() {
		return _feed;
	}

}
