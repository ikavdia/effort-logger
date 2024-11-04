// Author: Ishan Kavdia

package application.effortloggerv2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The InputValidation class is designed to handle input validation and sanitization
 * to ensure the security and integrity of user input in the Effort Logger application.
 */

public class InputValidation {

    /**
     * Sanitizes the input to remove potential security risks.
     *
     * @param input The input string to be sanitized.
     * @return A sanitized version of the input string, with potential malicious characters removed.
     */
    public static String sanitize(String input) {
        // Check for null input to prevent NullPointerException
        if (input == null) {
            return "";
        }

        // Remove leading and trailing white spaces for clean input
        input = input.trim();

        // Prevent SQL injection by removing common SQL injection characters like single quotes, semicolons, double quotes, and backslashes
        input = input.replaceAll("([';\"\\\\])", "");

        // Prevent XSS (Cross-Site Scripting) attacks by converting < and > to their HTML entity equivalents
        input = input.replaceAll("<", "&lt;").replaceAll(">", "&gt;");

        return input; // Return the sanitized input string
    }

    /**
     * Checks if the input string meets custom validation rules (not used currently).
     * Designed for additional validation beyond sanitization.
     *
     * @param input The input string to be validated.
     * @return True if the input passes all validation checks, false otherwise.
     */
    private static boolean isValidInput(String input) {
        // Define a regular expression pattern to allow only alphanumeric characters and select special characters
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9!@#\\$%^&*()_+\\-={}:;',./?]*$");

        // Create a matcher to check the input against the pattern
        Matcher matcher = pattern.matcher(input);

        // Return true if the input matches the pattern, indicating valid input
        return matcher.matches();
    }
}
