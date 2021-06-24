package com.librarycommander.app;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CustomerLoaderTest {
    private  CustomerLoader loader;

    @Before
    public void setUp(){
        loader=new CustomerLoader();
    }

    @Test
    public void testLoadCustomersFromFile_shouldNotBeNull(){
       assertNotNull(loader.loadCustomersFromFile());
        assertTrue(loader.loadCustomersFromFile().size() >0);
    }

    @Test
    public void testLoadCustomerFromFile_shouldReturnTrue_whenLoadedSuccessfully(){
        assertTrue(loader.loadCustomersFromFile().size() >0);
    }

    @Test
    public void testWriteCustomersToFile(){
        loader.writeCustomersToFile(loader.loadCustomersFromFile());
    }


}
