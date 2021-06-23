package com.librarycommander.client;

import com.librarycommander.app.*;

import java.io.FileNotFoundException;
import java.io.IOException;

class tester {
    public static void main(String[] args) {

        new CatalogLoader().loadItemsFromFile();

    }

}