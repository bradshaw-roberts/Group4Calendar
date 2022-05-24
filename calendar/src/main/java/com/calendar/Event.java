package com.example.calendar;

public class Event {
    private String title, location, notes, date, startTime, endTime;

    //default constructor
    public Event() { }

    //input all variables
    public Event(String title, String date, String startTime, String endTime, String location, String notes)  {
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
    public String getDate() {return date;}
    public String getStartTime() {return startTime;}
    public String getEndTime() {return endTime;}
    public String getLocation() {return location;}
    public String getNotes() {return notes;}

    //specialty getters
    public int getYear() {
        int num = 0;

        String string = date.substring(0, 4);
        num = Integer.valueOf(string);

        return num;
    }
    public int getMonth() {
        int num = 0;

        String string = date.substring(5, 7);
        num = Integer.valueOf(string);

        return num;
    }
    public int getDay() {
        int num = 0;

        String string = date.substring(8);
        num = Integer.valueOf(string);

        return num;
    }
    public int getStartHours() {
        int num = 0;

        String string = startTime.substring(0, 2);
        num = Integer.valueOf(string);

        if (startTime.substring(6).equals("AM")) {
            if (num == 12) {
                num = 0;
            }
        } else if (startTime.substring(6).equals("PM")){
            if (num != 12) {
                num += 12;
            }
        }

        return num;
    }
    public int getStartMinutes() {
        int num = 0;

        String string = startTime.substring(3, 5);
        num = Integer.valueOf(string);

        return num;
    }
    public int getEndHours() {
        int num = 0;

        String string = endTime.substring(0, 2);
        num = Integer.valueOf(string);

        if (endTime.substring(6).equals("AM")) {
            if (num == 12) {
                num = 0;
            }
        } else if (endTime.substring(6).equals("PM")){
            if (num != 12) {
                num += 12;
            }
        }

        return num;
    }
    public int getEndMinutes() {
        int num = 0;

        String string = endTime.substring(3, 5);
        num = Integer.valueOf(string);

        return num;
    }

    //setters
    public void setTitle(String newTitle) {this.title = newTitle;}
    public void setLocation(String newLocation) {this.location = newLocation;}
    public void setNotes(String newNotes) {this.notes = newNotes;}
    public void setStartTime(String newStartTime) {this.startTime = newStartTime;}
    public void setEndTime(String newEndTime) {this.endTime = newEndTime;}
    public void setDate(String newDate) {this.date = newDate;}
}
