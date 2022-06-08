package com.group4calendar;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ToDoList {

    public static ArrayList getAll() throws FileNotFoundException {
        //return all items from the file in an ArrayList

        ArrayList<String> items = new ArrayList<>();
        Scanner fileReader = new Scanner(new File ("src/main/resources/ToDoList.txt"));
        if (fileReader.hasNextLine()) {
            do {
                items.add(fileReader.nextLine());
            } while (fileReader.hasNextLine());
        }
        fileReader.close();

        return items;
    }

    public static void addItem(String name) throws IOException {
        //given the name of the new to do list item add it to the file.

        PrintWriter listWriter = new PrintWriter(new FileWriter(new File("src/main/resources/ToDoList.txt"), true));

        listWriter.println(name);

        listWriter.close();
    }

    public static void removeItem(String name) throws IOException {
        //given a string see if the item is in the file and if it is, remove it.

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
        String removeLine = name;

        //Reads the original ToDoList into the new temp one, excluding the
        //line to be deleted
        while ((currentLine = br.readLine()) != null) {
            String trimmedLine = currentLine.trim();
            if(trimmedLine.equalsIgnoreCase(name)) continue;
            bw.println(currentLine);
        }

        bw.close();
        br.close();

//        System.out.println("Renaming: " + tempFile.getAbsolutePath() + " --> " + importFile.getAbsolutePath()); //debug testing

        try{
            Files.delete(importFile.toPath());
            Files.move(tempFile.toPath(), importFile.toPath());
        }
        catch (Exception e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
    }
}
