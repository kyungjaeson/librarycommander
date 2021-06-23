package com.librarycommander.app;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CustomerLoader {
    public Map<Integer, Customer> readCustomersFromFile() throws IOException {
        Map<Integer, Customer> catalog = new TreeMap<>();
        //FileIO read
        List<String> collections = Files.readAllLines(FileSystems.getDefault().getPath("customerDirectory"));
        int counter = 0;
        //mapping values to collections
        if (!collections.isEmpty()) {
            for (String item : collections) {
                counter++;
                //fields
                String[] line = item.split(",");
                String[] objects = item.split(";");
                List<Item> itemList = new ArrayList<>();
                //customer checking out from file
                Customer libraryCustomer = new Customer(line[0], Integer.parseInt(line[1]));
                for(int i = 2; i < item.length(); i++){
                    if(!libraryCustomer.searchItem(line[i]).isEmpty()){
                        for(Item individualItem : libraryCustomer.searchItem(line[i])) {
                            libraryCustomer.checkOutItem(individualItem);
                        }
                    }
                    else{
                        System.out.println(libraryCustomer + " was not able to check out an item named" + line[i]);
                    }
                }
                //writing values to map
                catalog.put(counter, libraryCustomer);
            }
        }

        System.out.println(catalog);
        return catalog;
    }
    public void writeCustomersToFile(Map<Integer, Customer> customers) throws IOException{
        StringBuffer line;

    }
}


