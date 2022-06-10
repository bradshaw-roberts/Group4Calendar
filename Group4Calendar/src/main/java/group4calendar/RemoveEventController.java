/**
 * Controller for remove-event-view.fxml
 * The user will select the date of the event they want to remove.
 * Then the title choicebox will be changed to have all the events
 * on the date title as values. The user will then select one of those
 * titles and click the submit button. Then that event will be removed
 * from the files.
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


public class RemoveEventController implements Initializable {

    private static ArrayList<Event> events;

    @FXML private ChoiceBox removeEventTitleChoiceBox = new ChoiceBox();

    @FXML private DatePicker removeEventDatePicker = new DatePicker();

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        LocalDate dateToday = LocalDate.now();

        removeEventDatePicker.setValue(dateToday);

        try {
            dateChanged(dateToday);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * When the datepicker is changed the title choicebox
     * will be updated.
     *
     * @param date
     * @throws FileNotFoundException
     */
    public void dateChanged(LocalDate date) throws FileNotFoundException {
        events = GetData.getAllEventsForDay(date);

        removeEventTitleChoiceBox.getItems().clear();

        for (Event event : events) {
            removeEventTitleChoiceBox.getItems().add(event.getTitle());
        }

        removeEventTitleChoiceBox.setValue(events.get(0).getTitle());
    }

    /**
     * Send the date to dateChanged.
     *
     * @throws FileNotFoundException
     */
    public void removeEventDatePicked() throws FileNotFoundException {
        dateChanged(removeEventDatePicker.getValue());
    }

    /**
     * Submit button is clicked and the event chosen will be removed.
     *
     * @throws IOException
     */
    public void onRemoveEventSubmitButtonClick() throws IOException {
        Event eventSelected = events.get(0);

        for (Event event : events) {
            if (event.getTitle().equals(removeEventTitleChoiceBox.getValue().toString())) {
                eventSelected = event;
            }
        }

        GetData.removeEvent(eventSelected);

        CalendarController.updateDayView(CalendarController.dayDisplayDate);

        CalendarController.closeNewWindow();
    }

}

