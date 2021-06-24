package com.librarycommander.app;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


public enum Library {
    INSTANCE;

    public static Library getInstance() {
        return INSTANCE;
    }

    // new customer loader
    private Map<Integer,Customer> customers;
    private Map<Integer,Item> items;

    Library() {
        CatalogLoader loader = new CatalogLoader();
        items = loader.loadItemsFromFile();
        CustomerLoader customerLoader=new CustomerLoader();
        //customers = customerLoader.loadCustomersFromFile();
    }

    public void addCustomer(Customer customer){
        int highestValue = 0;
        for(Integer mapKey: customers.keySet()){
            if(highestValue < mapKey){
                highestValue = mapKey;
            }
        }
        if(customers != null) {
            customers.put(highestValue + 1, customer);
        }
        else{
            customers.put(1,customer);
        }
    }

    public Customer deactivateCustomer(Customer customer) throws IOException {
        Collection<Item> customerItemInPossession = customer.getItemInPossession();
        for(Item item : customerItemInPossession){
            customer.checkOutItem(item);
        }
        System.out.println(customer.getName() + " has been deactivated");
        return customer;
    }

    public void addItem(Item item){
        int highestValue = 0;
        for(Integer mapKey: items.keySet()){
            if(highestValue < mapKey){
                highestValue = mapKey;
            }
        }
        items.put(highestValue + 1, item);

    }


    public void deleteItem(Item item){
        items.remove(item);
    }

    //substring search by iteration
    public Collection<Item> searchItemByTitle(String keywords){
        Collection<Item> itemsByTitle = new ArrayList<>();
        for(Item item : items.values()){
            if(item.getTitle().contains(keywords)){
                itemsByTitle.add(item);
            }
        }
        return itemsByTitle;
    }

    //streams implementation of substring search

    public Collection<Item> searchItemByAuthor(String author){
        Collection<Item> result = items.values().stream()
                .filter(item -> item.getAuthor().contains(author))
                .collect(Collectors.toList());
        return result;
    }

    //search customer implementations
    public Collection<Customer> searchCustomerById(Integer id){
        return customers.values().stream().
                filter(customer -> id.equals(customer.getId()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public Collection<Customer> searchCustomerByName(String name){
        return customers.values().stream()
                .filter(customer -> customer.getName().contains(name))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public Map<Integer, Customer> getCustomers() {
        return customers;
    }

    public Map<Integer, Item> getItems() {
        return items;
    }

}
