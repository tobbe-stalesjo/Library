package com.company;

import java.io.Serializable;

public class Book implements Serializable {

    private String title;
    private String author;
    private String description;
    private boolean available;

    // timestamp
    // localdatetime

    public Book(String title, String author, String descrition) {
        this.title = title;
        this.author = author;
        this.description = descrition;
        setAvailable(true);
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean getAvailable(){
        return available;
    }

    @Override
    public String toString() {
        return "Title: " + title +
                " Author: " + author +
                " Description: " + description;
    }
}
