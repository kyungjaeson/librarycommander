package com.librarycommander.client;

import com.apps.util.Prompter;
import com.librarycommander.controller.LibraryCommander;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.util.Scanner;

public class LibraryClient {

    public static void main(String[] args) {
        LibraryCommander commander = new LibraryCommander();
        commander.start();
    }

}
