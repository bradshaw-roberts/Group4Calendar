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

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        LocalDate dateToday = LocalDate.now();

        editEventDateInput.setValue(dateToday);

        try {
            dateChanged(dateToday);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void dateChanged(LocalDate date) throws FileNotFoundException {
        events = GetData.getAllEventsForDay(date);

        editEventTitleChoiceBox.getItems().clear();

        for (Event event : events) {
            editEventTitleChoiceBox.getItems().add(event.getTitle());
        }
    }

    public void editEventDatePicked() throws FileNotFoundException {
        dateChanged(editEventDateInput.getValue());
    }

    public void editEventTitleChoiceBoxSelected() {
        Event eventSelected = events.get(0);

        for (Event event : events) {
            if (event.getTitle().equals(editEventTitleChoiceBox.getValue().toString())) {
                eventSelected = event;
            }
        }

        editEventTitleChoiceBox.setValue(eventSelected.getTitle());

//        editEventLocationTextField.setText(eventSelected.getLocation());
//        editEventNotesTextArea.setText(eventSelected.getNotes());
////        editEventStartTimeMinSpinner.
////                editEventStartTimeMinSpinner
////
////        editEventStartTimeAMPMChoiceBox.setValue(eventSelected.get);
////                editEventEndTimeAMPMChoiceBox

    }

    public void onEditEventSubmitButtonClick () throws IOException {
        String startTime = "";
        String endTime = "";

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

        Event newEvent = new Event(editEventTitleChoiceBox.getValue().toString(), editEventDateInput.getValue().toString(), editEventDateInput.getValue().getDayOfWeek().toString(), startTime, endTime, editEventLocationTextField.getText(), editEventNotesTextArea.getText());

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
