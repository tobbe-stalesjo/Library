package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Library {

    transient Scanner scanner = new Scanner(System.in);
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Member> members = new ArrayList<>();
    Admin admin;
    Member member;

    public Library() {

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

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        //allBook();
                        break;
                    case 2:
                        //allAvailableBook();
                        break;
                    case 3:
                        System.out.println("Enter the title of the book you searching for");
                        String title = scanner.nextLine();
                        searchForBook(title);
                        break;
                    case 4:
                        String user = logIn();
                        member.logInMember(user);
                        break;
                    case 5:
                        logInAdmin();
                        break;
                    case 6:
                        running = false;
                        break;
                }

            } catch (Exception e) {
                System.out.println("Please enter a number");
            }
        }
    }

    public Book searchForBook(String title) {
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {      // Kan jag här även lägga till isAvailable?
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

    public void logInAdmin () {
        System.out.println("Please enter your admin name: ");
        String user = scanner.nextLine();
        if (user.equals("Admin")) {
            admin.logInAdmin();                                                     // Kan man ha den packaged private?
        } else {
            System.out.println("You enter wrong credentials please try again. ");
            startMenu();
        }
    }

     public void listOfMembers() {              // Ska dessa vara public istället
        for (Member member: members){
            System.out.println(member);
        }
    }
}
