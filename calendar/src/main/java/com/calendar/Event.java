package com.example.calendar;

public class Event {
    private String title, location, notes;
    private int startTime, endTime, date;
    //0800 1524  20220519

    //default constructor
    public Event() { }

    //input all variables
    public Event(String title, int date, int startTime, int endTime, String location, String notes)  {
        this.title = title;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.notes = notes;
    }

    public String toStringForFile() {
        return title + "\n" + startTime + "\n" + endTime + "\n" + location + "\n" + notes + "\n";
    }

    //getters
    public String getTitle() {return title;}
    public int getDate() {return date;}
    public int getStartTime() {return startTime;}
    public int getEndTime() {return endTime;}
    public String getLocation() {return location;}
    public String getNotes() {return notes;}

    //setters
    public void setTitle(String newTitle) {this.title = newTitle;}
    public void setLocation(String newLocation) {this.location = newLocation;}
    public void setNotes(String newNotes) {this.notes = newNotes;}
    public void setStartTime(int newStartTime) {this.startTime = newStartTime;}
    public void setEndTime(int newEndTime) {this.endTime = newEndTime;}
    public void setDate(int newDate) {this.date = newDate;}
}