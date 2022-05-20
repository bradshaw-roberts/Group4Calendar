package com.example.calendar;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class ToDoList() {

  //basic layout of what methods we need in this java file, can be changed 
  //read and write to ToDoList.txt in the resources file
  
  public static ArrayList getAll() {
    //return all items from the file in an ArrayList
    ArrayList<String> items = new ArrayList<>();
    Scanner fileReader = new Scanner("ToDoList.txt");
    while (fileReader.hasNextLine()) {
      items.add(fileReader.nextLine());
      }
  
    return items;
  }
  
  public static void add (String name) {
    //given the name of the new to do list item add it to the file.
    FileWriter listWriter = new FileWriter("ToDoList.txt");
    listWriter.append(name + "\n");
    listWriter.close();
  }

  public static void remove (String name) {
    //given a string see if the item is in the file and if it is, remove it.
    File importFile = new File("ToDoList.txt");
    //Creates a new temp file, to later replace the original
    File tempFile = new File("ToDoList.tmp");
    BufferedReader br = new BufferedReader(new FileReader(importFile));
    PrintWriter listWriter = new PrintWriter(tempFile);
    String line = null;

    //Reads the original ToDoList into the new temp one, excluding the
    //line to be deleted
    while ((line = br.readLine()) != null) {
      if (!line.trim().equal(name)) {
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