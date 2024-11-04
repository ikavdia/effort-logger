package application.effortloggerv2;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// Author: Rishi Senthilvel
public class TaskEditorPage extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Set the title for the task editor window
        primaryStage.setTitle("EffortLogger Tasks Editor");

        // Initialize an observable list for completed projects
        ObservableList<String> completedProjectList = FXCollections.observableArrayList(Utility.completedProjects);

        // Dropdowns for project, life cycle, effort category, and plans selection
        ComboBox<String> projectDropdown = new ComboBox<>(FXCollections.observableArrayList(Utility.projects));
        projectDropdown.setPromptText("Projects");

        ComboBox<String> lifeCycleDropdown = new ComboBox<>(FXCollections.observableArrayList(Utility.lifeCycles));
        lifeCycleDropdown.setPromptText("Life Cycles");

        ComboBox<String> effortCategoryDropdown = new ComboBox<>(FXCollections.observableArrayList(Utility.effortCategories));
        effortCategoryDropdown.setPromptText("Effort Categories");

        ComboBox<String> planDropdown = new ComboBox<>(FXCollections.observableArrayList(Utility.plans));
        planDropdown.setPromptText("Plans");

        // Set preferred width for dropdowns
        projectDropdown.setPrefWidth(150);
        lifeCycleDropdown.setPrefWidth(150);
        effortCategoryDropdown.setPrefWidth(150);
        planDropdown.setPrefWidth(150);

        // Buttons for add, delete, and move actions
        Button addButton = new Button("Add");
        Button deleteButton = new Button("Delete");
        Button moveButton = new Button("Move to Complete");

        // TextFields for adding new projects, life cycles, effort categories, and plans
        TextField textProject = new TextField();
        TextField textLifeCycle = new TextField();
        TextField textEffortCategory = new TextField();
        TextField textPlans = new TextField();

        // Set preferred width for text fields
        textProject.setPrefWidth(150);
        textLifeCycle.setPrefWidth(150);
        textEffortCategory.setPrefWidth(150);
        textPlans.setPrefWidth(150);

        // Table to display completed projects
        TableView<String> completedTable = new TableView<>();
        TableColumn<String, String> completedProjects = new TableColumn<>("Completed Projects");
        completedProjects.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
        completedProjects.setPrefWidth(200);
        completedTable.getColumns().add(completedProjects);
        completedTable.setItems(completedProjectList);

        // Action for the "Add" button to add new items to dropdowns and database
        addButton.setOnAction(event -> {
            // Add new project if the field is not empty
            if (!textProject.getText().isEmpty()) {
                Utility.projects.add(textProject.getText());
                projectDropdown.setItems(FXCollections.observableArrayList(Utility.projects));
                DatabaseConnector.updateLists("projects", "add", textProject.getText());
                textProject.clear(); // Clear the text field after adding
            }
            // Add new life cycle step
            if (!textLifeCycle.getText().isEmpty()) {
                Utility.lifeCycles.add(textLifeCycle.getText());
                lifeCycleDropdown.setItems(FXCollections.observableArrayList(Utility.lifeCycles));
                DatabaseConnector.updateLists("lifeCycles", "add", textLifeCycle.getText());
                textLifeCycle.clear();
            }
            // Add new effort category
            if (!textEffortCategory.getText().isEmpty()) {
                Utility.effortCategories.add(textEffortCategory.getText());
                effortCategoryDropdown.setItems(FXCollections.observableArrayList(Utility.effortCategories));
                DatabaseConnector.updateLists("effortCategories", "add", textEffortCategory.getText());
                textEffortCategory.clear();
            }
            // Add new plan
            if (!textPlans.getText().isEmpty()) {
                Utility.plans.add(textPlans.getText());
                planDropdown.setItems(FXCollections.observableArrayList(Utility.plans));
                DatabaseConnector.updateLists("plans", "add", textPlans.getText());
                textPlans.clear();
            }

            // Show confirmation alert after items are added
            Utility.showAlert("Added", "Items have been added successfully", Alert.AlertType.CONFIRMATION);
        });

        // Action for the "Delete" button to remove selected items from dropdowns and database
        deleteButton.setOnAction(event -> {
            // Remove selected project from Utility, database, and dropdown
            if (projectDropdown.getValue() != null) {
                Utility.projects.remove(projectDropdown.getValue());
                DatabaseConnector.updateLists("projects", "delete", projectDropdown.getValue());
                projectDropdown.setItems(FXCollections.observableArrayList(Utility.projects));
            }
            // Remove selected life cycle step
            if (lifeCycleDropdown.getValue() != null) {
                Utility.lifeCycles.remove(lifeCycleDropdown.getValue());
                DatabaseConnector.updateLists("lifeCycles", "delete", lifeCycleDropdown.getValue());
                lifeCycleDropdown.setItems(FXCollections.observableArrayList(Utility.lifeCycles));
            }
            // Remove selected effort category
            if (effortCategoryDropdown.getValue() != null) {
                Utility.effortCategories.remove(effortCategoryDropdown.getValue());
                DatabaseConnector.updateLists("effortCategories", "delete", effortCategoryDropdown.getValue());
                effortCategoryDropdown.setItems(FXCollections.observableArrayList(Utility.effortCategories));
            }
            // Remove selected plan
            if (planDropdown.getValue() != null) {
                Utility.plans.remove(planDropdown.getValue());
                DatabaseConnector.updateLists("plans", "delete", planDropdown.getValue());
                planDropdown.setItems(FXCollections.observableArrayList(Utility.plans));
            }

            // Show confirmation alert after items are deleted
            Utility.showAlert("Deleted", "Items have been deleted successfully", Alert.AlertType.CONFIRMATION);
        });

        // Action for the "Move to Complete" button to mark selected project as completed
        moveButton.setOnAction(event -> {
            // Move the selected project to completed list
            if (projectDropdown.getValue() != null) {
                Utility.projects.remove(projectDropdown.getValue());
                Utility.completedProjects.add(projectDropdown.getValue());
                DatabaseConnector.updateLists("projects", "delete", projectDropdown.getValue());
                DatabaseConnector.updateLists("completedProjects", "add", projectDropdown.getValue());
                completedProjectList.add(projectDropdown.getValue()); // Add to completed projects table
                projectDropdown.setItems(FXCollections.observableArrayList(Utility.projects));
            }

            // Show confirmation alert after moving the project to completed
            Utility.showAlert("Completed", "Project has been successfully marked as completed", Alert.AlertType.CONFIRMATION);
        });

        // Button to return to the home page
        Button homePageButton = new Button("Home Page");
        homePageButton.setOnAction(e -> Utility.openPage("effortloggingpage", primaryStage));

        // Layouts for organizing the UI components
        HBox dropdowns = new HBox(10, projectDropdown, lifeCycleDropdown, effortCategoryDropdown, planDropdown);
        HBox texts = new HBox(10, textProject, textLifeCycle, textEffortCategory, textPlans);
        HBox buttons = new HBox(10, addButton, deleteButton, moveButton);
        HBox homePageBox = new HBox(10, homePageButton);

        VBox leftSide = new VBox(10, dropdowns, texts, buttons, homePageBox);
        HBox layout = new HBox(10, leftSide, completedTable);

        // Create and set the scene for the primary stage
        Scene scene = new Scene(layout, 1000, 500);
        primaryStage.setScene(scene);
        primaryStage.show(); // Display the task editor window
    }
}
