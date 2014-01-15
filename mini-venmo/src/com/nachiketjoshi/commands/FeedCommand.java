package com.nachiketjoshi.commands;

import com.nachiketjoshi.Venmo;

public class FeedCommand extends AbstractCommand {

	public FeedCommand(Venmo app) {
		super(app, new CommandSpec("feed"));
		CommandSpec spec = getCommandSpec();
		spec.addRequiredParameter("<user>");
	}

	@Override
	public void process(String[] parameters) {
		_app.displayFeed(parameters[0]);
	}
}
