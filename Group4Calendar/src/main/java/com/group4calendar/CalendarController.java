package com.group4calendar;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CalendarController implements Initializable  {

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

        try {
            updateToDoList();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        LocalDate dateToday = LocalDate.now();

        try {
            updateDayView(dateToday.toString());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



    //to do list stuff
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
        FXMLLoader fxmlLoader = new FXMLLoader(CalendarApplication.class.getResource("add-to-do-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 309, 135);

        newWindow = new Stage();
        newWindow.setTitle("Add To Do List Item");
        newWindow.setScene(scene);

        newWindow.setX(500);
        newWindow.setY(250);

        newWindow.show();
        updateToDoList();
    }

    public void onRemoveToDoButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CalendarApplication.class.getResource("remove-to-do-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 309, 135);

        newWindow = new Stage();
        newWindow.setTitle("Remove To Do List Item");
        newWindow.setScene(scene);

        newWindow.setX(500);
        newWindow.setY(250);

        newWindow.show();
    }



    //for testing
    static Event event1 = new Event("one", "2022-05-23", "01:00 AM", "01:00 PM", "loc1", "note1");
    static Event event2 = new Event("two", "2022-05-23", "02:00 AM", "02:00 PM", "loc2", "note2");
    static Event event3 = new Event("three", "2022-05-23", "03:00 AM", "03:00 PM", "loc3", "note3");
    static Event noon = new Event("noon", "2022-05-23", "12:00 AM", "12:00 PM", "locNoon", "noteNoon");


    //calendar stuff
    @FXML private TableView dayTableView = new TableView();
    private static TableView dayTableView_ = new TableView();

    @FXML private TableColumn<Event, String> titleDayTableColumn = new TableColumn<>();
    @FXML private TableColumn<Event, String> startTimeDayTableColumn = new TableColumn<>();
    @FXML private TableColumn<Event, String> endTimeDayTableColumn = new TableColumn<>();
    @FXML private TableColumn<Event, String> notesDayTableColumn = new TableColumn<>();
    @FXML private TableColumn<Event, String> locationDayTableColumn = new TableColumn<>();

    private static ArrayList<Event> events = new ArrayList<>();

    public static void updateDayView(String date) throws FileNotFoundException {
        //for testing
        events.add(event1);
        events.add(event2);
        events.add(event3);
        events.add(noon);

        ArrayList<Event> events = GetData.getAllEventsForDay(date);

        sortEvents(events);

        for (int i = 0; i < dayTableView_.getItems().size(); i++) {
            dayTableView_.getItems().clear();
        }

        for (Event event : events) {
            dayTableView_.getItems().add(event);
        }
    }

    public void updateWeekView() {

    }

    public void updateMonthView() {

    }

    public static void sortEvents (ArrayList<Event> events) {
        //sortEventsByDate(events);
        sortEventsByStartTime(events, 0, events.size() - 1);
    }

    private void sortEventsByDate(ArrayList<Event> events) {
        int i = 0;
        int j = 0;
        if (events.get(i).getYear() == events.get(j).getYear()) {
            if (events.get(i).getMonth() == events.get(j).getMonth()) {
                if (events.get(i).getDay() == events.get(j).getDay()) {

                }
            } else {

            }
        } else {

        }
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

    }

    public void onRemoveEventButtonClick() throws IOException {

    }

    public void dayViewDatePicked() throws IOException {

    }
}
