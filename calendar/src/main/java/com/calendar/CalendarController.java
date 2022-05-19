package com.example.calendar;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CalendarController {
    public void toAddMenu() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("event-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        Stage newWindow = new Stage();
        newWindow.setTitle("Add Event");
        newWindow.setScene(scene);

        newWindow.setX(500);
        newWindow.setY(250);

        newWindow.show();
    }

    public void toEditMenu() throws IOException {

    }
}
