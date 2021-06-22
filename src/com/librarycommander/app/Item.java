package com.librarycommander.app;

import java.util.List;

public abstract class Item {
  private String title;
  private String author;
  private boolean checkedStatus;
  private DistributionType distributionType;
  private ItemType itemType;
  private List<Customer> waitList;

  public Item(){}
  public Item(String title){
      setTitle(title);
  }

  public Item(String title,String author){
      this(title);
      setAuthor(author);
  }
  public Item(String title,String author,boolean status){
      this(title,author);
      setCheckedStatus(status);
  }
  public Item(String title,String author,boolean status,DistributionType type,ItemType itemType){
      this(title,author,status);
     setDistributionType(type);
     setItemType(itemType);
  }

  public  abstract boolean addToWaitList(Customer customer);
  public abstract boolean removeFromWaitList(Customer customer);

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isCheckedStatus() {
        return checkedStatus;
    }

    public void setCheckedStatus(boolean checkedStatus) {
        this.checkedStatus = checkedStatus;
    }

    public DistributionType getDistributionType() {
        return distributionType;
    }

    public void setDistributionType(DistributionType distributionType) {
        this.distributionType = distributionType;
    }

    public List<Customer> getWaitList() {
        return waitList;
    }

    public void setWaitList(List<Customer> waitList) {
        this.waitList = waitList;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()+"{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", distributionType=" + distributionType +
                ", itemType=" + itemType +
                '}';
    }
}