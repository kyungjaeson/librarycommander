package com.librarycommander.client;


import com.librarycommander.controller.LibraryCommander;
public class LibraryClient {

    public static void main(String[] args) {
        LibraryCommander commander = new LibraryCommander();
        commander.start();
    }

}
