package com.librarycommander.app;

import java.util.LinkedList;
import java.util.List;

public class Customer {

    private String name;
    private int id;
    private List<Item> itemInPossession = new LinkedList<>();
    private Library library =new Library();

    public Customer(String name){
        this.name = name;
    }
    public Customer(String name, int id){
        this.name = name;
        this.id = id;
    }
    public void checkOutItem(){

    }
    public  void checkInItem(){

    }

    public List<Item> search(String keyWord){
        return null;
    }
    public  void renewItem(){

    }
    public void reserveItem(){

    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public List<Item> getItemInPossession() {
        return itemInPossession;
    }
}