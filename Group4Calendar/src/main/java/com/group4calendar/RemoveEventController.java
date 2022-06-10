package com.group4calendar;

import javafx.event.ActionEvent;
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

    public void dateChanged(LocalDate date) throws FileNotFoundException {
        events = GetData.getAllEventsForDay(date);

        removeEventTitleChoiceBox.getItems().clear();

        for (Event event : events) {
            removeEventTitleChoiceBox.getItems().add(event.getTitle());
        }

        removeEventTitleChoiceBox.setValue(events.get(0).getTitle());
    }

    public void removeEventDatePicked() throws FileNotFoundException {
        dateChanged(removeEventDatePicker.getValue());
    }

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

