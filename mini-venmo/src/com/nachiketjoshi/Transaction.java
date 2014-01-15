package com.nachiketjoshi;

public class Transaction {

	private User _actor;
	private User _target;
	private double _amount;
	private String _note;

	public Transaction(User actor, User target, double amount, String note) {
		_actor = actor;
		_target = target;
		_amount = amount;
		_note = note;
	}

	public User getActor() {
		return _actor;
	}

	public User getTarget() {
		return _target;
	}

	public double getAmount() {
		return _amount;
	}

	public String getNote() {
		return _note;
	}

}
