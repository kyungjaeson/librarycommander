package com.librarycommander.app;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class CatalogLoader {

    public Map<Integer, Item> loadItemsFromFile() {
        Map<Integer, Item> catalog = new TreeMap<>();
        List<String> lines = null;
        try {
            lines = Files.readAllLines(getCatalogue());
        } catch (IOException e) {
            e.printStackTrace();
        }
        int counter = 0;
         for (String item : lines) {
                Item libraryItem;
                counter++;
                List<String> customerList = new ArrayList<>();
                String[] line = item.split(",");
                libraryItem = createItems(ItemType.valueOf(line[7]));
                libraryItem.setTitle(line[0]);
                libraryItem.setAuthor(line[1]);
                libraryItem.setCheckedStatus(Boolean.parseBoolean(line[2]));
                libraryItem.setDistributionType(DistributionType.valueOf(line[3]));
                libraryItem.setItemType(ItemType.valueOf(line[7]));
                setCustomerList(customerList, line);
                libraryItem.setWaitList(customerList);
                setItemTypes(libraryItem, line);
                catalog.put(counter, libraryItem);
            }
//        }
        System.out.println(catalog);
        return catalog;
    }

    private void setCustomerList(List<String> customerList, String[] line) {
        if (!line[4].isBlank()) {
            String[] names = line[4].split(";");
            for (String name : names) {
                customerList.add(name);
            }
        }
    }
 // TODO: else if? instanceOf
    private void setItemTypes(Item libraryItem, String[] item) {
        switch ((libraryItem.getClass().getSimpleName())) {
            case "Book":
                ((Book) libraryItem).setIsbn(item[5]);
                ((Book) libraryItem).setPages(Integer.parseInt(item[6]));
                break;
            case "Audio":
                ((Audio) libraryItem).setLength(item[5]);
                ((Audio) libraryItem).setAudio(AudioType.valueOf(item[6]));
                break;
            case "Video":
                ((Video) libraryItem).setLength(item[5]);
                ((Video) libraryItem).setResolution(ResolutionType.valueOf(item[6]));
        }
    }

    public void writeItemsToFile(Map<Integer, Item> items) {
        StringBuffer line;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getCatalogue().toString()))) {
            for (Map.Entry<Integer, Item> catalogue : items.entrySet()) {
                line = new StringBuffer();
                Item catalog = catalogue.getValue();
                line.append(catalog.getTitle()).append(",").append(catalog.getAuthor()).append(",")
                        .append(catalog.isCheckedStatus()).append(",").append(catalog.getDistributionType())
                        .append(",");
                if (catalog.getWaitList().isEmpty()) {
                    line.append(",");
                } else {
                    String waitList = "";
                    for (String customer : catalog.getWaitList()) {
                        waitList += customer + ";";
                    }
                    waitList = waitList.replace(waitList.charAt(waitList.lastIndexOf(';')), ' ').strip();
                    line.append(waitList).append(",");
                }
                setItemTypeAttributes(catalog, line);
                line.append(",").append(catalog.getItemType());
                writer.write(line + "\n");
            }
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        System.out.println("Success");
    }

    private void setItemTypeAttributes(Item item, StringBuffer line) {
        switch (item.getItemType()) {
            case AUDIO:
                line.append(((Audio) item).getLength()).append(",")
                        .append(((Audio) item).getAudio());
                break;
            case BOOK:
                line.append(((Book) item).getIsbn()).append(",")
                        .append(((Book) item).getPages());
                break;
            case VIDEO:
                line.append(((Video) item).getLength()).append(",")
                        .append(((Video) item).getResolution());
        }
    }

    private Path getCatalogue() {
       // System.out.println(FileSystems.getDefault().getPath("catalogue.txt"));
        // return Path.of("catalogue.txt")
        // create sub directories data/catalogue.txt
        // Path.of("data/catalogue.txt");
        return Path.of("data/catalogue.txt");
    }

    private Item createItems(ItemType type) {
        return ItemFactory.createItem(type);
    }

}