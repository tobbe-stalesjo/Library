package com.company;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Admin implements Serializable {

    transient Scanner scanner = new Scanner(System.in);


    public void logInAsAdmin(ArrayList<Member> members, ArrayList<Book> books) {
        boolean running = true;

        while (running) {
            System.out.println("Welcome to Admin page");
            System.out.println("Please make a choice: ");
            System.out.println("1. Add new books");
            System.out.println("2. Remove a book");
            System.out.println("3. List of all members");
            System.out.println("4. Search for a member");
            System.out.println("5. See all borrowed books.");
            System.out.println("6. See all borrowed books by user");
            System.out.println("7. Log out and save");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addNewBook(books);
                    break;
                case "2":
                    removeBook(books);
                    break;
                case "3":
                    listOfMembers(members);
                    break;
                case "4":
                    System.out.println("Enter the members name: ");
                    String name = scanner.nextLine();
                    Member member = findUser(name, members);
                    System.out.println(member);
                    break;
                case "5":
                    seeAllBorrowedBook(books);
                    break;
                case "6":
                    seeAllBorrowedBookByUser(members);
                    break;
                case "7":
                    running = false;
                    break;
                default:
                    System.out.println("Please choose a valid number");
            }
        }
    }

    private void addNewBook(ArrayList<Book> books) {
        System.out.println("Enter the title of the book: ");
        String title = scanner.nextLine();
        System.out.println("Enter the author of the book: ");
        String author = scanner.nextLine();
        System.out.println("Enter a short description of the book: ");
        String description = scanner.nextLine();
        books.add(new Book(title, author, description));
    }

    private void removeBook(ArrayList<Book> books) {
        System.out.println("Enter what book you want to remove: ");
        String input = scanner.nextLine();
        Book book = searchForBook(books, input);
        books.remove(book);
        System.out.println("You have removed book " + book);
    }

    public void listOfMembers(ArrayList<Member> members) {
        for (Member member : members) {
            System.out.println(member);
        }
    }

    public Member findUser(String user, ArrayList<Member> members) {
        for (Member member : members) {
            if (member.getName().equals(user))
                return member;
        }
        return null;
    }

    private void seeAllBorrowedBook(ArrayList<Book> books) {
        for (Book book : books) {
            if (!book.getAvailable()) {
                System.out.println(book);
            }
        }
    }

    private void seeAllBorrowedBookByUser(ArrayList<Member> members) {
        System.out.println("Enter the user you want to see: ");
        String name = scanner.nextLine();
        Member user = findUser(name, members);
        user.seeMyBooks();
    }

    public Book searchForBook(ArrayList<Book> books, String input) {
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(input.toLowerCase()) || book.getAuthor().toLowerCase().contains(input.toLowerCase())) {
                return book;
            }
        }
        return null;
    }
}
