package com.librarycommander.app;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerTest {
    private Customer customer;
    private Item item;


    @Before
    public void setUp() throws Exception {
        customer=new Customer();
        item=Library.getInstance().getItems().get(3);
    }

    @Test
    public void checkOutItem_shouldReturnTrue_whenCustomerCheckOutSuccessfully() {
        assertEquals(true,customer.checkOutItem(item));
        assertEquals(1,customer.getItemInPossession().size());
    }

    @Test
    public void checkInItem() {
        item=Library.getInstance().getItems().get(2);
        customer.checkOutItem(item);
        assertTrue(customer.checkInItem(item));
        assertFalse(customer.getItemInPossession().size()>0);
    }

    @Test
    public void searchItemByTitle() {

        for(Item item:customer.searchItemByTitle(item.getTitle()))
        {
            assertEquals("Hibernate and Java Persistence API",
               item.getTitle() );}
    }

    @Test
    public void searchItemByAuthor() {
        item=Library.getInstance().getItems().get(1);
        assertTrue(customer.searchItemByAuthor(item.getAuthor()).size()>0);
        for(Item item:customer.searchItemByAuthor("Joshua")){
            assertEquals("Effective Java",item.getTitle());
            assertEquals("Joshua Bloch",item.getAuthor());
        }
    }

    @Test
    public void renewItem() {
       assertFalse(customer.renewItem(item));
       assertTrue(customer.renewItem(Library.getInstance().getItems().get(1)));
    }

    @Test
    public void reserveItem() {
        customer=new Customer("Amma Ababio");
        assertTrue(customer.reserveItem(item));
        String [] names={"Stephen Yeboah", "Amma Ababio"};
        assertArrayEquals(names,Library.getInstance()
        .getItems().get(3).getWaitList().toArray(new String[0]));
    }
}