package com.librarycommander.app;

public class Book extends Item {

    private String isbn;
    private int pages;

    public Book(){
        super();
    }
    public Book(String isbn){
        setIsbn(isbn);
    }
    public Book(String isbn,int pages){
        this(isbn);
        setPages(pages);
    }
     public Book(String title,String author,boolean status){
        super(title,author,status);
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
                "isbn='" + isbn + '\'' +
                ", pages=" + pages;
    }
}
