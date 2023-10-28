package org.kainos.ea.core;

import org.kainos.ea.cli.CustomerRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerValidator {

    /**
     * Validates the data in a CustomerRequest object
     *
     * @param customer The customer to validate
     * @return true if the customer passed all validation checks, otherwise false
     */
    public static String validateCustomer(CustomerRequest customer) {

        boolean errorFound = false;
        String errorMessage = "Please correct the following errors:\n\n";

        // Validate the customers first name
        String firstName = customer.getFirstName();
        if (firstName == null || firstName.length() < 1 || firstName.length() > 255) {
            errorFound = true;
            errorMessage += "First name must contain 1-255 characters\n";
        }

        // Validate the customers last name
        String lastName = customer.getLastName();
        if (lastName == null || lastName.length() < 1 || lastName.length() > 255) {
            errorFound = true;
            errorMessage += "Last name must contain 1-255 characters\n";
        }

        // Validate the customers address
        String address = customer.getAddress();
        if (address == null || address.length() < 1 || address.length() > 255) {
            errorFound = true;
            errorMessage += "Address must contain 1-255 characters\n";
        }

        // Validate the customers phone number
        String phone = customer.getPhone();
        if (phone == null || !validatePhoneNumber(phone) || phone.length() != 11) {
            errorFound = true;
            errorMessage += "Phone number must contain exactly 11 digits (no non-numerical characters allowed)\n";
        }

        // Validate the customers email address
        String email = customer.getEmail();
        if (email == null || !validateEmail(email) || email.length() < 1 || email.length() > 255) {
            errorFound = true;
            errorMessage += "Email address must be in a valid format";
        }

        if (errorFound) {
            return errorMessage;
        }

        return null;

    }

    /**
     * Validates a phone number to ensure it contains only numerical characters
     *
     * @param phoneNumber The phone number to validate
     * @return true if the phone number contains only digits, otherwise false
     */
    private static boolean validatePhoneNumber(String phoneNumber) {

        // Regex pattern for non-numerical characters
        Pattern pattern = Pattern.compile("[^0-9]");

        Matcher matcher = pattern.matcher(phoneNumber);

        // If a non-numerical character is found, return false (validation failed)
        if (matcher.find()) {
            return false;
        }

        return true;

    }

    /**
     * Validates a phone number to ensure it contains only numerical characters
     *
     * @param emailAddress The email address to validate
     * @return true if the email address is in a valid format, otherwise false
     */
    private static boolean validateEmail(String emailAddress) {

        // Regex pattern for valid email address
        Pattern pattern = Pattern.compile("^[\\w\\.-]+@[\\w\\.-]+\\.\\w+$");

        Matcher matcher = pattern.matcher(emailAddress);

        // Return true/false if validation passed/failed
        return matcher.matches();

    }

}
