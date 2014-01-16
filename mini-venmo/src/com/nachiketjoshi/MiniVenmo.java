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

	protected Map<String, Command> _commands = new HashMap<String, Command>();
	protected Map<String, User> _users = new HashMap<String, User>();
	protected Set<String> _cards = new HashSet<String>();

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
	public void addUser(String name) {
		if (!name.matches("[a-zA-Z0-9_-]{4,15}")) {
			System.out.println("ERROR: Invalid name: " + name);
			return;
		}
		if (_users.containsKey(name)) {
			System.out.println("ERROR: duplicate user: " + name);
			return;
		}
		_users.put(name, new User(name));
	}

	public User getUser(String name) {
		return _users.get(name);
	}

	@Override
	public void addCreditCard(String name, String digits) {
		User user = getUser(name);
		if (user == null) {
			System.out.println("ERROR: user not found: " + name);
			return;
		}
		if (user.getCard() != null) {
			System.out
					.println("ERROR: this user already has a valid credit card");
			return;
		}
		if (!digits
				.matches("\\d\\d\\d\\d([-\\s])?\\d\\d\\d\\d([-\\s])?\\d\\d\\d\\d([-\\s])?\\d\\d\\d\\d")) {
			System.out.println("ERROR: this card is invalid: " + digits);
			return;
		}

		digits = digits.replaceAll("[ -]", "");
		/* LUHN-10: http://www.chriswareham.demon.co.uk/software/Luhn.java */
		int sum = 0;
		boolean alternate = false;
		for (int i = digits.length() - 1; i >= 0; i--) {
			int n = Integer.parseInt(digits.substring(i, i + 1));
			if (alternate) {
				n *= 2;
				if (n > 9) {
					n = (n % 10) + 1;
				}
			}
			sum += n;
			alternate = !alternate;
		}
		if (sum % 10 != 0) {
			System.out.println("ERROR: this card is invalid: " + digits);
			return;
		}
		CreditCard card = new CreditCard(digits);
		if (_cards.contains(digits)) {
			System.out
					.println("ERROR: that card has already been added by another user, reported for fraud!");
			return;
		}
		user.setCreditCard(card);
		_cards.add(digits);
	}

	@Override
	public void addPayment(String[] parameters) {
		String actorName = parameters[0];
		User actor = getUser(actorName);
		if (actor == null) {
			System.out.println("ERROR: actor not found: " + actorName);
			return;
		}
		String targetName = parameters[1];
		User target = getUser(targetName);
		if (target == null) {
			System.out.println("ERROR: target not found: " + targetName);
			return;
		}
		if (actor == target) {
			System.out.println("ERROR: users cannot pay themselves");
			return;
		}
		if (actor.getCard() == null) {
			System.out.println("ERROR: this user does not have a credit card");
			return;
		}
		String dollars = parameters[2];
		if (dollars.charAt(0) != '$') {
			System.out.println("ERROR: Invalid amount: " + dollars);
			return;
		}
		double amount = Double.parseDouble(dollars.substring(1));
		StringBuilder noteBuilder = new StringBuilder();
		for (int i = 3; i < parameters.length; i++) {
			noteBuilder.append(parameters[i]);
			noteBuilder.append(" ");
		}
		Transaction t = new Transaction(actor, target, amount, noteBuilder
				.toString().trim());
		actor.addTransaction(t);
		target.incrementBalance(amount);
		target.addTransaction(t);
	}

	@Override
	public void displayFeed(String name) {
		User user = getUser(name);
		if (user == null) {
			System.out.println("ERROR: user not found: " + name);
			return;
		}
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
	public void displayBalance(String name) {
		User user = getUser(name);
		if (user == null) {
			System.out.println("ERROR: user not found: " + name);
			return;
		}
		System.out.println("-- $" + String.format("%.2f", user.getBalance()));
	}
}
