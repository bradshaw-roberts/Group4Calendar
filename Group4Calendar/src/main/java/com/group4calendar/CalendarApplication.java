/**
 * To run this application call main().
 *
 * CIS-2999 Summer I Semester
 * Oakland University
 * Group 4 Calendar
 *
 * Brenden Nagey
 * Ravi Prajapati
 * Bradshaw Roberts
 * Nora Sinishtaj
 * V VanCamp
 */

package com.group4calendar;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CalendarApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CalendarApplication.class.getResource("calendar-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1273, 654);
        stage.setTitle("Calendar");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}