package com.nachiketjoshi.harness;

import com.nachiketjoshi.User;

public class TestUser extends User {

	public TestUser(String name) {
		super(name);
	}

	public boolean checkFeedSize(int n) {
		return _feed.size() == n;
	}

}
