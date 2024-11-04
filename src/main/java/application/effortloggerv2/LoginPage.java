// Authors: Aditya Jarodiya, Mishika Chhabra, Ishan Kavdia, Mihir Kataria, Akshit Jain, Rishikumar Senthilvel

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
public class LoginPage extends Application {
	private Stage primaryStage; // Main stage for the login window

	@Override
	public void start(Stage stage) {
		try {
			// Set the primary stage for this login page
			this.primaryStage = stage;
			primaryStage.setTitle("EffortLogger Login Page");

			// Create a GridPane layout for organizing form elements
			GridPane grid = new GridPane();
			grid.setHgap(10); // Horizontal gap between elements
			grid.setVgap(10); // Vertical gap between elements
			grid.setPadding(new Insets(40, 40, 40, 40)); // Padding around the grid

			// Username label and input field
			Label usernameLabel = new Label("Username:");
			TextField usernameField = new TextField();

			// Password label and input field
			Label passwordLabel = new Label("Password:");
			PasswordField passwordField = new PasswordField();

			// Login button to initiate login action
			Button loginButton = new Button("Login");

			// Text for signup prompt with styling and event handling
			Text signupText = new Text("Don't have an account? Sign up!");
			signupText.setStyle("-fx-fill: blue;"); // Set the text color to blue
			signupText.setOnMouseEntered(event -> signupText.setUnderline(true)); // Underline on hover
			signupText.setOnMouseExited(event -> signupText.setUnderline(false)); // Remove underline on hover exit
			signupText.setOnMouseClicked(event -> Utility.openPage("signuppage", primaryStage)); // Open signup page on click

			// Set up action for login button
			loginButton.setOnAction(event -> {
				// Sanitize user inputs for security
				String username = InputValidation.sanitize(usernameField.getText());
				String password = InputValidation.sanitize(passwordField.getText());

				// Validate credentials using the DatabaseConnector
				String role = DatabaseConnector.validateUser(username, password);
				if (role.equals("Not Found")) {
					// Display error alert if credentials are incorrect
					Utility.showAlert("Incorrect Credentials", "Invalid username or password", Alert.AlertType.ERROR);
				} else {
					// If login is successful, store role and username, and open the main effort logging page
					Utility.role = role;
					Utility.username = username;
					Utility.openPage("effortloggingpage", primaryStage);
				}
			});

			// Add elements to the grid layout
			grid.add(usernameLabel, 0, 0); // Username label at row 0, column 0
			grid.add(usernameField, 1, 0); // Username field at row 0, column 1
			grid.add(passwordLabel, 0, 1); // Password label at row 1, column 0
			grid.add(passwordField, 1, 1); // Password field at row 1, column 1
			grid.add(loginButton, 0, 3, 2, 1); // Login button spanning two columns at row 3
			grid.add(signupText, 0, 4, 2, 1); // Signup text spanning two columns at row 4

			// Create a VBox container for the grid and set spacing
			VBox container = new VBox(20);
			container.getChildren().addAll(grid);

			// Create and set the scene for the primary stage
			Scene scene = new Scene(container, 400, 300);
			primaryStage.setScene(scene);
			primaryStage.show(); // Display the login window
		} catch(Exception e) {
			e.printStackTrace(); // Print any exceptions encountered
		}
	}

	// Main method to load essential data and launch the application
	public static void main(String[] args) {
		DatabaseConnector.loadLists(); // Load essential lists from the database before starting the app
		launch(args); // Launch the JavaFX application
	}
}
