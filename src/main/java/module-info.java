module com.example.group4calendar {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.group4calendar to javafx.fxml;
    exports com.group4calendar;
}