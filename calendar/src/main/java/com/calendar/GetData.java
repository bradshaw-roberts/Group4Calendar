package com.example.calendar;

import java.util.*;
import java.util.Scanner;
import java.io.*;  
import java.io.File;

public class GetData {
  //read and write to the files

  //basic layout of what methods we need in this java file, can be changed 
  
  public static void addEvent(Event event) {
        //add the new event to the file
    }

    public static void removeEvent(Event event) {
        //remove the old event from the file
    }
    
    public static void editEvent(Event oldEvent, Event newEvent) {
        //this will call addEvent and send it the newEvent
        //also call removeEvent and send it the oldEvent
    }

    public static void getAllEventsForDay(int date) {
        //get all events on a certain date
      String dateF = date + ".txt";
      Scanner scan = new Scanner(new File(dateF));
      while (scan.hasNextLine()) {
        String line = scan.nextLine();
        if(line == null) {
          String title = scan.nextLine();
          int start = scan.nextInt();
          int end = scan.nextInt();
          String location = scan.nextLine();
          String notes = scan.nextLine();
          
        }else{}
      }
    }

    public static void getAllEventsForWeek(int date, String dayOfTheWeek) {
        /*
        get all events for a week by calling getAllEventsForDay()
        for every day in the week in which the given day resides.
        */

    }

    public static void getAllEventsForMonth(int date, String dayOfTheWeek) {
        /*
        get all events for a week by calling getAllEventsForWeek()
        for every week in the month in which the given day resides.
        */
    }
   
}
