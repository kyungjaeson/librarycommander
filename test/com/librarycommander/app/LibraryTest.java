package com.librarycommander.app;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class LibraryTest {

    private Library library;
    private Item item;
    private Customer customer;
    private int initialItemCount;
    private int initialCustomerCount;

    @Before
    public void setUp() throws Exception{
        library= Library.getInstance();
    };

    @Test
    public void addItem_shouldHaveExtraItem_1() {
        item = new Book("Pride and Prejudice", "Jane Austen");
        initialItemCount = library.getItems().size();
        library.addItem(item);
        assertEquals(initialItemCount + 1 ,library.getItems().size() );
    }
    /* waiting on customer loader
    @Test
    public void addCustomer_shouldHaveExtra(){
        customer = new Customer("Steve Astley",1111111);
        initialCustomerCount = library.getCustomers().size();
        library.addCustomer(customer);
        assertEquals(initialCustomerCount + 1, library.getCustomers().size());

    }



    @Test
    public void addCustomer_shouldBeAbleToCheckout() {
        customer = new Customer("Rick Astley", 1111112);
        library.addCustomer(customer);
        customer.checkOutItem(item);
       // item = new Video("Charl")
    }
*/

    @Test
    public void searchItemByAuthor(){
        item = new Book("Planet of the Apes","Franklin Schaffner","9780307792365",10,DistributionType.CIRCULATING);
        Collection<Item> itemCollection= new ArrayList<>();
        library.addItem(item);
        itemCollection.add(item);
        assertEquals(itemCollection, library.getItems().values().stream().filter(i -> i.getAuthor().contains("Franklin")).collect(Collectors.toCollection(ArrayList::new)));
    }


}
