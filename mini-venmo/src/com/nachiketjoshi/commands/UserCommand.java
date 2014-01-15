package com.nachiketjoshi.commands;

import com.nachiketjoshi.Venmo;
import com.nachiketjoshi.User;

public class UserCommand extends AbstractCommand {

	public UserCommand(Venmo app) {
		super(app, new CommandSpec("user"));
		CommandSpec spec = getCommandSpec();
		spec.addRequiredParameter("<user>");
	}

	@Override
	public void process(String[] parameters) {
		try {
			String name = parameters[0];
			validateName(name);
			_app.addUser(new User(name));
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}

	private void validateName(String name) throws Exception {
		if (!name.matches("[a-zA-Z0-9_-]{4,15}")) {
			throw new Exception("Invalid name: " + name);
		}
	}
}
