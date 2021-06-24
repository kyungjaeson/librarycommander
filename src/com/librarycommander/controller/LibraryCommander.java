package com.librarycommander.controller;

import com.apps.util.Prompter;
import com.librarycommander.app.Item;
import com.librarycommander.app.ItemType;
import com.librarycommander.app.Library;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class LibraryCommander {
    private Library library;

    public LibraryCommander() {
        library = Library.getInstance();
    }

    public void searchTitle(String input) {
        library.searchItemByTitle(input).forEach(item -> System.out.println(item.getTitle()));
    }

    public void start() {
        Prompter prompter = new Prompter(new Scanner(System.in));
        boolean main = true;

        System.out.println("Welcome to Library Commander, sir!");

        while (main) {
            System.out.println("I would like to...");
            System.out.println();
            System.out.println("S - Search for an item");
            System.out.println("C - Check out item");
            System.out.println("R - Return item");
            System.out.println("E - Extend reservation");
            System.out.println("L - List out items checked out");
            System.out.println("Q - Quit Library Commander");
            System.out.println();

            String mainInput = prompter.prompt("What would you like to do today? ");

            switch (mainInput.toLowerCase()) {
                case "s":
                    System.out.println();

                    String searchBy = prompter.prompt("Would you like to search by [T]itle or [A]uthor?", "T|t|A|a", "Invalid input, please enter only 'T' or 'A'");

                    switch (searchBy.toLowerCase()) {
                        case "t":
                            String title = prompter.prompt("Enter the title of the item: ");
                            System.out.println("Here's what we have: \n");
                            library.searchItemByTitle(title).forEach(item -> System.out.println(item.getTitle() + " by " + item.getAuthor()));
                            System.out.println();
                        case "a":
                            String author = prompter.prompt("Enter the author of the item: \n");
                            System.out.println("Here's what we have: \n");
                            library.searchItemByAuthor(author).forEach(item -> System.out.println(item.getTitle() + " by " + item.getAuthor()));
                            System.out.println();
                    }
                    break;
                case "c":
                    System.out.println("Checking out an item? Sure! \n");

                    String checkout = prompter.prompt("Would you like to check out a [B]ook, [A]udio), or [V]ideo)? ", "B|b|A|a|V|v", "Invalid input, please enter only 'B', 'A', or 'V'");

                    switch (checkout.toLowerCase()) {
                        case "b":
                            library.getItems().entrySet().stream()
                                    .filter(item->item.getValue().getItemType()== ItemType.BOOK)
                                    .forEach(item-> System.out.println(item.getKey()+": "+
                                            item.getValue().getTitle()+" by "+item.getValue().getAuthor()));
                           /* for (Map.Entry<Integer, Item> collection : library.getItems().entrySet()) {
                                System.out.println(collection.getKey() + ": " + collection.getValue().getTitle() + " by " + collection.getValue().getAuthor());
                            }*/

                            String choice = prompter.prompt("Select the item you would like to check out. Enter a number: ", "\\d+", "please choose a number");
                            System.out.println("Are you sure you want to check out this item? " + library.getItems().get(Integer.valueOf(choice)).getTitle() + " by " + library.getItems().get(Integer.valueOf(choice)).getAuthor());
                            String verify = prompter.prompt("Please choose [Y]es or [N]o ", "Y|y|N|n", "Invalid input, please enter only 'Y', or 'N'");

                            // checkout item goes here
                            System.out.println();
                            break;
                        case "a":
                            String author = prompter.prompt("Enter the author of the item: \n");
                            System.out.println("Here's what we have: \n");
                            library.searchItemByAuthor(author).forEach(item -> System.out.println(item.getTitle() + " by " + item.getAuthor()));
                            System.out.println();
                        case "t":
                            System.out.println();

                    }
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
