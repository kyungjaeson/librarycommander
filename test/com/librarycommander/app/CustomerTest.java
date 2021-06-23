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
    public void checkOutItem() {
        assertEquals(true,customer.checkOutItem(item));
    }

    @Test
    public void checkInItem() {
    }

    @Test
    public void searchItemByTitle() {
    }

    @Test
    public void searchItemByAuthor() {
    }

    @Test
    public void renewItem() {
    }

    @Test
    public void reserveItem() {
    }
}