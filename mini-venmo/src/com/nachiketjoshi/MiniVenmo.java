package com.nachiketjoshi;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.nachiketjoshi.commands.AddCommand;
import com.nachiketjoshi.commands.BalanceCommand;
import com.nachiketjoshi.commands.FeedCommand;
import com.nachiketjoshi.commands.PayCommand;
import com.nachiketjoshi.commands.UserCommand;
import com.nachiketjoshi.commands.Command;
import com.nachiketjoshi.commands.CommandSpec;

/**
 * @author Nachi
 * 
 */
public class MiniVenmo implements Venmo {

	private Map<String, Command> _commands = new HashMap<String, Command>();
	private Map<String, User> _users = new HashMap<String, User>();
	private Set<String> _cards = new HashSet<String>();

	public MiniVenmo() {
		registerCommand(new UserCommand(this));
		registerCommand(new AddCommand(this));
		registerCommand(new PayCommand(this));
		registerCommand(new FeedCommand(this));
		registerCommand(new BalanceCommand(this));
	}

	public void registerCommand(Command command) {
		String name = command.getCommandSpec().getName();
		if (_commands.containsKey(name)) {
			System.out.println("ERROR: duplicate command registered: " + name);
			return;
		}
		_commands.put(name, command);
	}

	public void process(String line) {
		line.trim();
		String[] tokens = line.split(" ");
		String commandName = tokens[0];
		Command command = _commands.get(commandName);
		if (command == null) {
			System.out.println("ERROR: command not recognized: " + commandName);
			return;
		}
		CommandSpec spec = command.getCommandSpec();
		int requiredCount = spec.getRequiredParameterCount();
		if (requiredCount > tokens.length - 1) {
			System.out.println("ERROR: invalid arguments");
			return;
		}
		String[] parameters = new String[tokens.length - 1];
		for (int i = 0; i < tokens.length - 1; i++) {
			parameters[i] = tokens[i + 1].trim();
		}
		command.process(parameters);
	}

	@Override
	public void addUser(User user) {
		if (_users.containsValue(user)) {
			System.out.println("ERROR: duplicate user: " + user);
			return;
		}
		_users.put(user.getName(), user);
	}

	@Override
	public User getUser(String name) {
		return _users.get(name);
	}

	@Override
	public void addCreditCard(User user, CreditCard card) {
		String number = card.getNumber();
		if (_cards.contains(number)) {
			System.out
					.println("ERROR: that card has already been added by another user, reported for fraud!");
			return;
		}
		user.setCreditCard(card);
		_cards.add(number);
	}

	@Override
	public void addPayment(User actor, User target, double amount, String note) {
		Transaction t = new Transaction(actor, target, amount, note);
		actor.addTransaction(t);
		target.incrementBalance(amount);
		target.addTransaction(t);
	}

	@Override
	public void displayFeed(User user) {
		List<Transaction> feed = user.getFeed();
		for (Transaction t : feed) {
			StringBuilder output = new StringBuilder("-- ");
			User maybeYou = t.getActor();
			if (maybeYou == user) {
				output.append("You");
			} else {
				output.append(maybeYou.getName());
			}
			output.append(" paid ");
			maybeYou = t.getTarget();
			if (maybeYou == user) {
				output.append("you");
			} else {
				output.append(maybeYou.getName());
			}
			output.append(" $" + String.format("%.2f", t.getAmount()));
			output.append(" for " + t.getNote());
			System.out.println(output.toString());
		}
	}

	@Override
	public void displayBalance(User user) {
		System.out.println("-- $" + String.format("%.2f", user.getBalance()));
	}
}
