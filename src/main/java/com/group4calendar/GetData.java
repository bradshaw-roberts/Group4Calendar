package com.group4calendar;

import java.io.*;
import java.nio.file.Files;
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

    public static void removeEvent(Event event) throws IOException {
        //remove the old event from the file

        File importFile = new File("src/main/resources/EventsDataFiles/" + event.getDate() + ".txt");
        if(!importFile.isFile()){
            System.out.println(event.getDate() + ".txt does not exist");
            return;
        }
        //Creates a new temp file, to later replace the original
        File tempFile = new File("src/main/resources/EventsDataFiles/temp.tmp");

        BufferedReader br = new BufferedReader(new FileReader(importFile));
        PrintWriter bw = new PrintWriter(new FileWriter(tempFile));

        String currentLine;

        //Reads the original file into the new temp one, excluding the
        //line to be deleted
        while ((currentLine = br.readLine()) != null) {
            String trimmedLine = currentLine.trim();
            if(trimmedLine.equals("----------")) {
                currentLine = br.readLine();
                trimmedLine = currentLine.trim();
                if (trimmedLine.equals(event.getTitle())) {
                    for (int i = 0; i < 7; i++) {
                        br.readLine();
                    }
                } else {
                    bw.println("----------");
                    bw.println(currentLine);
                }
            } else {
                bw.println(currentLine);
            }
        }

        //close files
        bw.close();
        br.close();



        br = new BufferedReader(new FileReader(tempFile));
        bw = new PrintWriter(new FileWriter(importFile));

        //Reads the original file into the new temp one, excluding the
        //line to be deleted
        while ((currentLine = br.readLine()) != null) {
            bw.println(currentLine);
        }

        //close files
        bw.close();
        br.close();

        Files.delete(tempFile.toPath());
    }

    public static void editEvent(Event oldEvent, Event newEvent) throws IOException {
        //this will call addEvent and send it the newEvent
        //also call removeEvent and send it the oldEvent

        removeEvent(oldEvent);
        addEvent(newEvent);
    }

    public static ArrayList<Event> getAllEventsForDay(LocalDate date) throws FileNotFoundException {
        //get all events on a certain date

        String dateF = date.toString() + ".txt";

        Scanner scan;

        ArrayList<Event> events = new ArrayList<>();

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

                Event event = new Event(title, date.toString(), day, start, end, location, notes);
                events.add(event);
            }
        }

        if (events.isEmpty()) {
            scan = new Scanner(new File("src/main/resources/EventsDataFiles/default.txt"));

            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                if(line.equals("----------")) {
                    String title = scan.nextLine();
                    String day = scan.nextLine();
                    String start = scan.nextLine();
                    String end = scan.nextLine();
                    String location = scan.nextLine();
                    String notes = scan.nextLine();

                    Event event = new Event(title, date.toString(), day, start, end, location, notes);
                    events.add(event);
                }
            }
        }

        return events;
    }
}