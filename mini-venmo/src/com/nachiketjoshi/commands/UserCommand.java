package com.nachiketjoshi.commands;

import com.nachiketjoshi.Venmo;

public class UserCommand extends AbstractCommand {

	public UserCommand(Venmo app) {
		super(app, new CommandSpec("user"));
		CommandSpec spec = getCommandSpec();
		spec.addRequiredParameter("<user>");
	}

	@Override
	public void process(String[] parameters) {
		_app.addUser(parameters[0]);
	}

}
