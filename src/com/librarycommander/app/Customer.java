package com.librarycommander.app;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Customer {

    private String name;
    private int id;
    private List<Item> itemInPossession = new LinkedList<>();
    private Library library ;
    private Map<Integer,Item> catalog;

    public Customer() throws IOException{
        library=new Library();
        catalog=library.getItems();
    }
    public Customer(String name) throws IOException {
        this();
        this.name = name;
    }

    public Customer(String name, int id) throws IOException {
        this();
        this.name = name;
        this.id = id;
    }

    public void checkOutItem(Item libraryItem) throws IOException {
        System.out.println("Checking out: " + libraryItem.getTitle());
        Collection<Item> libraryCatalog = catalog.values();
        itemInPossession.add(libraryItem);
        List<Item> updatedItem =libraryCatalog.stream()
                .filter(item -> item.getTitle().equalsIgnoreCase(libraryItem.getTitle()) &&
                        item.getAuthor().equalsIgnoreCase(libraryItem.getAuthor()))
                .peek(item -> item.setCheckedStatus(false)).collect(Collectors.toList());
        updateCatalogue(updatedItem,catalog);
    }

    public void checkInItem(Item libraryItem) throws IOException {
        System.out.println("Checking in: " + libraryItem.getTitle());
        Collection<Item> libraryCatalog = catalog.values();
        itemInPossession.remove(libraryItem);
        List<Item> checkedIn=libraryCatalog.stream()
                .filter(item -> item.getTitle().equalsIgnoreCase(libraryItem.getTitle()) &&
                        item.getAuthor().equalsIgnoreCase(libraryItem.getAuthor()))
                .peek(item -> item.setCheckedStatus(true)).collect(Collectors.toList());
        updateCatalogue(checkedIn,catalog);
    }

    public List<Item> searchItemByTitle(String keyWord) throws IOException {
        Collection<Item> libraryCatalog = catalog.values();
        List<Item> searchedItem = libraryCatalog.stream()
                .filter(item -> item.getTitle().contains(keyWord))
                .sorted(Comparator.comparing(item -> item.getTitle()))
                .collect(Collectors.toList());
        return searchedItem;
    }

    public List<Item> searchItemByAuthor(String author) throws IOException {
        List<Item> itemByAuthor = catalog.values().stream()
                .filter(item -> item.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .sorted((item1, item2) -> item1.getAuthor().compareTo(item2.getAuthor()))
                .collect(Collectors.toList());
        return itemByAuthor;
    }

    public void renewItem(Item libraryItem) throws IOException {
        boolean hasWaitList = false;
        //check if the item has a wait list
        Collection<Item> itemToBeRenewed = catalog.values();
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
        //list of updated items
        List<Item> updatedItem = new LinkedList<>();
        //search if we have the item
        List<Item> searchedItem = catalog.values().stream()
                .filter(item -> item.getTitle().toLowerCase().contains(keyWord.getTitle().toLowerCase()) ||
                        item.getAuthor().toLowerCase().contains(keyWord.getAuthor().toLowerCase()))
                .collect(Collectors.toList());
        if (searchedItem.isEmpty()) {
            System.out.println("Sorry, we do not have " + keyWord.getItemType() + "\n" +
                    " " + keyWord.getTitle());
        } else {
            updatedItem = searchedItem.stream()
                    .peek(item -> {
                        List<Customer> waiting = item.getWaitList();
                        waiting.add(this);
                        item.setWaitList(waiting);
                    }).collect(Collectors.toList());
            updatedItem.stream().forEach(item -> System.out.println(Arrays.toString(item.getWaitList().toArray())));
        }
        System.out.println("Before updating");
        System.out.println(catalog);
        updateCatalogue(updatedItem, catalog);
        System.out.println("After updating...");
        System.out.println(catalog);
    }

    private void updateCatalogue(List<Item> updatedItem, Map<Integer, Item> values) {
        for (Map.Entry<Integer, Item> item : values.entrySet()) {
            for (Item newItem : updatedItem) {
                if (newItem.equals(item.getValue())) {
                    values.put(item.getKey(), newItem);
                    break;
                }
            }
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


    public String toString() {
        return getName();
    }

    public void setItemInPossession(List<Item> items) {
        this.itemInPossession = items;
    }

    public Map<Integer,Item> currentLibraryCollection(){
        return catalog;
    }
}