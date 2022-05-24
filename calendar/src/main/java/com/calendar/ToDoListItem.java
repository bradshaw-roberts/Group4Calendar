package com.example.calendar;

public class ToDoListItem {
    private static String itemName;

    public ToDoListItem(String itemName) {this.itemName = itemName;}

    public String getItemName () {return itemName;}

    public void setItemName(String itemName) {this.itemName = itemName;}
}
