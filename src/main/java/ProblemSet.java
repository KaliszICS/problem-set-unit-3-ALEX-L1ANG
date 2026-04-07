/*
File Name: Problem Set Unit 3
Author: Alex Liang
Date Created: March 30, 2026
Date Last Modified: April 7, 2026
*/

import java.util.Scanner;
public class ProblemSet {

	public static void main(String[] args) {
		// Email(s) intake system
		Scanner input = new Scanner(System.in);
		System.out.print("Input two emails: ");
		String emails = input.nextLine();
		emails = emails.toLowerCase(); // Removing case sensitivity 
		emails = emails.trim(); // Remove excess spacing

		if (emails.contains(",")) {
			// Dividing into 2 seperate Emails for seperate validation
		    String email1 = (emails.substring(0, emails.indexOf(",")));
		    String email2 = (emails.substring(emails.indexOf(",") + 2));
	    	// Validation
		    String reason1 = validation(email1);
	    	String reason2 = validation(email2);
	    	// Result
	    	System.out.println(result(reason1, email1));
	    	System.out.println(result(reason2, email2));
		}
		else {
		    String reasoning = validation(emails);
		    System.out.println(result(reasoning, emails));
		}
	}

	// Checking for invaldations
	public static String validation(String email) {
		if (!email.contains("@")) { // If Email does not contain @
			return "Missing @"; // Reason
		}
		if (email.indexOf("@") != email.lastIndexOf("@")) { // If there's more than one @
			return "Multiple @"; // Reason
		}
		if (email.startsWith(".") || email.endsWith(".")) { // Email can not start or end with .
			return "Starts or ends with dot"; // Reason
		}
		if (email.contains(" ")) { // Email can not have spaces
			return "Contains spaces"; // Reason
		}
		String local = email.substring(0, email.indexOf("@")); // Defining local part
		String domain = email.substring(email.indexOf("@") + 1); // Defining domain part
		// exception C process
		if (domain.equals("gmail.com")) {
			local = local.replace(".", "");
		}
		if (local.length() > 64) { // Local must be under 64 characters
			return "Local part too long"; // Reason
		}
		if (local.length() < 1) { // Local can not be less than 1 character
			return "Local part too short"; // Reason
		}
		if (!domain.contains(".")) { // Domain needs a . in between
			return "No dot in domain"; // Reason
		} 
		String domainExtension = domain.substring(domain.lastIndexOf(".") + 1); // Defining domain extension (what's after .)
		int domainExtensionLength = domainExtension.length();
		if (domainExtensionLength < 2 || domainExtensionLength > 6) { // Extension (after final dot) has to be 2-6 characters
			return "Invalid domain extension length";
		}

		// exception B
		return exceptionB(email, domain);
	}

	//exception B process
	public static String exceptionB(String email, String domain) {
		if (email.startsWith("+") || email.endsWith("+")) { // Can not start or end with +
			return "Starts or ends with plus";
		}
		if (email.startsWith("_") || email.endsWith("_")) { // Can not start or end with _
			return "Starts or ends with underscore";
		}
		if (domain.contains("+")) { // + can only be in Local
			return "Domain contains plus";
		}
		if (domain.contains("_")) { // _ can only be in Local
			return "Domain contains underscore";
		}
		return "Valid"; // if all statements above are false, start valid Email format
	}

	// Printing result, i.e. Valid/Invalid & Reason
	public static String result(String reasoning, String email) {
		// If result is valid
		if (reasoning.equals("Valid")) {
			String local = email.substring(0, email.indexOf("@"));
			String domain = email.substring(email.indexOf("@") + 1);
			System.out.print(email + ": Valid ");
			if (domain.equals("gmail.com")) {
				System.out.print("(Gmail normalized) ");
			}
			return "| Local: " + local + " | Domain: " + domain;
		}
		// If result is invalid
		else {
		return email + ": Invalid: " + reasoning;
		}
	}
}

