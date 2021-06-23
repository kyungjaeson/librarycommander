package com.librarycommander.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Library {

    private Set<Customer> customers = new TreeSet<>();
    private Collection<Item> items = new ArrayList<>();

    Set<Customer> addCustomer(Customer customer){
        customers.add(customer);
        return customers;
    }

    //I believe original intention was to destroy customer, but most libraries do not do this and neither should we. Instead we remove any holds
    Customer deactivateCustomer(Customer customer) throws IOException {
        Collection<Item> customerItemInPossession = customer.getItemInPossession();
        for(Item item : customerItemInPossession){
            customer.checkOutItem(item);
        }
        System.out.println(customer.getName() + " has been deactivated");
        return customer;
    }

    Collection<Item> addItem(Item item){
        items.add(item);
        return items;
    }


    Collection<Item> deleteItem(Item item){
        items.remove(item);
        return items;
    }

    //substring search by iteration
    Collection<Item> searchItemByTitle(String keywords){
        Collection<Item> itemsByTitle = new ArrayList<>();
        for(Item item : items){
            if(item.getTitle().contains(keywords)){
                itemsByTitle.add(item);
            }
        }
        return itemsByTitle;
    }



    //streams implementation of substring search
    Collection<Item> searchItemByAuthor(String author){
        Collection<Item> result = items.stream()
                .filter(item -> item.getAuthor().contains(author))
                .collect(Collectors.toCollection(ArrayList::new));
        return result;
    }

    //search customer implementations
    Collection<Customer> searchCustomerById(Integer id){
        return customers.stream().
                filter(customer -> id.equals(customer.getId()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    Collection<Customer> searchCustomerByName(String name){
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

    public Collection<Item> getItems() {
        return items;
    }

    public void setItems(Collection<Item> items) {
        this.items = items;
    }
}
