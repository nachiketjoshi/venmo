package com.nachiketjoshi.commands;

import com.nachiketjoshi.Venmo;

public abstract class AbstractCommand implements Command {

	protected CommandSpec _commandSpec;
	protected Venmo _app;

	protected AbstractCommand(Venmo app, CommandSpec spec) {
		_app = app;
		_commandSpec = spec;
	}

	public CommandSpec getCommandSpec() {
		return _commandSpec;
	}

}
