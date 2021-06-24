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
    public Map<Integer, Customer> loadCustomersFromFile() {
        Map<Integer, Customer> catalog = new TreeMap<>();
        List<String> collections = null;
        try {
            collections = Files.readAllLines(getCustomerCatalog());
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                if (line[2].contains(";")) {
                    for (String title : line[2].split(";")) {
                        List<Item> possessedItem = libraryCustomer.searchItemByTitle(title.strip());
                        possessedItem.forEach(itemInHand -> itemList.add(itemInHand));
                    }

                } else {
                    List<Item> posses = libraryCustomer.searchItemByTitle(line[2].strip());
                    posses.forEach(itemInHand -> itemList.add(itemInHand));
                }
                libraryCustomer.setItemInPossession(itemList);
                catalog.put(counter, libraryCustomer);
            }
        }

        return catalog;
    }

    //Allows write of entries in project via text file
    public boolean writeCustomersToFile(Map<Integer, Customer> customers) {
        boolean isSuccessful = false;
        StringBuffer line;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getCustomerCatalog().toString()))) {
            for (Map.Entry<Integer, Customer> map : customers.entrySet()) {
                line = new StringBuffer();
                Customer c = map.getValue();
                line.append(c.getName()).append(",")
                        .append(c.getId()).append(",");
                for (Item item : c.getItemInPossession()) {
                    line.append(item.getTitle()).append(";");
                }
                line.deleteCharAt(line.lastIndexOf(";"));
                writer.write(line + "\n");
            }
            isSuccessful = true;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccessful;
    }

    //refactored methods
    private Path getCustomerCatalog() {
        return FileSystems.getDefault().getPath("data/customerDirectory.txt");
    }

}

