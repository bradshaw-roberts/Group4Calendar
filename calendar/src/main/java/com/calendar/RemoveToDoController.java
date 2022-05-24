package com.example.calendar;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.FileNotFoundException;
import java.io.IOException;

public class RemoveToDoController {
    @FXML
    TextField newToDoListItemTextField = new TextField();
    @FXML
    Button newToDoListItemSubmitButton = new Button();

    public void onRemoveToDoListItemSubmitButtonClick() throws IOException {
        ToDoList.removeItem(newToDoListItemTextField.getText());

        //static problem
        //CalendarController.updateToDoList();

        CalendarController.closeNewWindow();
    }
}
