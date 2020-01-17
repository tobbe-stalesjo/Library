package com.company;

import java.io.File;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Library implements Serializable {

    transient Scanner scanner = new Scanner(System.in);
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Member> members = new ArrayList<>();
    Admin admin;
    Member member;

    public Library() {
        loadProgram();
        startMenu();
    }

    public void startMenu() {
        boolean running = true;

        while (running) {
            System.out.println("Welcome to the Library, please make a choice: ");
            System.out.println("Please log in to borrow or return a book. ");
            System.out.println("1. See list of all books");
            System.out.println("2. See list of available books");
            System.out.println("3. Search for a book");
            System.out.println("4. Log in to My Pages");
            System.out.println("5. Log in as Admin");
            System.out.println("6. Exit");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    allBook();
                    break;
                case 2:
                    allAvailableBook();
                    break;
                case 3:
                    System.out.println("Enter the title or author of the book you searching for");
                    String input = scanner.nextLine();
                    Book book = searchForBook(input);
                    System.out.println(book);
                    break;
                case 4:
                    String user = logIn();
                    member.logInMember(user, books);
                    break;
                case 5:
                    logInAdmin();
                    break;
                case 6:
                    FileUtility.saveObject("librarySave.ser", this);
                    running = false;
                    break;
            }
        }
    }

    private void allBook() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    private void allAvailableBook() {
        for (Book book : books) {
            if (book.getAvailable()) {
                System.out.println(book);
            }
        }
    }

    public Book searchForBook(String input) {
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(input.toLowerCase()) || book.getAuthor().toLowerCase().contains(input.toLowerCase())) {      // Kan jag här även lägga till isAvailable?
                // && book.getAvailable()  Denna funkar inte så länge man har toLowerCase?
                return book;
                // Kan man här även visa om den finns tillänglig att låna och lånad den direkt?
            }
        }
        return null;
    }

    public String logIn() {                                         // Blir detta rätt med att skicka tillbaka en String
        System.out.println("Please enter your user name: ");
        String user = scanner.nextLine();
        Member inListMember = findUser(user);
        member = inListMember;
        if (inListMember == null) {
            newUser(user);
        }
        return null;
    }

    public Member findUser(String user) {
        for (Member member : members) {
            if (member.getName().equals(user))
                return member;
        }
        return null;
    }

    private void newUser(String user) {
        member = new Member(user);
        members.add(member);
    }

    public void logInAdmin() {
        System.out.println("Please enter your admin name: ");
        String user = scanner.nextLine();
        if (user.equals("Admin")) {
            admin = new Admin();
            admin.logInAsAdmin(members);
        } else {
            System.out.println("You enter wrong credentials please try again. ");
            startMenu();
        }
    }

    private void loadProgram() {
        Path file = new File("librarySave").toPath();
        if (Files.exists(file)) {
            Library savedFile = (Library) FileUtility.loadObject("librarySave.ser");
            books = savedFile.books;
            members = savedFile.members;
        } else {
            booksFromStart();
        }
    }

    private void booksFromStart() {
        books.add(new Book("Harry Potter and the Chamber of Secrets", "J.K Rowling", "Ever since Harry Potter had come home for the summer, the Dursleys had been so mean and hideous that all Harry wanted was to get back to the Hogwarts School of Witchcraft and Wizardry."));
        books.add(new Book("Svenska hackare", "Daniel Goldberg och Linus Larsson", "A story from the web's shadow page. It describes the growth of Swedish hacker culture based on a number of noteworthy cases of data breaches"));
        books.add(new Book("Elon Musk - Tesla, SpaceX", "Ashlee Vance", "He has been compared to both Thomas Edison and Steve Jobs. But Elon Musk's goal is unparalleled: he wants people to be able to live on the planet Mars."));
        books.add(new Book("Jag är Zlatan Ibrahimovic : min historia", "David Lagercrantz", "In Zlatan´1s own autobiography, we get the riveting game behind the club changes. We get a glimpse into the absolute top football we never got before. For the first time, we get the whole truth about Zlatan Ibrahimović."));
        books.add(new Book("The Witcher - Last wish", "Andrzej Sapkowski", "Geralt is a Witcher, a man whose magic powers, enhanced by long training and a mysterious elixir, have made him a brilliant fighter and a merciless assassin. Yet he is no ordinary murderer: his targets are the multifarious monsters and vile fiends that ravage the land and attack the innocent."));
        members.add(new Member("Mantas"));
        members.add(new Member("Johan"));
        members.add(new Member("Theodor"));
    }
}
