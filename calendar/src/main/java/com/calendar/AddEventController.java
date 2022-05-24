package com.example.calendar;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;


public class AddEventController {

    String ampm[] = {"AM", "PM"};

    @FXML
    public ChoiceBox addEventStartTimeAMPMChoiceBox = new ChoiceBox(FXCollections.observableArrayList(ampm));

    @FXML
    private Spinner<Integer> startTimeHourSpinner = new Spinner<Integer>(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 12, 0));

    @FXML
    private TextField addEventTitleTextField;
    @FXML
    private TextField addEventLocationTextField;
    @FXML
    private TextArea addEventNotesTextArea;

    @FXML
    private DatePicker addEventDateInput;

//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//
//    }

    public void onAddEventSubmitButtonClick () {
        //Event test = new Event(addEventTitleTextField.getText(), addEventDateInput.getValue().toString(), , 200, addEventLocationTextField.getText(), addEventNotesTextArea.getText());

        addEventDateInput.getValue(); // 2022-05-01
    }
}
