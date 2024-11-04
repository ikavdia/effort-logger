package application.effortloggerv2;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utility {
    // Global variables to store the current user's role and username
    public static String role = "";
    public static String username = "";

    // Lists to store data related to projects, life cycles, effort categories, plans, and completed projects
    public static List<String> projects = new ArrayList<>();
    public static List<String> lifeCycles = new ArrayList<>();
    public static List<String> effortCategories = new ArrayList<>();
    public static List<String> plans = new ArrayList<>();
    public static List<String> completedProjects = new ArrayList<>();

    // Author: Mishika Chhabra
    /**
     * Opens a specific page in the application based on the provided page name.
     *
     * @param pageName The name of the page to open.
     * @param stage    The main application stage to set the new page on.
     */
    public static void openPage(String pageName, Stage stage) {
        switch (pageName) {
            case "loginpage":
                LoginPage loginPage = new LoginPage();
                loginPage.start(stage);
                break;
            case "signuppage":
                SignupPage signupPage = new SignupPage();
                signupPage.start(stage);
                break;
            case "effortloggingpage":
                EffortLoggingPage effortLoggingPage = new EffortLoggingPage();
                effortLoggingPage.start(stage);
                break;
            case "effortlogdisplaypage":
                EffortLogDisplayPage effortLogDisplayPage = new EffortLogDisplayPage();
                effortLogDisplayPage.start(stage);
                break;
            case "taskeditorpage":
                TaskEditorPage taskEditorPage = new TaskEditorPage();
                taskEditorPage.start(stage);
                break;
        }
    }

    // Author: Mishika Chhabra
    /**
     * Hashes a plain text password using SHA-256 for secure storage.
     *
     * @param plainPassword The plain text password to hash.
     * @return The hashed password as a hexadecimal string, or an empty string if an error occurs.
     */
    public static String hashPassword(String plainPassword) {
        try {
            // Create a MessageDigest instance for SHA-256 hashing
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Hash the password bytes and store the result
            byte[] hashedBytes = md.digest(plainPassword.getBytes());

            // Convert the hashed bytes to a hexadecimal string
            StringBuilder hashedPassword = new StringBuilder();
            for (byte b : hashedBytes) {
                hashedPassword.append(String.format("%02x", b));
            }
            return hashedPassword.toString();
        } catch (NoSuchAlgorithmException e) {
            // Print stack trace if hashing algorithm is not available
            e.printStackTrace();
            return ""; // Return empty string if hashing fails
        }
    }

    // Author: Aditya Jarodiya
    /**
     * Displays an alert dialog to the user with a specified title, content, and alert type.
     *
     * @param title     The title of the alert dialog.
     * @param content   The content message to display in the alert.
     * @param alertType The type of alert (e.g., INFORMATION, WARNING, ERROR).
     */
    public static void showAlert(String title, String content, Alert.AlertType alertType) {
        // Create a new alert with the specified type
        Alert alert = new Alert(alertType);
        alert.setTitle(title); // Set the alert title
        alert.setHeaderText(null); // No header text for simplicity
        alert.setContentText(content); // Set the main content of the alert
        alert.showAndWait(); // Display the alert and wait for user acknowledgment
    }

    // Author: Aditya Jarodiya
    /**
     * Formats a decimal value as a percentage with two decimal places.
     *
     * @param value The decimal value to format as a percentage.
     * @return A string representing the value as a percentage.
     */
    public static String formatAsPercentage(double value) {
        // Get a percentage format instance and set it to show two decimal places
        NumberFormat percentFormat = NumberFormat.getPercentInstance();
        percentFormat.setMinimumFractionDigits(2);
        return percentFormat.format(value); // Return the formatted percentage string
    }

    // Database credentials (split into multiple parts for added security)
    public static String dbUsername = "lewtn53opij8mfwl0m41";
    public static String dbPassword1 = "pscale_pw_Q5Er";
    public static String dbPassword2 = "oCN9ClpstdMb1MSRRqt";
    public static String dbPassword3 = "ErXaZTxS5xUFT0G9jkm3";
}
