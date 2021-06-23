package com.librarycommander.app;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


public class Library {

    private Map<Integer,Customer> customers;
    private Map<Integer,Item> items;

    public Library() throws IOException{
        items = new CatalogLoader().loadItemsFromFile();
        customers = new CustomerLoader().readCustomersFromFile();
    }

    public void addCustomer(Customer customer){
        int highestValue = 0;
        for(Integer mapKey: customers.keySet()){
            if(highestValue > mapKey){
                highestValue = mapKey;
            }
        }
        customers.put(highestValue + 1, customer);
    }

    public Customer deactivateCustomer(Customer customer) throws IOException {
        Collection<Item> customerItemInPossession = customer.getItemInPossession();
        for(Item item : customerItemInPossession){
            customer.checkOutItem(item);
        }
        System.out.println(customer.getName() + " has been deactivated");
        return customer;
    }

    public Collection<Item> addItem(Item item){
        items.put()
        return items;
    }


    public Collection<Item> deleteItem(Item item){
        items.remove(item);
        return items;
    }

    //substring search by iteration
    public Collection<Item> searchItemByTitle(String keywords){
        Collection<Item> itemsByTitle = new ArrayList<>();
        for(Item item : items){
            if(item.getTitle().contains(keywords)){
                itemsByTitle.add(item);
            }
        }
        return itemsByTitle;
    }



    //streams implementation of substring search
    public Collection<Item> searchItemByAuthor(String author){
        Collection<Item> result = items.stream()
                .filter(item -> item.getAuthor().contains(author))
                .collect(Collectors.toCollection(ArrayList::new));
        return result;
    }

    //search customer implementations
    public Collection<Customer> searchCustomerById(Integer id){
        return customers.stream().
                filter(customer -> id.equals(customer.getId()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public Collection<Customer> searchCustomerByName(String name){
        return customers.stream()
                .filter(customer -> customer.getName().contains(name))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    public Map<Integer, Item> getItems() {
        return items;
    }

}
