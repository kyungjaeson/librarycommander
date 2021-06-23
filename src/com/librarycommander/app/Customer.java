package com.librarycommander.app;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Customer {

    private String name;
    private int id;
    private List<Item> itemInPossession = new LinkedList<>();
    private Library library = new Library();
    private CatalogLoader catalog = new CatalogLoader();

    public Customer(String name) {
        this.name = name;
    }

    public Customer(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public void checkOutItem(Item libraryItem) throws IOException {
        Collection<Item> libraryCatalog = catalog.loadItemsFromFile().values();
        itemInPossession.add(libraryItem);
        for (Item possess : libraryCatalog) {
            if (possess.getTitle().equalsIgnoreCase(libraryItem.getTitle())) {
                possess.setCheckedStatus(false);
                break;
            }
        }
    }

    public void checkInItem(Item libraryItem) throws IOException {
        System.out.println("Checking in: " + libraryItem.getTitle());
        Collection<Item> libraryCatalog = catalog.loadItemsFromFile().values();
        itemInPossession.remove(libraryItem);
        for (Item possess : libraryCatalog) {
            if (possess.getTitle().equalsIgnoreCase(libraryItem.getTitle())) {
                possess.setCheckedStatus(true);
                break;
            }
        }
    }

    public List<Item> searchItem(String keyWord) throws IOException {
        Collection<Item> libraryCatalog = catalog.loadItemsFromFile().values();
        List<Item> searchedItem = libraryCatalog.stream()
                .filter(item -> item.getTitle().contains(keyWord) || item.getAuthor().contains(keyWord))
                .collect(Collectors.toList());
        return searchedItem;
    }

    public void renewItem(Item libraryItem) throws IOException {
        boolean hasWaitList = false;
        //check if the item has a wait list
        Collection<Item> itemToBeRenewed = catalog.loadItemsFromFile().values();
        for (Item renewItem : itemToBeRenewed) {
            if (renewItem.getTitle().equalsIgnoreCase(libraryItem.getTitle())) {
                if (!libraryItem.getWaitList().isEmpty()) {
                    hasWaitList = true;
                }
                break;
            }
        }
        if (hasWaitList) {
            System.out.println("Sorry, " + libraryItem.getTitle() + " has a wait list. You have to check in\n. Thank you");
            checkInItem(libraryItem);
        } else {
            System.out.println(libraryItem.getTitle() + " has been renewed successfully");
        }
    }

    public void reserveItem(Item keyWord) throws IOException {
        List<Customer> waitList = new LinkedList<>();
        //search if we have the item
        List<Item> searchedItem = catalog.loadItemsFromFile().values().stream()
                .filter(item -> item.getTitle().toLowerCase().contains(keyWord.getTitle().toLowerCase()) ||
                        item.getAuthor().toLowerCase().contains(keyWord.getAuthor().toLowerCase()))
                .collect(Collectors.toList());
        if (searchedItem.isEmpty()) {
            System.out.println("Sorry, we do not have " + keyWord.getItemType() + "\n" +
                    " " + keyWord.getTitle());
        } else {
            waitList.add(this);
            searchedItem.stream()
                    .peek(item -> item.setWaitList(waitList));
        }
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public List<Item> getItemInPossession() {
        return itemInPossession;
    }
    public void setItemInPossession(List<Item> items) {
        this.itemInPossession = items;
    }


}