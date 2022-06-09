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

    public static void removeEvent(Event event) {
        //remove the old event from the file

        File importFile = new File("src/main/resources/ToDoList.txt");
        if(!importFile.isFile()){
            System.out.println("ToDoList.txt does not exist");
            return;
        }
        //Creates a new temp file, to later replace the original
        File tempFile = new File("src/main/resources/temp.tmp");

        BufferedReader br = new BufferedReader(new FileReader(importFile));
        PrintWriter bw = new PrintWriter(new FileWriter(tempFile));

        String currentLine;

        //Reads the original ToDoList into the new temp one, excluding the
        //line to be deleted
        while ((currentLine = br.readLine()) != null) {
            String trimmedLine = currentLine.trim();
            if(trimmedLine.equalsIgnoreCase(name)) continue;
            bw.println(currentLine);
        }

        //close files
        bw.close();
        br.close();

        //Delete old ToDoList.txt and replace it with temp.tmp
        try{
            Files.delete(importFile.toPath());
            Files.move(tempFile.toPath(), importFile.toPath());
        }
        catch (Exception e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }

    }

//    public static void editEvent(Event oldEvent, Event newEvent) {
//        //this will call addEvent and send it the newEvent
//        //also call removeEvent and send it the oldEvent
//    }
//
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

                Event event = new Event(title, day, date.toString(), start, end, location, notes);
                events.add(event);
            }
        }

        return events;
    }
}