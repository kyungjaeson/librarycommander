package com.librarycommander.app;

public class Book extends Item {

    private String isbn;
    private int pages;

    public Book(){
        setItemType(ItemType.BOOK);
        setCheckedStatus(false);
    }

    public Book(String title, String author){
        this();
        setTitle(title);
        setAuthor(author);
    }
    public Book(String title, String author, String isbn, int pages){
        this();
        setTitle(title);
        setAuthor(author);
        setIsbn(isbn);
        setPages(pages);
    }
    public Book(String title,String author, String isbn, int pages, DistributionType distributionType){
        this();
        setDistributionType(distributionType);
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }


    @Override
    public boolean addToWaitList(Customer customer) {
        return false;
    }

    @Override
    public boolean removeFromWaitList(Customer customer) {
        return false;
    }

    @Override
    public String toString() {
        return super.toString()+
                ", isbn='" + isbn + '\'' +
                ", pages=" + pages;
    }
}
