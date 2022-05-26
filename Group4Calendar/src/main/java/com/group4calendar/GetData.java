package com.group4calendar;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class GetData {
    //read and write to the files

    //basic layout of what methods we need in this java file, can be changed

    public static void addEvent(Event event) throws IOException {
        //add the new event to the file
        BufferedWriter listWriter = new BufferedWriter(new FileWriter(new File("src/main/resources/EventsDataFiles/" + event.getDate() + ".txt"), true));

        listWriter.append("\n" + "----------\n" + event.toStringForFile());

        listWriter.close();
    }

//    public static void removeEvent(Event event) {
//        //remove the old event from the file
//    }
//
//    public static void editEvent(Event oldEvent, Event newEvent) {
//        //this will call addEvent and send it the newEvent
//        //also call removeEvent and send it the oldEvent
//    }
//
    public static ArrayList<Event> getAllEventsForDay(LocalDate date, ArrayList<Event> events) throws FileNotFoundException {
        //get all events on a certain date

        String dateF = date.toString() + ".txt";

        Scanner scan;

        try {
            scan = new Scanner(new File("src/main/resources/EventsDataFiles/" + dateF));
        } catch (FileNotFoundException e) {
            scan = new Scanner(new File("src/main/resources/EventsDataFiles/default.txt"));
        }


        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            if(line.equals("----------")) {
                String title = scan.nextLine();
                String day = scan.nextLine();
                String start = scan.nextLine();
                String end = scan.nextLine();
                String location = scan.nextLine();
                String notes = scan.nextLine();

                Event event = new Event(title, day, date.toString(), start, end, location, notes);
                events.add(event);
            }
        }

        return events;
    }

    public static ArrayList<Event> getAllEventsForWeek(LocalDate date, ArrayList<Event> events) throws FileNotFoundException {
        /*
        get all events for a week by calling getAllEventsForDay()
        for every day in the week in which the given day resides.

        dayOfTheWeek = addEventDateInput.getValue().getDayOfWeek().toString()
        */

        ArrayList<LocalDate> datesInWeek = new ArrayList<>();

        switch (date.getDayOfWeek().toString()) {
            case "SUNDAY":
                datesInWeek.add(date);
                for (int i = 1; i < 7; i++) {
                    datesInWeek.add(date.plusDays(i));
                }
                break;
            case "MONDAY":
                for (int i = 1; i > 0; i--) {
                    datesInWeek.add(date.minusDays(i));
                }
                datesInWeek.add(date);
                for (int i = 1; i < 6; i++) {
                    datesInWeek.add(date.plusDays(i));
                }
                break;
            case "TUESDAY":
                for (int i = 2; i > 0; i--) {
                    datesInWeek.add(date.minusDays(i));
                }
                datesInWeek.add(date);
                for (int i = 1; i < 5; i++) {
                    datesInWeek.add(date.plusDays(i));
                }
                break;
            case "WEDNESDAY":
                for (int i = 3; i > 0; i--) {
                    datesInWeek.add(date.minusDays(i));
                }
                datesInWeek.add(date);
                for (int i = 1; i < 4; i++) {
                    datesInWeek.add(date.plusDays(i));
                }
                break;
            case "THURSDAY":
                for (int i = 4; i > 0; i--) {
                    datesInWeek.add(date.minusDays(i));
                }
                datesInWeek.add(date);
                for (int i = 1; i < 3; i++) {
                    datesInWeek.add(date.plusDays(i));
                }
                break;
            case "FRIDAY":
                for (int i = 5; i > 0; i--) {
                    datesInWeek.add(date.minusDays(i));
                }
                datesInWeek.add(date);
                for (int i = 1; i < 2; i++) {
                    datesInWeek.add(date.plusDays(i));
                }
                break;
            case "SATURDAY":
                for (int i = 6; i > 0; i--) {
                    datesInWeek.add(date.minusDays(i));
                }
                datesInWeek.add(date);
                break;
        }

        for (LocalDate nextDate : datesInWeek) {
            getAllEventsForDay(nextDate, events);
        }

        return events;
    }
//
//    public static ArrayList<Event> getAllEventsForMonth(int date, String dayOfTheWeek) {
//      /*
//      get all events for a week by calling getAllEventsForWeek()
//      for every week in the month in which the given day resides.
//      */
//    }

}

