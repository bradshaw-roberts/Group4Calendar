package com.group4calendar;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RemoveToDoController {

    @FXML private TextField newToDoListItemTextField = new TextField();
    @FXML private Button newToDoListItemSubmitButton = new Button();

    public void onRemoveToDoListItemSubmitButtonClick() throws IOException {
        ToDoList.removeItem(newToDoListItemTextField.getText());

        CalendarController.updateToDoList();

        CalendarController.closeNewWindow();
    }
}
