package com.group4calendar;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CalendarController implements Initializable  {

    public static LocalDate dayDisplayDate;

    @FXML DatePicker dayDatePicker = new DatePicker();

    @FXML private TextField toDoListTextField = new TextField();

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
        dayDisplayDate = dateToday;
        dayDatePicker.setValue(dateToday);

        try {
            updateDayView(dayDisplayDate);
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
//        FXMLLoader fxmlLoader = new FXMLLoader(CalendarApplication.class.getResource("add-to-do-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 309, 135);
//
//        newWindow = new Stage();
//        newWindow.setTitle("Add To Do List Item");
//        newWindow.setScene(scene);
//
//        newWindow.setX(500);
//        newWindow.setY(250);
//
//        newWindow.show();
//        updateToDoList();

        ToDoList.addItem(toDoListTextField.getText());

        toDoListTextField.clear();

        CalendarController.updateToDoList();
    }

    public void onRemoveToDoButtonClick() throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(CalendarApplication.class.getResource("remove-to-do-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 309, 135);
//
//        newWindow = new Stage();
//        newWindow.setTitle("Remove To Do List Item");
//        newWindow.setScene(scene);
//
//        newWindow.setX(500);
//        newWindow.setY(250);
//
//        newWindow.show();

        ToDoList.removeItem(toDoListTextField.getText());

        toDoListTextField.clear();

        CalendarController.updateToDoList();
    }





    //calendar stuff
    @FXML private TableView dayTableView = new TableView();
    private static TableView dayTableView_ = new TableView();

    @FXML private TableColumn<Event, String> titleDayTableColumn = new TableColumn<>();
    @FXML private TableColumn<Event, String> startTimeDayTableColumn = new TableColumn<>();
    @FXML private TableColumn<Event, String> endTimeDayTableColumn = new TableColumn<>();
    @FXML private TableColumn<Event, String> notesDayTableColumn = new TableColumn<>();
    @FXML private TableColumn<Event, String> locationDayTableColumn = new TableColumn<>();

    private static ArrayList<Event> events = new ArrayList<>();

    public static void updateDayView(LocalDate date) throws FileNotFoundException {

        ArrayList<Event> events = GetData.getAllEventsForDay(date, new ArrayList<>());

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

    public void dayViewDatePicked() throws FileNotFoundException {
        dayDisplayDate = dayDatePicker.getValue();
        updateDayView(dayDisplayDate);
    }
}
