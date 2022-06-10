/**
 * Controller for add-event-view.fxml
 * The user will enter all the information for a new event
 * and then it will be added to the files.
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

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddEventController implements Initializable {

    @FXML private ChoiceBox addEventStartTimeAMPMChoiceBox = new ChoiceBox();
    @FXML private ChoiceBox addEventEndTimeAMPMChoiceBox = new ChoiceBox();

    @FXML private Spinner<Integer> addEventStartTimeHourSpinner = new Spinner<Integer>();
    @FXML private Spinner<Integer> addEventStartTimeMinSpinner = new Spinner<Integer>();
    @FXML private Spinner<Integer> addEventEndTimeHourSpinner = new Spinner<Integer>();
    @FXML private Spinner<Integer> addEventEndTimeMinSpinner = new Spinner<Integer>();

    @FXML private TextField addEventTitleTextField;
    @FXML private TextField addEventLocationTextField;
    @FXML private TextArea addEventNotesTextArea;

    @FXML private DatePicker addEventDateInput;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LocalDate dateToday = LocalDate.now();
        addEventDateInput.setValue(dateToday);
    }

    /**
     * Get all data from the interface fields and create an Event
     * object. Then send that object to GetData.
     *
     * @throws IOException
     */
    public void onAddEventSubmitButtonClick () throws IOException {
        String startTime = "";
        String endTime = "";

        // convert the start time input to string for Event object
        if (addEventStartTimeHourSpinner.getValue() == 12) {
            startTime += addEventStartTimeHourSpinner.getValue();
        } else {
            startTime += "0" + addEventStartTimeHourSpinner.getValue();
        }

        startTime += ":";

        if (addEventStartTimeMinSpinner.getValue() > 10) {
            startTime += addEventStartTimeMinSpinner.getValue();
        } else {
            startTime += "0" + addEventStartTimeMinSpinner.getValue();
        }

        startTime += " " + addEventStartTimeAMPMChoiceBox.getValue();

        // convert the end time input to string for Event object
        if (addEventEndTimeHourSpinner.getValue() == 12) {
            endTime += addEventEndTimeHourSpinner.getValue();
        } else {
            endTime += "0" + addEventEndTimeHourSpinner.getValue();
        }

        endTime += ":";

        if (addEventEndTimeMinSpinner.getValue() > 10) {
            endTime += addEventEndTimeMinSpinner.getValue();
        } else {
            endTime += "0" + addEventEndTimeMinSpinner.getValue();
        }

        endTime += " " + addEventEndTimeAMPMChoiceBox.getValue();

        Event event = new Event(addEventTitleTextField.getText(), addEventDateInput.getValue().toString(), addEventDateInput.getValue().getDayOfWeek().toString(), startTime, endTime, addEventLocationTextField.getText(), addEventNotesTextArea.getText());

        GetData.addEvent(event);

        CalendarController.updateDayView(CalendarController.dayDisplayDate);

        CalendarController.closeNewWindow();
    }
}
