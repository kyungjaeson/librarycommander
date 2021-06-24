package com.librarycommander.app;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class LibraryClient {
    public static void main(String[] args) {
        Library library =  Library.getInstance();
        Item item = new Book("Planet of the Apes","Franklin Schaffner","9780307792365",10);
        library.addItem(item);
        for(Item i :library.getItems().values().stream().filter(i -> i.getAuthor().contains("Franklin")).collect(Collectors.toCollection(ArrayList::new))){
            System.out.println(i);
        }

    }
}
