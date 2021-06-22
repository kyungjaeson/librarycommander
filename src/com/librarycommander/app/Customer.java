package com.librarycommander.app;

public class Customer {

    private String name;
    private int id;

    public Customer(String name){
        this.name = name;
    }
    public Customer(String name, int id){
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}