package com.example.calendar;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.io.IOException;

public class AddToDoController {

    @FXML TextField newToDoListItemTextField = new TextField();
    @FXML Button newToDoListItemSubmitButton = new Button();

    public void onAddToDoListItemSubmitButtonClick() throws IOException {
        ToDoList.addItem(newToDoListItemTextField.getText());

        //static problem
        //CalendarController.updateToDoList();

        CalendarController.closeNewWindow();
    }
}
