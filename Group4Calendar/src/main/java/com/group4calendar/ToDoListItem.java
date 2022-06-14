/**
 * This class is used to input toDoList items into the table on
 * the interface. Only objects can be added to the TableView.
 *
 * CIS-2999 Summer I Semester
 * Oakland University
 * Group 4 Calendar
 *
 * Brenden Nagey
 * Ravi Prajapati
 * Bradshaw Roberts
 * Nora Sinishtaj
 * V VanCamp
 */

package com.group4calendar;

public class ToDoListItem {
    private String itemName;

    // constructor
    public ToDoListItem(String itemName) {this.itemName = itemName;}

    // getters
    public String getItemName() {return itemName;}

    // setter
    public void setItemName(String itemName) {this.itemName = itemName;}
}
