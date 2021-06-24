package com.librarycommander.app;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Customer {

    private String name;
    private int id;
    private List<Item> itemInPossession = new LinkedList<>();

    public Customer(){

    }


    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }


    public boolean checkOutItem(Item libraryItem)  {
       Map<Integer,Item> catalog=Library.getInstance().getItems();
        boolean status=false;
        System.out.println("Checking out: " + libraryItem.getTitle());
        Collection<Item> libraryCatalog = catalog.values();
        itemInPossession.add(libraryItem);
        List<Item> updatedItem =libraryCatalog.stream()
                .filter(item -> item.getTitle().equalsIgnoreCase(libraryItem.getTitle()) &&
                        item.getAuthor().equalsIgnoreCase(libraryItem.getAuthor()))
                .peek(item -> item.setCheckedStatus(false)).collect(Collectors.toList());
        status =updateCatalogue(updatedItem,catalog);
        return status;
    }

    public boolean checkInItem(Item libraryItem){
        Map<Integer,Item> catalog=Library.getInstance().getItems();
        System.out.println("Checking in: " + libraryItem.getTitle());
        Collection<Item> libraryCatalog = catalog.values();
        itemInPossession.remove(libraryItem);
        List<Item> checkedIn=libraryCatalog.stream()
                .filter(item -> item.getTitle().equalsIgnoreCase(libraryItem.getTitle()) &&
                        item.getAuthor().equalsIgnoreCase(libraryItem.getAuthor()))
                .peek(item -> item.setCheckedStatus(true)).collect(Collectors.toList());
        return updateCatalogue(checkedIn,catalog);
    }

    public List<Item> searchItemByTitle(String keyWord) {
        Map<Integer,Item> catalog=Library.getInstance().getItems();
        Collection<Item> libraryCatalog = catalog.values();
        List<Item> searchedItem = libraryCatalog.stream()
                .filter(item -> item.getTitle().contains(keyWord))
                .sorted(Comparator.comparing(item -> item.getTitle()))
                .collect(Collectors.toList());
        return searchedItem;

    }

    public List<Item> searchItemByAuthor(String author) {
        Map<Integer,Item> catalog=Library.getInstance().getItems();
        List<Item> itemByAuthor = catalog.values().stream()
                .filter(item -> item.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .sorted((item1, item2) -> item1.getAuthor().compareTo(item2.getAuthor()))
                .collect(Collectors.toList());
        return itemByAuthor;
    }

    public boolean renewItem(Item libraryItem)  {
       Map<Integer,Item> catalog=Library.getInstance().getItems();
        boolean hasWaitList = false;
        boolean isRenewable=false;
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

        } else {
            isRenewable=true;
            System.out.println(libraryItem.getTitle() + " has been renewed successfully");
        }
        return isRenewable;
    }

    public boolean reserveItem(Item keyWord) {
      Map<Integer,Item> catalog=Library.getInstance().getItems();
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
                        List<String> waiting = item.getWaitList();
                        waiting.add(this.getName());
                        item.setWaitList(waiting);
                    }).collect(Collectors.toList());
            updatedItem.stream().forEach(item -> System.out.println(Arrays.toString(item.getWaitList().toArray())));
        }

       return updateCatalogue(updatedItem, catalog);

    }

    private boolean updateCatalogue(List<Item> updatedItem, Map<Integer, Item> values) {
        boolean isUpdated=false;
        for (Map.Entry<Integer, Item> item : values.entrySet()) {
            for (Item newItem : updatedItem) {
                if (newItem.equals(item.getValue())) {
                    values.put(item.getKey(), newItem);
                    isUpdated=true;
                    break;
                }
            }
        }
        return isUpdated;
    }

    public String getName() {
        return name;
    }

    public long getId() {
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
        return Library.getInstance().getItems();
    }
}