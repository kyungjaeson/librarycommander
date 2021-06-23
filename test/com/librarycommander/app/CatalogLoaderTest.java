package com.librarycommander.app;

import junit.framework.TestCase;
import org.junit.Test;
import static org.junit.Assert.*;

public class CatalogLoaderTest extends TestCase {

    @Test
    public void testLoadItemsFromFile() {
        CatalogLoader loader=new CatalogLoader();
        assertNotNull(loader.loadItemsFromFile());
    }

    @Test
    public void testWriteItemsToFile_shouldReturnTrue_whenItemsSuccessfullyWriteToFile() {
        assertTrue(new CatalogLoader().writeItemsToFile(new CatalogLoader().loadItemsFromFile()));
    }
}