package com.librarycommander.client;

import com.apps.util.Prompter;
import com.librarycommander.controller.LibraryCommander;
import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;

public class LibraryClient {

    static void search() {

    }
    public static void main(String[] args) {
        Prompter prompter = new Prompter(new Scanner(System.in));
        LibraryCommander librarian = new LibraryCommander();

        boolean main = true;

        System.out.println("Welcome to Library Commander, sir!");

        while(main) {
            System.out.println("I would like to...");
            System.out.println();
            System.out.println("S - Search for an item");
            System.out.println("C - Check out item");
            System.out.println("R - Return item");
            System.out.println("E - Extend reservation");
            System.out.println("L - List out items checked out");
            System.out.println("Q - Quit Library Commander");
            System.out.println();

            String mainInput  = prompter.prompt("What would you like to do today? ");

            String lowerInput = mainInput.toLowerCase();

            switch(lowerInput){
                case "s":
                    System.out.println("Would you like to search by Title(T) or Author(A)?");
                    System.out.println();
                    String searchBy = prompter.prompt("Type T or A: ");

                    // insert search function here to take in an an argument of T or A;
                    break;
                case "c":
                    System.out.println("Checking out an item? Sure!");
                    System.out.println();
                    String checkout = prompter.prompt("Would you like to check out a Book(B), Audio(A), or Video(V)? ");
                    // insert checkout function here that takes in the item type as an argument

                    // "For that category, here's what we have" then do a for each on the collection
                    // Show both items checked out and not checked out

                    // user will choose by item id to checkout a book
                    break;
                case "r":
                    System.out.println("Returning an item? No problem!");
                    System.out.println();
                    // Display the customer's list of items and they'll choose by array index to return book
                    String returnBookIndex = prompter.prompt("Which one would you like to return? ");
                    // this logic will remove the item in the customer's list and add it back to the library's catalog
                    break;
                case "e":
                    System.out.println("You want to extend your item reservation? Sure thing. Here's what you have:");
                    System.out.println();
                    // Display the customer's list of items and they'll choose by array index to extend reservation
                    String extend = prompter.prompt("Which one would you like to extend? ");
                    // this logic will see if the item chosen has a waiting list and if it does, the user can't extend it
//                    System.out.println("Sorry, it looks like someone is interested in the item.");
                    break;

                case "l":
                    System.out.println("Here's what you have checked out:");
                    System.out.println();
                    // list out items from customer class
                    break;
                case "q":
                    System.out.println("Thank you for choosing Library Commander");
                    System.exit(0);
                default:
                    System.out.println("Invalid input: " + mainInput + ". Please try again.");
                    System.out.println();
                    break;

            }

        }

    }
}
