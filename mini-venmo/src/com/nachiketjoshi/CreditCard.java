package com.nachiketjoshi;

public class CreditCard {

	private final String _number;

	public CreditCard(String number) {
		_number = number;
	}

	public String getNumber() {
		return _number;
	}

	public String toString() {
		return "XXXX-XXXX-XXXX-" + _number.substring(12);
	}
}
