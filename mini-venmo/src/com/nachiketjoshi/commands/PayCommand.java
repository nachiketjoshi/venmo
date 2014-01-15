package com.nachiketjoshi.commands;

import com.nachiketjoshi.Venmo;
import com.nachiketjoshi.User;

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
		try {
			String actorName = parameters[0];
			User actor = _app.getUser(actorName);
			if (actor == null) {
				throw new Exception("user not found: " + actorName);
			}
			String targetName = parameters[1];
			User target = _app.getUser(targetName);
			if (target == null) {
				throw new Exception("user not found: " + targetName);
			}
			if (actor == target) {
				throw new Exception("users cannot pay themselves");
			}
			if (actor.getCard() == null) {
				throw new Exception("this user does not have a credit card");
			}
			_app.addPayment(actor, target, parseAmount(parameters[2]),
					parseNote(parameters));
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}

	private double parseAmount(String amount) throws Exception {
		if (amount.charAt(0) != '$') {
			throw new Exception("Invalid amount: " + amount);
		}
		return Double.parseDouble(amount.substring(1));
	}

	private String parseNote(String[] parameters) {
		StringBuilder note = new StringBuilder();
		for (int i = 3; i < parameters.length; i++) {
			note.append(parameters[i]);
			note.append(" ");
		}
		return note.toString().trim();
	}
}
