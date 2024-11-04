package application.effortloggerv2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

// Author: Mishika Chhabra
public class SignupPage extends Application {
    private Stage primaryStage; // Main stage for the signup window

    @Override
    public void start(Stage stage) {
        // Set the primary stage for this signup page
        primaryStage = stage;
        stage.setTitle("EffortLogger Signup Page");

        // Create a GridPane layout for the signup form
        GridPane grid = new GridPane();
        grid.setHgap(10); // Horizontal gap between elements
        grid.setVgap(10); // Vertical gap between elements
        grid.setPadding(new Insets(40, 40, 40, 40)); // Padding around the grid

        // Username, password, and confirm password fields
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField(); // Text field for entering the username

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField(); // Password field to hide entered text

        Label confirmPasswordLabel = new Label("Confirm Password:");
        PasswordField confirmPasswordField = new PasswordField(); // Password field to confirm the password

        // Signup button to initiate registration
        Button signupButton = new Button("Sign Up");

        // Text for the login prompt with styling and event handling
        Text loginText = new Text("Already have an account? Log in!");
        loginText.setStyle("-fx-fill: blue;"); // Set the text color to blue
        loginText.setOnMouseEntered(event -> loginText.setUnderline(true)); // Underline on mouse hover
        loginText.setOnMouseExited(event -> loginText.setUnderline(false)); // Remove underline on mouse exit
        loginText.setOnMouseClicked(event -> Utility.openPage("loginpage", stage)); // Open login page on click

        // Handle the action for the signup button
        signupButton.setOnAction(event -> {
            // Sanitize inputs to prevent injection attacks
            String username = InputValidation.sanitize(usernameField.getText());
            String password = InputValidation.sanitize(passwordField.getText());
            String confirmPassword = InputValidation.sanitize(confirmPasswordField.getText());

            // Validate input: Check if passwords match
            if (!password.equals(confirmPassword)) {
                Utility.showAlert("Password Mismatch", "The entered passwords do not match", Alert.AlertType.ERROR);
            }
            // Validate input: Check if username or password fields are empty
            else if (username.isEmpty() || password.isEmpty()) {
                Utility.showAlert("Empty Fields", "Username and password cannot be empty", Alert.AlertType.ERROR);
            }
            // If all validations pass, register the user
            else {
                DatabaseConnector.addUser(username, password); // Add the user to the database
                Utility.showAlert("Successfully Registered", "You have been successfully registered", Alert.AlertType.INFORMATION); // Show success alert
                Utility.username = username; // Store the username
                Utility.role = "developer"; // Assign default role as "developer"
                Utility.openPage("effortloggingpage", primaryStage); // Open the main effort logging page
            }
        });

        // Add UI elements to the grid layout
        grid.add(usernameLabel, 0, 0); // Username label at row 0, column 0
        grid.add(usernameField, 1, 0); // Username field at row 0, column 1
        grid.add(passwordLabel, 0, 1); // Password label at row 1, column 0
        grid.add(passwordField, 1, 1); // Password field at row 1, column 1
        grid.add(confirmPasswordLabel, 0, 2); // Confirm password label at row 2, column 0
        grid.add(confirmPasswordField, 1, 2); // Confirm password field at row 2, column 1
        grid.add(signupButton, 0, 3, 2, 1); // Signup button spanning two columns at row 3
        grid.add(loginText, 0, 4, 2, 1); // Login text spanning two columns at row 4

        // Create a VBox container for the grid layout and set spacing
        VBox container = new VBox(20);
        container.getChildren().addAll(grid);

        // Create a new scene with the container and set the dimensions
        Scene scene = new Scene(container, 400, 300);

        // Set the scene for the primary stage and display the signup window
        stage.setScene(scene);
        stage.show();
    }
}
