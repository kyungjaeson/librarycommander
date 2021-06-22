package com.librarycommander.app;

public class ItemFactory {
    private ItemFactory(){}
    public static Item createItem(ItemType indicator) {
        Item libraryItem = null;
        switch (indicator) {
            case AUDIO:
                libraryItem = new Audio();
                break;
            case BOOK:
                libraryItem = new Book();
                break;
            case VIDEO:
                libraryItem = new Video();
        }
        return libraryItem;
    }

}