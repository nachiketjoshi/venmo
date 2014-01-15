package com.nachiketjoshi.commands;

import com.nachiketjoshi.Venmo;

public class AddCommand extends AbstractCommand {

	public AddCommand(Venmo app) {
		super(app, new CommandSpec("add"));
		CommandSpec spec = getCommandSpec();
		spec.addRequiredParameter("<user>");
		spec.addRequiredParameter("<card_number>");
	}

	@Override
	public void process(String[] parameters) {
		_app.addCreditCard(parameters[0], parameters[1]);
	}
}
