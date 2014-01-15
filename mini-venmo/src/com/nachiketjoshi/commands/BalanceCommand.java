package com.nachiketjoshi.commands;

import com.nachiketjoshi.Venmo;
import com.nachiketjoshi.User;

public class BalanceCommand extends AbstractCommand {

	public BalanceCommand(Venmo app) {
		super(app, new CommandSpec("balance"));
		CommandSpec spec = getCommandSpec();
		spec.addRequiredParameter("<user>");
	}

	@Override
	public void process(String[] parameters) {
		try {
			String userName = parameters[0];
			User user = _app.getUser(userName);
			if (user == null) {
				throw new Exception("user not found: " + userName);
			}
			_app.displayBalance(user);
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}
}
