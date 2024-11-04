package application.effortloggerv2;

import javafx.beans.property.SimpleStringProperty;

// Author: Ishan Kavdia

// Class representing a single entry in an effort log, containing details like user,
// time spent, project, lifecycle stage, and other relevant information.
public class EffortLogEntry {
    // Properties for storing details of each effort log entry.
    public SimpleStringProperty username, startTime, endTime, timeElapsed, project, lifeCycle, effortCategory, plan;

    // Constructor for initializing the EffortLogEntry with given parameters.
    // Each parameter is assigned to a SimpleStringProperty for data binding purposes in JavaFX.
    public EffortLogEntry(String username, String startTime, String endTime, String timeElapsed, String project, String lifeCycle, String effortCategory, String plan) {
        this.username = new SimpleStringProperty(username);
        this.startTime = new SimpleStringProperty(startTime);
        this.endTime = new SimpleStringProperty(endTime);
        this.timeElapsed = new SimpleStringProperty(formatTime(Integer.parseInt(timeElapsed))); // Converts time in seconds to HH:mm:ss format
        this.project = new SimpleStringProperty(project);
        this.lifeCycle = new SimpleStringProperty(lifeCycle);
        this.effortCategory = new SimpleStringProperty(effortCategory);
        this.plan = new SimpleStringProperty(plan);
    }

    // Getter methods to allow data binding for each property in JavaFX.
    public SimpleStringProperty usernameProperty() {
        return username;
    }
    public SimpleStringProperty startTimeProperty() {
        return startTime;
    }
    public SimpleStringProperty endTimeProperty() {
        return endTime;
    }
    public SimpleStringProperty timeElapsedProperty() {
        return timeElapsed;
    }
    public SimpleStringProperty projectProperty() {
        return project;
    }
    public SimpleStringProperty lifeCycleProperty() {
        return lifeCycle;
    }
    public SimpleStringProperty effortCategoryProperty() {
        return effortCategory;
    }
    public SimpleStringProperty planProperty() {
        return plan;
    }

    // Helper method to format time in seconds to HH:mm:ss format.
    // This helps in displaying the elapsed time in a user-friendly way.
    // Author: Mihir Kataria
    private String formatTime(int seconds) {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int remainingSeconds = seconds % 60;

        // Returns a formatted string in the form of "HH:mm:ss"
        return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds);
    }
}
