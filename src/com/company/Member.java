package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Member {

    transient Scanner scanner = new Scanner(System.in);
    private String name;
    private ArrayList<Book> borrowedBooks = new ArrayList<>();
    Library library;

    public Member(String name) {
        this.name = name;
    }

    void logInMember(String user) {
        boolean running = true;

        while (running) {
            System.out.println("Welcome "+ user + " to My Pages:");
            System.out.println("Please make a choice: ");
            System.out.println("1. Borrow a book");
            System.out.println("2. See what book I have borrowed right now");
            System.out.println("3. Return a book");
            System.out.println("4. Log out");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.println("Enter what book you want to borrow: ");
                    String input = scanner.nextLine();
                    borrowBook(input);
                    break;
                case 2:
                    seeMyBooks();
                    break;
                case 3:
                    System.out.println("Enter the title of the book you are returning");
                    String title = scanner.nextLine();
                    returnBook(title);
                    break;
                case 4:
                    running = false;
                    library.startMenu();
                    // Här ska du komma tillbaka till huvudmenu igen.
                    break;
            }
        }
    }

    private void borrowBook(String input) {
        Book book = library.searchForBook(input);
        borrowedBooks.add(book);    // funkar denna?
        book.setAvailable(false);
        // Här måste jag fixa så det räcker med att man skriver in bokens namn
        // Här behöver man sätta att boken är utlånad boolean?
        // Samt att den sparas
    }


    private void seeMyBooks() {
        if (borrowedBooks.size() == 0)
            return;

        System.out.println("You have current books borrowed");
        for (Book book : borrowedBooks) {
            System.out.println(book);
        }
    }

    private void returnBook(String title) {
        for (Book book : borrowedBooks) {
            if (book.getTitle().equals(title)) {
                borrowedBooks.remove(book);
                book.setAvailable(true);
                // Behöver man uppdatera den sparade listan här?
            }
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
