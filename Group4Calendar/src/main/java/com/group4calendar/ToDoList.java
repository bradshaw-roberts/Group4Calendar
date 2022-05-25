package com.group4calendar;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ToDoList {

    //basic layout of what methods we need in this java file, can be changed
    //read and write to ToDoList.txt in the resources file

    public static ArrayList getAll() throws FileNotFoundException {
        //return all items from the file in an ArrayList

        ArrayList<String> items = new ArrayList<>();
        Scanner fileReader = new Scanner(new File ("src/main/resources/ToDoList.txt"));

        do {
            items.add(fileReader.nextLine());
        } while (fileReader.hasNextLine());

        return items;
    }

    public static void addItem(String name) throws IOException {
        //given the name of the new to do list item add it to the file.

        BufferedWriter listWriter = new BufferedWriter(new FileWriter(new File("src/main/resources/ToDoList.txt"), true));

        listWriter.append("\n" + name);

        listWriter.close();
    }

    public static void removeItem(String name) throws IOException {
        //given a string see if the item is in the file and if it is, remove it.

        File importFile = new File("src/main/resources/ToDoList.txt");
        //Creates a new temp file, to later replace the original
        File tempFile = new File("src/main/resources/tempToDoList.txt");

        BufferedReader br = new BufferedReader(new FileReader(importFile));
        PrintWriter listWriter = new PrintWriter(tempFile);
        String line = null;

        //Reads the original ToDoList into the new temp one, excluding the
        //line to be deleted
        while ((line = br.readLine()) != null) {
            if (!line.trim().equals(name)) {
                listWriter.println(line);
                listWriter.flush();
            }
        }
        listWriter.close();
        br.close();

        if (!importFile.delete()) {
            System.out.println("Could not delete file.");
            return;
        }

        if (!tempFile.renameTo(importFile)) {
            System.out.println("Could not rename temp file.");
        }
    }
}
