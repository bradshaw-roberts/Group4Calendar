package com.group4calendar;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.ResourceBundle;

public class CalendarController implements Initializable  {

    public static LocalDate dayDisplayDate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        toDoListTableView_ = toDoListTableView;
        toDoListTableColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));

        dayTableView_ = dayTableView;

        startTimeDayTableColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endTimeDayTableColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        titleDayTableColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        notesDayTableColumn.setCellValueFactory(new PropertyValueFactory<>("notes"));
        locationDayTableColumn.setCellValueFactory(new PropertyValueFactory<>("location"));

        setUpMonthViewLabelsArrayList();
        setUpWeekViewLabelsArrayList();

        try {
            updateToDoList();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        LocalDate dateToday = LocalDate.now();
        dayDisplayDate = dateToday;
        dayDatePicker.setValue(dateToday);
        weekDatePicker.setValue(dateToday);
        monthChoiceBox.setValue(dateToday.getMonth().toString());
        yearSpinner.getValueFactory().setValue(dateToday.getYear());

        try {
            updateDayView(dayDisplayDate);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            updateMonthView();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            updateWeekView();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



    //to do list stuff
    @FXML private TextField toDoListTextField = new TextField();
    @FXML private TableView toDoListTableView = new TableView();
    private static TableView toDoListTableView_;

    @FXML private TableColumn<ToDoListItem, String> toDoListTableColumn = new TableColumn<>("Items");

    public static void updateToDoList() throws FileNotFoundException {
        ArrayList<String> currentToDoListItems = ToDoList.getAll();

        for (int i = 0; i < currentToDoListItems.size(); i++) {
            toDoListTableView_.getItems().clear();
        }

        for (String item : currentToDoListItems) {
            toDoListTableView_.getItems().add(new ToDoListItem(item));
        }
    }

    public static Stage newWindow;

    public static void closeNewWindow() {
        newWindow.close();
    }

    public void onAddToDoButtonClick() throws IOException {
        ToDoList.addItem(toDoListTextField.getText());

        toDoListTextField.clear();

        CalendarController.updateToDoList();
    }

    public void onRemoveToDoButtonClick() throws IOException {
        ToDoList.removeItem(toDoListTextField.getText());

        toDoListTextField.clear();

        CalendarController.updateToDoList();
    }





    //calendar stuff
    //day view
    @FXML DatePicker dayDatePicker = new DatePicker();

    @FXML private TableView dayTableView = new TableView();
    private static TableView dayTableView_ = new TableView();

    @FXML private TableColumn<Event, String> titleDayTableColumn = new TableColumn<>();
    @FXML private TableColumn<Event, String> startTimeDayTableColumn = new TableColumn<>();
    @FXML private TableColumn<Event, String> endTimeDayTableColumn = new TableColumn<>();
    @FXML private TableColumn<Event, String> notesDayTableColumn = new TableColumn<>();
    @FXML private TableColumn<Event, String> locationDayTableColumn = new TableColumn<>();

    //week view
    @FXML DatePicker weekDatePicker = new DatePicker();

    ArrayList<Label> weekViewLabels = new ArrayList<>();
    ArrayList<Label> weekViewDateLabels = new ArrayList<>();

    @FXML private Label weekViewSUNDAYLabel = new Label();
    @FXML private Label weekViewMONDAYLabel = new Label();
    @FXML private Label weekViewTUESDAYLabel = new Label();
    @FXML private Label weekViewWEDNESDAYLabel = new Label();
    @FXML private Label weekViewTHURSDAYLabel = new Label();
    @FXML private Label weekViewFRIDAYLabel = new Label();
    @FXML private Label weekViewSATURDAYLabel = new Label();

    @FXML private Label weekViewSUNDAYDateLabel = new Label();
    @FXML private Label weekViewMONDAYDateLabel = new Label();
    @FXML private Label weekViewTUESDAYDateLabel = new Label();
    @FXML private Label weekViewWEDNESDAYDateLabel = new Label();
    @FXML private Label weekViewTHURSDAYDateLabel = new Label();
    @FXML private Label weekViewFRIDAYDateLabel = new Label();
    @FXML private Label weekViewSATURDAYDateLabel = new Label();

    //month view
    @FXML ChoiceBox monthChoiceBox = new ChoiceBox<>();
    @FXML Spinner yearSpinner = new Spinner<>();

    ArrayList<Label> monthViewLabels = new ArrayList<>();

    @FXML private Label week1SUNDAYLabel = new Label();
    @FXML private Label week1MONDAYLabel = new Label();
    @FXML private Label week1TUESDAYLabel = new Label();
    @FXML private Label week1WEDNESDAYLabel = new Label();
    @FXML private Label week1THURSDAYLabel = new Label();
    @FXML private Label week1FRIDAYLabel = new Label();
    @FXML private Label week1SATURDAYLabel = new Label();
    @FXML private Label week2SUNDAYLabel = new Label();
    @FXML private Label week2MONDAYLabel = new Label();
    @FXML private Label week2TUESDAYLabel = new Label();
    @FXML private Label week2WEDNESDAYLabel = new Label();
    @FXML private Label week2THURSDAYLabel = new Label();
    @FXML private Label week2FRIDAYLabel = new Label();
    @FXML private Label week2SATURDAYLabel = new Label();
    @FXML private Label week3SUNDAYLabel = new Label();
    @FXML private Label week3MONDAYLabel = new Label();
    @FXML private Label week3TUESDAYLabel = new Label();
    @FXML private Label week3WEDNESDAYLabel = new Label();
    @FXML private Label week3THURSDAYLabel = new Label();
    @FXML private Label week3FRIDAYLabel = new Label();
    @FXML private Label week3SATURDAYLabel = new Label();
    @FXML private Label week4SUNDAYLabel = new Label();
    @FXML private Label week4MONDAYLabel = new Label();
    @FXML private Label week4TUESDAYLabel = new Label();
    @FXML private Label week4WEDNESDAYLabel = new Label();
    @FXML private Label week4THURSDAYLabel = new Label();
    @FXML private Label week4FRIDAYLabel = new Label();
    @FXML private Label week4SATURDAYLabel = new Label();
    @FXML private Label week5SUNDAYLabel = new Label();
    @FXML private Label week5MONDAYLabel = new Label();
    @FXML private Label week5TUESDAYLabel = new Label();
    @FXML private Label week5WEDNESDAYLabel = new Label();
    @FXML private Label week5THURSDAYLabel = new Label();
    @FXML private Label week5FRIDAYLabel = new Label();
    @FXML private Label week5SATURDAYLabel = new Label();
    @FXML private Label week6SUNDAYLabel = new Label();
    @FXML private Label week6MONDAYLabel = new Label();
    @FXML private Label week6TUESDAYLabel = new Label();
    @FXML private Label week6WEDNESDAYLabel = new Label();
    @FXML private Label week6THURSDAYLabel = new Label();
    @FXML private Label week6FRIDAYLabel = new Label();
    @FXML private Label week6SATURDAYLabel = new Label();

    private static ArrayList<Event> events = new ArrayList<>();



    public static void updateDayView(LocalDate date) throws FileNotFoundException {

        ArrayList<Event> events = GetData.getAllEventsForDay(date);

        sortEvents(events);

        for (int i = 0; i < dayTableView_.getItems().size(); i++) {
            dayTableView_.getItems().clear();
        }

        for (Event event : events) {
            dayTableView_.getItems().add(event);
        }
    }



    public void updateWeekView() throws FileNotFoundException {
        clearWeekView();

        LocalDate newDate = weekDatePicker.getValue();
        ArrayList<Event> events = new ArrayList<>();
        ArrayList<LocalDate> datesInWeek = new ArrayList<>();

        switch (newDate.getDayOfWeek().toString()) {
            case "SUNDAY":
                for (int i = 0; i < 7; i++) {
                    datesInWeek.add(newDate.plusDays(i));
                }
                break;
            case "MONDAY":
                for (int i = 1; i > 0; i--) {
                    datesInWeek.add(newDate.minusDays(i));
                }
                datesInWeek.add(newDate);
                for (int i = 1; i < 6; i++) {
                    datesInWeek.add(newDate.plusDays(i));
                }
                break;
            case "TUESDAY":
                for (int i = 2; i > 0; i--) {
                    datesInWeek.add(newDate.minusDays(i));
                }
                datesInWeek.add(newDate);
                for (int i = 1; i < 5; i++) {
                    datesInWeek.add(newDate.plusDays(i));
                }
                break;
            case "WEDNESDAY":
                for (int i = 3; i > 0; i--) {
                    datesInWeek.add(newDate.minusDays(i));
                }
                datesInWeek.add(newDate);
                for (int i = 1; i < 4; i++) {
                    datesInWeek.add(newDate.plusDays(i));
                }
                break;
            case "THURSDAY":
                for (int i = 4; i > 0; i--) {
                    datesInWeek.add(newDate.minusDays(i));
                }
                datesInWeek.add(newDate);
                for (int i = 1; i < 3; i++) {
                    datesInWeek.add(newDate.plusDays(i));
                }
                break;
            case "FRIDAY":
                for (int i = 5; i > 0; i--) {
                    datesInWeek.add(newDate.minusDays(i));
                }
                datesInWeek.add(newDate);
                for (int i = 1; i < 2; i++) {
                    datesInWeek.add(newDate.plusDays(i));
                }
                break;
            case "SATURDAY":
                for (int i = 6; i > 0; i--) {
                    datesInWeek.add(newDate.minusDays(i));
                }
                datesInWeek.add(newDate);
                break;
        }

        for (int i = 0; i < 7; i++) {
            events = GetData.getAllEventsForDay(datesInWeek.get(i));
            for (Event event : events) {
                if (!event.getTitle().equals("No Events Today")) {
                    weekViewLabels.get(i).setText(weekViewLabels.get(i).getText() + event.getTitle() + "\n" + event.getStartTime() + " - " + event.getEndTime() + "\nAt: " + event.getLocation() + "\n\n");
                } else {
                    weekViewLabels.get(i).setText(weekViewLabels.get(i).getText() + event.getTitle() + "\n\n");
                }
            }

            weekViewDateLabels.get(i).setText(datesInWeek.get(i).getMonth().toString() + " " + datesInWeek.get(i).getDayOfMonth());
        }
    }
    //testing git 1 2 3

    public void setUpWeekViewLabelsArrayList() {
        weekViewLabels.add(weekViewSUNDAYLabel);
        weekViewLabels.add(weekViewMONDAYLabel);
        weekViewLabels.add(weekViewTUESDAYLabel);
        weekViewLabels.add(weekViewWEDNESDAYLabel);
        weekViewLabels.add(weekViewTHURSDAYLabel);
        weekViewLabels.add(weekViewFRIDAYLabel);
        weekViewLabels.add(weekViewSATURDAYLabel);

        weekViewDateLabels.add(weekViewSUNDAYDateLabel);
        weekViewDateLabels.add(weekViewMONDAYDateLabel);
        weekViewDateLabels.add(weekViewTUESDAYDateLabel);
        weekViewDateLabels.add(weekViewWEDNESDAYDateLabel);
        weekViewDateLabels.add(weekViewTHURSDAYDateLabel);
        weekViewDateLabels.add(weekViewFRIDAYDateLabel);
        weekViewDateLabels.add(weekViewSATURDAYDateLabel);
    }

    public void clearWeekView() {
        for (Label label : weekViewLabels) {
            label.setText("");
        }
        for (Label label : weekViewDateLabels) {
            label.setText("");
        }
    }


    public void updateMonthView() throws FileNotFoundException {
        clearMonthView();

        LocalDate newDate = LocalDate.of(Integer.valueOf(yearSpinner.getValue().toString()), monthStringToInt(monthChoiceBox.getValue().toString()), 1);
        int index = dayOfWeekStringToInt(newDate.getDayOfWeek().toString()) - 1;
        events = GetData.getAllEventsForDay(newDate);
        String text = "";

        for (int i = 0; i < newDate.lengthOfMonth() + 1; i++) {
            int day = i + 1;
            text = String.valueOf(day);

            events = GetData.getAllEventsForDay(newDate.plusDays(i));

            for (Event event : events) {
                if (!event.getTitle().equals("No Events Today")) {
                    text += "\n" + event.getTitle();
                }
            }

            monthViewLabels.get(index + i).setText(text);
        }
    }

    public void monthPicked() throws FileNotFoundException {
        updateMonthView();
    }

    public void setUpMonthViewLabelsArrayList() {
        monthViewLabels.add(week1SUNDAYLabel);
        monthViewLabels.add(week1MONDAYLabel);
        monthViewLabels.add(week1TUESDAYLabel);
        monthViewLabels.add(week1WEDNESDAYLabel);
        monthViewLabels.add(week1THURSDAYLabel);
        monthViewLabels.add(week1FRIDAYLabel);
        monthViewLabels.add(week1SATURDAYLabel);
        monthViewLabels.add(week2SUNDAYLabel);
        monthViewLabels.add(week2MONDAYLabel);
        monthViewLabels.add(week2TUESDAYLabel);
        monthViewLabels.add(week2WEDNESDAYLabel);
        monthViewLabels.add(week2THURSDAYLabel);
        monthViewLabels.add(week2FRIDAYLabel);
        monthViewLabels.add(week2SATURDAYLabel);
        monthViewLabels.add(week3SUNDAYLabel);
        monthViewLabels.add(week3MONDAYLabel);
        monthViewLabels.add(week3TUESDAYLabel);
        monthViewLabels.add(week3WEDNESDAYLabel);
        monthViewLabels.add(week3THURSDAYLabel);
        monthViewLabels.add(week3FRIDAYLabel);
        monthViewLabels.add(week3SATURDAYLabel);
        monthViewLabels.add(week4SUNDAYLabel);
        monthViewLabels.add(week4MONDAYLabel);
        monthViewLabels.add(week4TUESDAYLabel);
        monthViewLabels.add(week4WEDNESDAYLabel);
        monthViewLabels.add(week4THURSDAYLabel);
        monthViewLabels.add(week4FRIDAYLabel);
        monthViewLabels.add(week4SATURDAYLabel);
        monthViewLabels.add(week5SUNDAYLabel);
        monthViewLabels.add(week5MONDAYLabel);
        monthViewLabels.add(week5TUESDAYLabel);
        monthViewLabels.add(week5WEDNESDAYLabel);
        monthViewLabels.add(week5THURSDAYLabel);
        monthViewLabels.add(week5FRIDAYLabel);
        monthViewLabels.add(week5SATURDAYLabel);
        monthViewLabels.add(week6SUNDAYLabel);
        monthViewLabels.add(week6MONDAYLabel);
        monthViewLabels.add(week6TUESDAYLabel);
        monthViewLabels.add(week6WEDNESDAYLabel);
        monthViewLabels.add(week6THURSDAYLabel);
        monthViewLabels.add(week6FRIDAYLabel);
        monthViewLabels.add(week6SATURDAYLabel);
    }

    public void clearMonthView() {
        for (Label label : monthViewLabels) {
            label.setText("");
        }
    }

    public int monthStringToInt(String monthString) {
        int monthInt = 0;
        switch (monthString) {
            case "JANUARY":
                monthInt = 1;
                break;
            case "FEBRUARY":
                monthInt = 2;
                break;
            case "MARCH":
                monthInt = 3;
                break;
            case "APRIL":
                monthInt = 4;
                break;
            case "MAY":
                monthInt = 5;
                break;
            case "JUNE":
                monthInt = 6;
                break;
            case "JULY":
                monthInt = 7;
                break;
            case "AUGUST":
                monthInt = 8;
                break;
            case "SEPTEMBER":
                monthInt = 9;
                break;
            case "OCTOBER":
                monthInt = 10;
                break;
            case "NOVEMBER":
                monthInt = 11;
                break;
            case "DECEMBER":
                monthInt = 12;
                break;

        }

        return monthInt;
    }

    public int dayOfWeekStringToInt(String dayString) {
        int dayInt = 0;
        switch (dayString) {
            case "SUNDAY":
                dayInt = 1;
                break;
            case "MONDAY":
                dayInt = 2;
                break;
            case "TUESDAY":
                dayInt = 3;
                break;
            case "WEDNESDAY":
                dayInt = 4;
                break;
            case "THURSDAY":
                dayInt = 5;
                break;
            case "FRIDAY":
                dayInt = 6;
                break;
            case "SATURDAY":
                dayInt = 7;
                break;
        }

        return dayInt;
    }


    public void updateAllViews() throws FileNotFoundException {
        dayDisplayDate = dayDatePicker.getValue();
        updateDayView(dayDisplayDate);
        updateWeekView();
        updateMonthView();
    }



    public static void sortEvents (ArrayList<Event> events) {
        //sortEventsByDate(events);
        sortEventsByStartTime(events, 0, events.size() - 1);
    }

    private static void sortEventsByStartTime(ArrayList<Event> events, int begin, int end) {
        if (begin >= end) {return;}
        int middle = (begin + end) / 2;
        sortEventsByStartTime(events, begin, middle);
        sortEventsByStartTime(events, middle + 1, end);
        sortEventsByStartTimeMergeHalves(events, begin, middle, end);
    }

    private static void sortEventsByStartTimeMergeHalves(ArrayList<Event> events, int left, int middle, int right) {
        int subArrayOne = middle - left + 1;
        int subArrayTwo = right - middle;

        ArrayList<Event> leftArray = new ArrayList<>();
        ArrayList<Event> rightArray = new ArrayList<>();

        for (int i = 0; i < subArrayOne; i++) {
            leftArray.add(i, events.get(left + i));
        }
        for (int i = 0; i < subArrayTwo; i++) {
            rightArray.add(i, events.get(middle + 1 + i));
        }

        int indexOfSubArrayOne = 0;
        int indexOfSubArrayTwo = 0;
        int indexOfMergedArray = left;

        while(indexOfSubArrayOne < subArrayOne && indexOfSubArrayTwo < subArrayTwo) {
            if (leftArray.get(indexOfSubArrayOne).getStartHours() == rightArray.get(indexOfSubArrayTwo).getStartHours()) {
                if (leftArray.get(indexOfSubArrayOne).getStartMinutes() <= rightArray.get(indexOfSubArrayTwo).getStartMinutes()) {
                    events.set(indexOfMergedArray, leftArray.get(indexOfSubArrayOne));
                    indexOfSubArrayOne++;
                } else {
                    events.set(indexOfMergedArray, rightArray.get(indexOfSubArrayTwo));
                    indexOfSubArrayTwo++;
                }
            } else if (leftArray.get(indexOfSubArrayOne).getStartHours() < rightArray.get(indexOfSubArrayTwo).getStartHours()) {
                events.set(indexOfMergedArray, leftArray.get(indexOfSubArrayOne));
                indexOfSubArrayOne++;
            } else {
                events.set(indexOfMergedArray, rightArray.get(indexOfSubArrayTwo));
                indexOfSubArrayTwo++;
            }

            indexOfMergedArray++;
        }

        while (indexOfSubArrayOne < subArrayOne) {
            events.set(indexOfMergedArray, leftArray.get(indexOfSubArrayOne));
            indexOfSubArrayOne++;
            indexOfMergedArray++;
        }
        while (indexOfSubArrayTwo < subArrayTwo) {
            events.set(indexOfMergedArray, rightArray.get(indexOfSubArrayTwo));
            indexOfSubArrayTwo++;
            indexOfMergedArray++;
        }
    }



    public void onAddEventButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CalendarApplication.class.getResource("add-event-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 728, 350);

        newWindow = new Stage();
        newWindow.setTitle("Add Event");
        newWindow.setScene(scene);

        newWindow.setX(500);
        newWindow.setY(250);

        newWindow.show();
    }

    public void onEditEventButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CalendarApplication.class.getResource("edit-event-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 728, 376);

        newWindow = new Stage();
        newWindow.setTitle("Edit Event");
        newWindow.setScene(scene);

        newWindow.setX(500);
        newWindow.setY(250);

        newWindow.show();
    }

    public void onRemoveEventButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CalendarApplication.class.getResource("remove-event-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 501, 183);

        newWindow = new Stage();
        newWindow.setTitle("Edit Event");
        newWindow.setScene(scene);

        newWindow.setX(500);
        newWindow.setY(250);

        newWindow.show();
    }

    public void dayViewDatePicked() throws FileNotFoundException {
        dayDisplayDate = dayDatePicker.getValue();
        updateDayView(dayDisplayDate);
    }

    public void weekViewDatePicked() throws FileNotFoundException {
        updateWeekView();
    }
}
