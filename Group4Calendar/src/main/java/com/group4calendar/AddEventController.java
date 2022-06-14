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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class AddEventController implements Initializable {

    public static Stage popUp;

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
        /* get current date and time and set the title to it.
        If the user does not input a date that the current date and time
        will be the title of the event. That was no repeating titles.
         */
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        Date tempTitleDate = new Date(System.currentTimeMillis());

        String title = formatter.format(tempTitleDate);
        String startTime = "";
        String endTime = "";
        String location = "N/A";
        String notes = "N/A";

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

        if (addEventNotesTextArea.getText().equals("")) {
            System.out.println("null");
        }

        if (!addEventTitleTextField.getText().equals("")) {
            title = addEventTitleTextField.getText();
        }

        if (!addEventLocationTextField.getText().equals("")) {
            location = addEventLocationTextField.getText();
        }

        if (!addEventNotesTextArea.getText().equals("")) {
            notes = addEventNotesTextArea.getText();
        }

        Event newEvent = new Event(title, addEventDateInput.getValue().toString(), addEventDateInput.getValue().getDayOfWeek().toString(), startTime, endTime, location, notes);

        // If there is an event with the same title for this date do not create the event.
        ArrayList<Event> events = GetData.getAllEventsForDay(addEventDateInput.getValue());
        boolean eventTitleExists = false;

        for (Event existingEvent : events) {
            if (existingEvent.getTitle().equals(newEvent.getTitle())) {
                eventTitleExists = true;
            }
        }

        if (eventTitleExists) {
            FXMLLoader fxmlLoader = new FXMLLoader(CalendarApplication.class.getResource("same-title-pop-up-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 482, 166);

            popUp = new Stage();
            popUp.setTitle("Error");
            popUp.setScene(scene);

            popUp.setX(600);
            popUp.setY(350);

            popUp.show();
        } else {
            GetData.addEvent(newEvent);

            CalendarController.updateDayView(CalendarController.dayDisplayDate);

            CalendarController.closeNewWindow();
        }

    }

    public static void closePopUp() {
        popUp.close();
    }
}

