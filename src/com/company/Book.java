package com.company;

import java.io.Serializable;

public class Book implements Serializable {

    private String title;
    private String author;
    private String descrition;
    private boolean available;


    public Book(String title, String author, String descrition) {
        this.title = title;
        this.author = author;
        this.descrition = descrition;
        setAvailable(true);
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescrition() {
        return descrition;
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
                " Available: " + available;
    }
}
