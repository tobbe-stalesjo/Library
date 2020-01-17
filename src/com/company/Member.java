package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Member implements Serializable {

    transient Scanner scanner = new Scanner(System.in);
    private String name;
    private ArrayList<Book> borrowedBooks = new ArrayList<>();


    public Member(String name) {
        this.name = name;
    }

    public void logInMember(String user, ArrayList<Book> books) {
        boolean running = true;

        while (running) {
            System.out.println("Welcome " + user + " to My Pages:");
            System.out.println("Please make a choice: ");
            System.out.println("1. Borrow a book");
            System.out.println("2. See what book I have borrowed right now");
            System.out.println("3. Return a book");
            System.out.println("4. Log out");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("Enter what book you want to borrow: ");
                    String input = scanner.nextLine();
                    Book book = searchForBook(input, books);
                    borrowBook(book);
                    break;
                case "2":
                    seeMyBooks();
                    break;
                case "3":
                    if (borrowedBooks.size() == 0) {
                        System.out.println("You don't have any borrowed books right now.");
                        break;
                    }
                    System.out.println("Enter the title of the book you are returning");
                    String title = scanner.nextLine();
                    returnBook(searchForBook(title, borrowedBooks));
                    break;
                case "4":
                    running = false;
                    break;
                default:
                    System.out.println("Please choose a valid number");
            }
        }
    }

    private void borrowBook(Book book) {
        borrowedBooks.add(book);
        book.setAvailable(false);
        System.out.println("You have borrowed following book " + book);
    }


    private void seeMyBooks() {
        if (borrowedBooks.size() == 0)
            System.out.println("You don't have any borrowed books right now.");
        else {
            System.out.println("You have current books borrowed");
            for (Book book : borrowedBooks) {
                System.out.println(book);
            }
        }
    }

    private void returnBook(Book book) {
            borrowedBooks.remove(book);
            book.setAvailable(true);
        System.out.println("You have returned book: " + book);
    }

    public Book searchForBook(String input, ArrayList<Book> books) {
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(input.toLowerCase()) || book.getAuthor().toLowerCase().contains(input.toLowerCase())) {      // Kan jag här även lägga till isAvailable?
                // && book.getAvailable()  Denna funkar inte så länge man har toLowerCase?
                return book;
                // Kan man här även visa om den finns tillänglig att låna och lånad den direkt?
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Member " + "name " + name;
    }
}
