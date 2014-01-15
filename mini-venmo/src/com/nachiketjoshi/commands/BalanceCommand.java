package com.nachiketjoshi.commands;

import com.nachiketjoshi.Venmo;

public class BalanceCommand extends AbstractCommand {

	public BalanceCommand(Venmo app) {
		super(app, new CommandSpec("balance"));
		CommandSpec spec = getCommandSpec();
		spec.addRequiredParameter("<user>");
	}

	@Override
	public void process(String[] parameters) {
		_app.displayBalance(parameters[0]);
	}
}
