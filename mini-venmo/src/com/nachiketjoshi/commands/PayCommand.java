package com.nachiketjoshi.commands;

import com.nachiketjoshi.Venmo;

public class PayCommand extends AbstractCommand {

	public PayCommand(Venmo app) {
		super(app, new CommandSpec("pay"));
		CommandSpec spec = getCommandSpec();
		spec.addRequiredParameter("<actor>");
		spec.addRequiredParameter("<target>");
		spec.addRequiredParameter("<amount>");
		spec.addRequiredParameter("<note>");
	}

	@Override
	public void process(String[] parameters) {
		_app.addPayment(parameters);
	}
}
