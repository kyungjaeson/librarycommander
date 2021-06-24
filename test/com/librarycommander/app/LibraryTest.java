package com.librarycommander.app;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LibraryTest {

    private Library library;
    private Item item;
    private Customer customer;
    private int initialItemCount;


    private Customer currentCustomers;

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

    @Test
    public void addItem_shouldHaveExtraItem_shouldBeCorrectType() {
       // item = new Video("Charl")
    }
}
