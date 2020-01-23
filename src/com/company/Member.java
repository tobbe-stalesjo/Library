package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Member implements Serializable {

    private String name;
    private ArrayList<Book> borrowedBooks = new ArrayList<>();


    public Member(String name) {
        this.name = name;
    }

    public void logInMember(String user, ArrayList<Book> books) {
        boolean running = true;
        Scanner scanner = new Scanner(System.in);

        while (running) {
            System.out.println("Welcome " + user + " to My Pages:");
            System.out.println("Please make a choice: ");
            System.out.println("1. Borrow a book");
            System.out.println("2. See what book I have borrowed right now");
            System.out.println("3. Return a book");
            System.out.println("4. Sort books");
            System.out.println("5. Log out and save");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    try {
                        System.out.println("Enter what book you want to borrow: ");
                        String input = scanner.nextLine();
                        Book book = searchForBook(input, books);
                        borrowBook(book);
                    } catch (Exception e) {
                        System.out.println("Book can not be found");
                    }
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
                    System.out.println("How do you want to sort?");
                    System.out.println("1. Sort by title");
                    System.out.println("2. Sort by author");
                    String sortType = scanner.nextLine();
                    if (sortType.equals("1")) {
                        sortBooksByTitle(books);
                    } else if (sortType.equals("2")) {
                        sortBooksByAuthor(books);
                    }
                    else {
                        System.out.println("Invalid number, try again");
                }
                break;
                case "5":
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

    public void seeMyBooks() {
        if (borrowedBooks.size() == 0)
            System.out.println("You don't have any borrowed books right now.\n");
        else {
            System.out.println("You have current books borrowed\n");
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
            if (book.getTitle().toLowerCase().contains(input.toLowerCase()) || book.getAuthor().toLowerCase().contains(input.toLowerCase())) {
                if (book.getAvailable()) {
                    return book;
                }
            }
        }
        return null;
    }

    private void sortBooksByTitle(ArrayList<Book> books) {
        books.sort((p1, p2) -> {
            return p1.getTitle().compareTo(p2.getTitle());
        });
        System.out.println("\nSorted by age;");
        for (Book book : books) {
            System.out.printf("Name: %s, Age: %s\n", book.getTitle(), book.getTitle());
        }
    }

    private void sortBooksByAuthor(ArrayList<Book> books) {
        books.sort((p1, p2) -> {
            return p1.getAuthor().compareTo(p2.getAuthor());
        });
        System.out.println("\nSorted by age;");
        for (Book book : books) {
            System.out.printf("Name: %s, Age: %s\n", book.getAuthor(), book.getAuthor());
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Member " + "name " + name;
    }
}
