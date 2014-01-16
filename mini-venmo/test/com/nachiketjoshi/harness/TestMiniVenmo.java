package com.nachiketjoshi.harness;

import com.nachiketjoshi.MiniVenmo;

public class TestMiniVenmo extends MiniVenmo {

	public boolean checkUserExists(String name) {
		return _users.containsKey(name);
	}

	public boolean checkUsersSize(int n) {
		return _users.size() == n;
	}

	public boolean checkCardExists(String digits) {
		return _cards.contains(digits);
	}

	public boolean checkCardsSize(int n) {
		return _cards.size() == n;
	}

	@Override
	public void addUser(String name) {
		if (!name.matches("[a-zA-Z0-9_-]{4,15}")) {
			System.out.println("ERROR: Invalid name: " + name);
			return;
		}
		if (_users.containsKey(name)) {
			System.out.println("ERROR: duplicate user: " + name);
			return;
		}
		_users.put(name, new TestUser(name));
	}

	@Override
	public TestUser getUser(String name) {
		return (TestUser) _users.get(name);
	}

}
