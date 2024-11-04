// Author: Ishan Kavdia

package application.effortloggerv2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The EffortLogDisplayPage class extends the JavaFX Application class and serves as the user interface
 * for displaying logged efforts in the Effort Logger application.
 *
 * This class creates a table view to display various details about logged efforts and provides
 * a button to navigate back to the home page.
 *
 * @author Ishan Kavdia
 */

public class EffortLogDisplayPage extends Application {
    private TableView<EffortLogEntry> tableView; // TableView to display effort log entries
    private Stage primaryStage; // Primary stage for the application window

    /**
     * The start method is the entry point for the EffortLogDisplayPage application.
     * It initializes the stage, sets up the UI components, and displays the Effort Log data.
     *
     * @param stage The primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        // Set the primary stage
        primaryStage = stage;
        stage.setTitle("Effort Log Display");

        // Create table columns for each field in EffortLogEntry
        // Each column is bound to a property in the EffortLogEntry class

        // Column for displaying the employee's name
        TableColumn<EffortLogEntry, String> usernameColumn = new TableColumn<>("Employee Name");
        usernameColumn.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());

        // Column for displaying the start time of the effort
        TableColumn<EffortLogEntry, String> startTimeColumn = new TableColumn<>("Start Time");
        startTimeColumn.setCellValueFactory(cellData -> cellData.getValue().startTimeProperty());

        // Column for displaying the end time of the effort
        TableColumn<EffortLogEntry, String> endTimeColumn = new TableColumn<>("End Time");
        endTimeColumn.setCellValueFactory(cellData -> cellData.getValue().endTimeProperty());

        // Column for displaying the time elapsed during the effort
        TableColumn<EffortLogEntry, String> timeTakenColumn = new TableColumn<>("Time Taken");
        timeTakenColumn.setCellValueFactory(cellData -> cellData.getValue().timeElapsedProperty());

        // Column for displaying the title of the project
        TableColumn<EffortLogEntry, String> projectTitleColumn = new TableColumn<>("Project Title");
        projectTitleColumn.setCellValueFactory(cellData -> cellData.getValue().projectProperty());

        // Column for displaying the life cycle stage of the effort
        TableColumn<EffortLogEntry, String> lifeCycleColumn = new TableColumn<>("Life Cycle");
        lifeCycleColumn.setCellValueFactory(cellData -> cellData.getValue().lifeCycleProperty());

        // Column for displaying the category of the effort (e.g., planning, development)
        TableColumn<EffortLogEntry, String> effortCategoryColumn = new TableColumn<>("Effort Category");
        effortCategoryColumn.setCellValueFactory(cellData -> cellData.getValue().effortCategoryProperty());

        // Column for displaying the plan or goal associated with the effort
        TableColumn<EffortLogEntry, String> planColumn = new TableColumn<>("Plan");
        planColumn.setCellValueFactory(cellData -> cellData.getValue().planProperty());

        // Initialize the TableView and add all columns
        tableView = new TableView<>();
        tableView.getColumns().addAll(usernameColumn, startTimeColumn, endTimeColumn, timeTakenColumn,
                projectTitleColumn, lifeCycleColumn, effortCategoryColumn, planColumn);

        // Fetch data from the database and populate the TableView
        DatabaseConnector.fetchData(tableView);

        // Create a "Home Page" button to navigate back to the main page
        Button backButton = new Button("Home Page");
        backButton.setOnAction(event -> Utility.openPage("effortloggingpage", stage));

        // Layout to hold the TableView and button
        VBox vbox = new VBox();
        vbox.getChildren().addAll(tableView, backButton);

        // Create the scene with a specified width and height
        Scene scene = new Scene(vbox, 1000, 800);
        stage.setScene(scene);

        // Center the stage on the screen and display it
        stage.centerOnScreen();
        stage.show();
    }
}
