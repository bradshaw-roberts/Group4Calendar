package com.group4calendar;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AddToDoController {

    @FXML private TextField newToDoListItemTextField = new TextField();

    public void onAddToDoListItemSubmitButtonClick() throws IOException {
        ToDoList.addItem(newToDoListItemTextField.getText());

        CalendarController.updateToDoList();

        CalendarController.closeNewWindow();
    }
}
