package com.nachiketjoshi.commands;

import java.util.ArrayList;
import java.util.List;

public class CommandSpec {

	private String _commandName;
	private List<String> _requiredParameters = new ArrayList<String>();

	public CommandSpec(String name) {
		_commandName = name;
	}

	public String getName() {
		return _commandName;
	}

	public void addRequiredParameter(String param) {
		_requiredParameters.add(param);
	}

	public int getRequiredParameterCount() {
		return _requiredParameters.size();
	}

}
