package com.nachiketjoshi.commands;

import com.nachiketjoshi.CreditCard;
import com.nachiketjoshi.Venmo;
import com.nachiketjoshi.User;

public class AddCommand extends AbstractCommand {

	public AddCommand(Venmo app) {
		super(app, new CommandSpec("add"));
		CommandSpec spec = getCommandSpec();
		spec.addRequiredParameter("<user>");
		spec.addRequiredParameter("<card_number>");
	}

	@Override
	public void process(String[] parameters) {
		try {
			String userName = parameters[0];
			User user = _app.getUser(userName);
			if (user == null) {
				throw new Exception("user not found: " + userName);
			}
			if (user.getCard() != null) {
				throw new Exception("this user already has a valid credit card");
			}
			String creditCard = parameters[1];
			validateCreditCard(creditCard);
			_app.addCreditCard(user,
					new CreditCard(creditCard.replaceAll("[ -]", "")));
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}

	/**
	 * @author <a
	 *         href="http://www.chriswareham.demon.co.uk/software/Luhn.java">Chris
	 *         Wareham</a>
	 */
	private void validateCreditCard(String digits) throws Exception {
		if (!digits
				.matches("\\d\\d\\d\\d([-\\s])?\\d\\d\\d\\d([-\\s])?\\d\\d\\d\\d([-\\s])?\\d\\d\\d\\d")) {
			throw new Exception("this card is invalid: " + digits);
		}

		digits = digits.replaceAll("[ -]", "");
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
			throw new Exception("this card is invalid: " + digits);
		}
	}
}
