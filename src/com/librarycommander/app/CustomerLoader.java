package com.librarycommander.app;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class CustomerLoader {
    //Allows read of existing entries in project via textfile
    public Map<Integer, Customer> readCustomersFromFile() throws IOException {
        Map<Integer, Customer> catalog = new TreeMap<>();
        List<String> collections = Files.readAllLines(getCustomerCatalog());
        int counter = 0;
        //mapping values to collections
        if (!collections.isEmpty()) {
            for (String item : collections) {
                counter++;
                //fields
                String[] line = item.split(",");
                List<Item> itemList = new ArrayList<>();
                //customer checking out from file
                Customer libraryCustomer = new Customer(line[0], Integer.parseInt(line[1]));
                for(int i = 2; i < item.length(); i++){
                    if(!libraryCustomer.searchItemByAuthor(line[i]).isEmpty()){
                        for(Item individualItem : libraryCustomer.searchItemByAuthor(line[i])) {
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

    //Allows write of entries in project via textfile
    public void writeCustomersToFile(Map<Integer, Customer> customers) throws IOException{
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(getCustomerCatalog().toString()))){
            for(Map.Entry<Integer, Customer> map  : customers.entrySet()){
                StringBuilder line = new StringBuilder();
                Customer c = map.getValue();
                line.append(c.getName()).append(",")
                        .append(c.getId()).append(",");
                for(Item item : c.getItemInPossession()){
                    line.append(item.getTitle()).append(",");
                }
                writer.write(line + "\n");
            }
        }

    }

    //refactored methods
    private Path getCustomerCatalog() {
        return FileSystems.getDefault().getPath("customerDirectory");
    }

}


