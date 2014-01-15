package com.nachiketjoshi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;

/**
 * @author Nachi
 * 
 */
public class MiniVenmoTest {

	public static void main(String[] args) {
		if (args.length > 1) {
			System.out.println("Too many arguments");
			System.exit(-1);
		}
		Venmo app = new MiniVenmo();
		try {
			BufferedReader in = new BufferedReader(
					args.length == 0 ? new InputStreamReader(System.in)
							: new FileReader(new File(args[1])));
			for (String input = in.readLine(); input != null; input = in
					.readLine()) {
				System.out.println(input);
				app.process(input);
			}
		} catch (Exception e) {
			System.out.println("Caught exception while processing: "
					+ e.getMessage());
			e.printStackTrace();
			System.exit(-1);
		}
	}
}
