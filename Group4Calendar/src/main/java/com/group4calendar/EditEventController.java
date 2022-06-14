/**
 * Controller for edit-event-view.fxml
 * Allows the user to edit existing events.
 * First the user will select a date using the date picker. Then the title choice
 * box will update its values to include every event on that date. The user will
 * select the title of the event they want to edit. Then they will be able to add
 * all the other information for the event.
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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class EditEventController implements Initializable {

    private static ArrayList<Event> events;

    @FXML private ChoiceBox editEventStartTimeAMPMChoiceBox = new ChoiceBox();
    @FXML private ChoiceBox editEventEndTimeAMPMChoiceBox = new ChoiceBox();

    @FXML private ChoiceBox editEventTitleChoiceBox = new ChoiceBox();

    @FXML private Spinner<Integer> editEventStartTimeHourSpinner = new Spinner<Integer>();
    @FXML private Spinner<Integer> editEventStartTimeMinSpinner = new Spinner<Integer>();
    @FXML private Spinner<Integer> editEventEndTimeHourSpinner = new Spinner<Integer>();
    @FXML private Spinner<Integer> editEventEndTimeMinSpinner = new Spinner<Integer>();

    @FXML private TextField editEventLocationTextField = new TextField();
    @FXML private TextArea editEventNotesTextArea = new TextArea();

    @FXML private DatePicker editEventDateInput = new DatePicker();

    @FXML private Button editEventSubmitButton = new Button();

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        LocalDate dateToday = LocalDate.now();

        editEventDateInput.setValue(dateToday);

        try {
            dateChanged(dateToday);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * When a new date is selected using the datepicker this method will be called
     * to update the title choicebox list of values
     *
     * @param date
     * @throws FileNotFoundException
     */
    public void dateChanged(LocalDate date) throws FileNotFoundException {
        events = GetData.getAllEventsForDay(date);

        editEventTitleChoiceBox.getItems().clear();

        for (Event event : events) {
            editEventTitleChoiceBox.getItems().add(event.getTitle());
        }

        editEventLocationTextField.setText("");
        editEventNotesTextArea.setText("");
        editEventTitleChoiceBox.setValue("");
    }

    public void editEventDatePicked() throws FileNotFoundException {
        dateChanged(editEventDateInput.getValue());
    }

    /**
     * When the user selects the title of the event they want to edit this method
     * will be called to enter the event's data into all the fields on screen.
     *
     * @throws FileNotFoundException
     */
    public void editEventTitleChoiceBoxSelected() {
        editEventSubmitButton.setDisable(false);

        Event eventSelected = events.get(0);

        for (Event event : events) {
            if (event.getTitle().equals(editEventTitleChoiceBox.getValue().toString())) {
                eventSelected = event;
            }
        }


        if (eventSelected.getTitle().equals("No Events Today")) {
            editEventSubmitButton.setDisable(true);
        } else {
            editEventTitleChoiceBox.setValue(eventSelected.getTitle());
            editEventLocationTextField.setText(eventSelected.getLocation());
            editEventNotesTextArea.setText(eventSelected.getNotes());
        }
    }

    /**
     * When the user clicks the submit button this method will get all
     * the fields' data, create an Event object, and send it to GetData.
     *
     * @throws IOException
     */
    public void onEditEventSubmitButtonClick () throws IOException {
        String startTime = "";
        String endTime = "";
        String location = "N/A";
        String notes = "N/A";

        // convert the start time input to string for Event object
        if (editEventStartTimeHourSpinner.getValue() == 12) {
            startTime += editEventStartTimeHourSpinner.getValue();
        } else {
            startTime += "0" + editEventStartTimeHourSpinner.getValue();
        }

        startTime += ":";

        if (editEventStartTimeMinSpinner.getValue() > 10) {
            startTime += editEventStartTimeMinSpinner.getValue();
        } else {
            startTime += "0" + editEventStartTimeMinSpinner.getValue();
        }

        startTime += " " + editEventStartTimeAMPMChoiceBox.getValue();

        // convert the end time input to string for Event object
        if (editEventEndTimeHourSpinner.getValue() == 12) {
            endTime += editEventEndTimeHourSpinner.getValue();
        } else {
            endTime += "0" + editEventEndTimeHourSpinner.getValue();
        }

        endTime += ":";

        if (editEventEndTimeMinSpinner.getValue() > 10) {
            endTime += editEventEndTimeMinSpinner.getValue();
        } else {
            endTime += "0" + editEventEndTimeMinSpinner.getValue();
        }

        endTime += " " + editEventEndTimeAMPMChoiceBox.getValue();

        if (!editEventLocationTextField.getText().equals("")) {
            location = editEventLocationTextField.getText();
        }

        if (!editEventNotesTextArea.getText().equals("")) {
            notes = editEventNotesTextArea.getText();
        }

        Event newEvent = new Event(editEventTitleChoiceBox.getValue().toString(), editEventDateInput.getValue().toString(), editEventDateInput.getValue().getDayOfWeek().toString(), startTime, endTime, location, notes);

        Event oldEvent = events.get(0);

        for (Event event : events) {
            if (event.getTitle().equals(editEventTitleChoiceBox.getValue().toString())) {
                oldEvent = event;
            }
        }

        GetData.editEvent(oldEvent, newEvent);

        CalendarController.updateDayView(CalendarController.dayDisplayDate);

        CalendarController.closeNewWindow();
    }
}