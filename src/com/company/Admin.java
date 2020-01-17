package com.company;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Admin implements Serializable {

    transient Scanner scanner = new Scanner(System.in);
    Library library;


    public void logInAsAdmin(ArrayList<Member> members) {
        boolean running = true;

        while (running) {
            System.out.println("Welcome to Admin page");
            System.out.println("Please make a choice: ");
            System.out.println("1. Add new books");
            System.out.println("2. Remove a book");
            System.out.println("3. List of all members");
            System.out.println("4. Search for a member");
            System.out.println("5. Log out");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addNewBook();
                    break;
                case 2:
                    removeBook();
                    break;
                case 3:
                    listOfMembers(members);
                    break;
                case 4:
                    System.out.println("Enter the members name: ");     // Här ska admin kunna söker på användare i systemet
                    String name = scanner.nextLine();
                    Member member = findUser(name, members);
                    System.out.println(member);
                    break;
                case 5:
                    running = false;
                    break;
            }
        }
    }

    private void addNewBook() {
        System.out.println("Enter the title of the book: ");
        String title = scanner.nextLine();
        System.out.println("Enter the author of the book: ");
        String author = scanner.nextLine();

        // metod som sen lägger till boken. Ska denna ligga i Library och att man skickar med alla attribut dit?
    }

    private void removeBook() {
        // här behöver jag ha en metod som först kollar om man har skrivit en författare eller title samt att man kollar detta mot listan
        // sen ska du kunna välja vilken av böckerna det är från den listan du fått upp. Tänk på att listan börjar på 0 och ta med detta
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
}
