package com.librarycommander.controller;

import com.apps.util.Prompter;
import com.librarycommander.app.Library;

import java.io.IOException;
import java.util.Scanner;

public class LibraryCommander {
    private Library library;

    public LibraryCommander() throws IOException {
        System.out.println("I am in LibraryCommander");
        library = Library.getInstance();
    }

    public void searchTitle(String input) {
        library.searchItemByTitle(input).forEach(item -> System.out.println(item.getTitle()));
    }

}
