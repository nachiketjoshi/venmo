package com.nachiketjoshi.commands;

public interface Command {

	CommandSpec getCommandSpec();

	void process(String[] parameters);
}
