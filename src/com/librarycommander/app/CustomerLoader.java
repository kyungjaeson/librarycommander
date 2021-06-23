package com.librarycommander.app;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CustomerLoader {


    public Map<Integer, Customer> loadCustomersFromFile() throws IOException{
        Map<Integer, Customer> catalog = new TreeMap<>();
        //FileIO read
        List<String> collections = Files.readAllLines(FileSystems.getDefault().getPath("customerDirectory"));
        int counter = 0;
        //mapping values to collections
        if(!collections.isEmpty()){
            for(String item : collections){
                counter++;
                Customer libraryCustomer;

                libraryCustomer.se
            }
        }
    }
}
