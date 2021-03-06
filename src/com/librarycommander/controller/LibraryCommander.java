package com.librarycommander.controller;

import com.apps.util.Prompter;
import com.librarycommander.app.Customer;
import com.librarycommander.app.Item;
import com.librarycommander.app.ItemType;
import com.librarycommander.app.Library;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class LibraryCommander {
    private final Library library;

    public LibraryCommander() {
        library = Library.getInstance();
    }

    public void searchTitle(String input) {
        library.searchItemByTitle(input).forEach(item -> System.out.println(item.getTitle()));
    }

    public Customer getCustomer(String name) {
        List<Customer> customers = library.getCustomers().values().stream()
                .filter(item -> item.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
        return customers.get(0);
    }

    public void start() {
        Prompter prompter = new Prompter(new Scanner(System.in));
        boolean main = true;
        String name = prompter.prompt("Please enter your name:");
        Customer customer = getCustomer(name);
        System.out.println("Welcome to Library Commander, " + customer.getName() + "!");

        while (main) {
            System.out.println("I would like to...");
            System.out.println();
            System.out.println("S - Search for an item");
            System.out.println("C - Check out item");
            System.out.println("R - Return an item");
            System.out.println("W - Renew item");
            System.out.println("V - Reserve item");
            System.out.println("L - List out items checked out");
            System.out.println("Q - Quit Library Commander");
            System.out.println();

            String mainInput = prompter.prompt("What would you like to do today? ");

            switch (mainInput.toLowerCase()) {
                case "s":
                     String searchBy = prompter.prompt("Would you like to search by [T]itle or [A]uthor?", "T|t|A|a", "Invalid input, please enter only 'T' or 'A'");

                    switch (searchBy.toLowerCase()) {
                        case "t":
                            String title = prompter.prompt("Enter the title of the item: ");
                            System.out.println("Here's what we have: ");
                            System.out.println("===============================");
                            library.searchItemByTitle(title).forEach(item -> System.out.println(item.getTitle() + " by " + item.getAuthor()));
                            System.out.println();
                            break;
                        case "a":
                            String author = prompter.prompt("Enter the author of the item: \n");
                            System.out.println("Here's what we have: ");
                            System.out.println("===============================");
                            library.searchItemByAuthor(author).forEach(item -> System.out.println(item.getTitle() + " by " + item.getAuthor()));
                            System.out.println();
                            break;
                    }
                    break;
                case "c":
                    System.out.println("Checking out an item? Sure! \n");

                    String checkout = prompter.prompt("Would you like to check out a [B]ook, [A]udio), or [V]ideo)? ", "B|b|A|a|V|v", "Invalid input, please enter only 'B', 'A', or 'V'");

                    switch (checkout.toLowerCase()) {
                        case "b":
                            library.getItems().entrySet().stream()
                                    .filter(item -> item.getValue().getItemType() == ItemType.BOOK)
                                    .forEach(item -> System.out.println(item.getKey() + ": " +
                                            item.getValue().getTitle() + " by " + item.getValue().getAuthor()));
                            String choice = prompter.prompt("Select the item you would like to check out. Enter a number: ", "\\d+", "please choose a number");
                            System.out.println("Are you sure you want to check out this item? " + library.getItems().get(Integer.valueOf(choice)).getTitle() + " by " + library.getItems().get(Integer.valueOf(choice)).getAuthor());
                            String verify = prompter.prompt("Please choose [Y]es or [N]o ", "Y|y|N|n", "Invalid input, please enter only 'Y', or 'N'");
                            switch (verify) {
                                case "y":
                                    customer.checkOutItem(library.getItems().get(Integer.valueOf(choice)));
                                    break;
                                case "n":
                                    break;
                            }

                            System.out.println();
                            break;
                        case "a":
                            library.getItems().entrySet().stream()
                                    .filter(item -> item.getValue().getItemType() == ItemType.AUDIO)
                                    .forEach(item -> System.out.println(item.getKey() + ": " +
                                            item.getValue().getTitle() + " by " + item.getValue().getAuthor()));
                            String choice2 = prompter.prompt("Select the item you would like to check out. Enter a number: ", "\\d+", "please choose a number");
                            System.out.println("Are you sure you want to check out this item? " + library.getItems().get(Integer.valueOf(choice2)).getTitle() + " by " + library.getItems().get(Integer.valueOf(choice2)).getAuthor());
                            String verify2 = prompter.prompt("Please choose [Y]es or [N]o ", "Y|y|N|n", "Invalid input, please enter only 'Y', or 'N'");
                            switch (verify2) {
                                case "y":
                                    customer.checkOutItem(library.getItems().get(Integer.valueOf(choice2)));
                                    break;
                                case "n":
                                    break;
                            }
                            break;
                        case "v":
                            library.getItems().entrySet().stream()
                                    .filter(item -> item.getValue().getItemType() == ItemType.VIDEO)
                                    .forEach(item -> System.out.println(item.getKey() + ": " +
                                            item.getValue().getTitle() + " by " + item.getValue().getAuthor()));
                            String choice3 = prompter.prompt("Select the item you would like to check out. Enter a number: ", "\\d+", "please choose a number");
                            System.out.println("Are you sure you want to check out this item? " + library.getItems().get(Integer.valueOf(choice3)).getTitle() + " by " + library.getItems().get(Integer.valueOf(choice3)).getAuthor());
                            String verify3 = prompter.prompt("Please choose [Y]es or [N]o ", "Y|y|N|n", "Invalid input, please enter only 'Y', or 'N'");
                            switch (verify3) {
                                case "y":
                                    customer.checkOutItem(library.getItems().get(Integer.valueOf(choice3)));
                                    break;
                                case "n":
                                    break;

                            }
                    }

                    break;
                case "r":
                    // this logic will remove the item in the customer's list and add it back to the library's catalog
                    System.out.println("Returning an item? No problem!");
                    System.out.println();
                    System.out.println("Here are the items that you have on possession:");
                    customer.getItemInPossession().forEach(item -> System.out.println(item.getTitle()));

                    // Display the customer's list of items and they'll choose by array index to return book
                    Map<Integer, Item> itemsToReturn = generateMap(customer.getItemInPossession());
                    if (itemsToReturn.size() < 1) {
                        System.out.println("Sorry, No item to turn in");
                    } else {
                        itemsToReturn.entrySet()
                                .stream().forEach(item -> System.out.println(
                                item.getKey() + ": " + item.getValue().getTitle() + " by " + item.getValue().getAuthor()
                        ));
                        String returnBookIndex = prompter.prompt("Which one would you like to return?\nEnter the associated number :");
                        customer.checkInItem(itemsToReturn.get(Integer.parseInt(returnBookIndex)));
                    }
                    break;
                case "w":
                    System.out.println("You want to renew your item? Sure thing. Here's what you have:");
                    Map<Integer, Item> extendedItem = generateMap(customer.getItemInPossession());
                    System.out.println();
                    // Display the customer's list of items and they'll choose by array index to extend reservation
                    extendedItem.entrySet()
                            .stream().forEach(item -> System.out.println(
                            item.getKey() + ": " + item.getValue().getTitle() + " by " + item.getValue().getAuthor()
                    ));
                    String extend = prompter.prompt("Which one would you like to extend?\nEnter the associated number :");
                    // this logic will see if the item chosen has a waiting list and if it does, the user can't extend it
                    boolean isRenewable = customer.renewItem(extendedItem.get(Integer.parseInt(extend)));
                    if (isRenewable) {
                        System.out.println(extendedItem.get(Integer.parseInt(extend)).getTitle() + " has been renewed successfully");
                    } else {
                        System.out.println("Sorry, " + extendedItem.get(Integer.parseInt(extend)).getTitle()
                                + " has a wait list. You have to check in\n. Thank you");
                    }
                    break;
                case "v":
                    System.out.println("You want to renew an item? Sure thing. Here's what we have:");
                    System.out.println("=============================================================");
                    library.getItems().entrySet().stream()
                            .forEach(item-> System.out.println(item.getKey()+": "+
                                    item.getValue().getTitle()+" by "+item.getValue().getAuthor()));
                    String reserve = prompter.prompt("Which one would you like to extend?\nEnter the associated number :");
                    boolean reserveStatus = customer.reserveItem(library.getItems().get(Integer.parseInt(reserve)));
                    if(reserveStatus){
                        System.out.println("You have successfully reserve the item.\nWe will notify you when it becomes available");
                    }
                    break;

                case "l":
                    System.out.println("Here's what you have checked out:");
                    System.out.println("=========================================");
                    Map<Integer, Item> checkedOutItem = generateMap(customer.getItemInPossession());
                     // Display the customer's list of items and they'll choose by array index to extend reservation
                    checkedOutItem.entrySet()
                            .stream().forEach(item -> System.out.println(
                            item.getKey() + ": " + item.getValue().getTitle() + " by " + item.getValue().getAuthor()
                    ));
                    System.out.println();
                    break;
                case "q":
                    System.out.println("Thank you for choosing Library Commander");
                    //save before quitting
                    customer.saveCustomerCollection(library.getCustomers(),library.getItems());
                    System.exit(0);
                default:
                    System.out.println("Invalid input: " + mainInput + ". Please try again.");
                    System.out.println();
                    break;

            }
        }

    }

    private Map<Integer, Item> generateMap(List<Item> itemInPossession) {
        int counter = 0;
        Map<Integer, Item> possessedItem = new TreeMap<>();
        for (Item item : itemInPossession) {
            possessedItem.put(++counter, item);
        }
        return possessedItem;
    }
}
