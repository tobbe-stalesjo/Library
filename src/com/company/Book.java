package com.company;

public class Book {

    private String title;
    private String author;
    private boolean available;


    public Book(String title, String author) {
        this.title = title;
        this.author = author;
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
        return "title " + title + '\'' +
                ", author " + author + '\'' +
                ", available " + available;
    }
}
