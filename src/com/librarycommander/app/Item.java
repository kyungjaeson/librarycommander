package com.librarycommander.app;

import java.util.List;

public abstract class Item {
  private String title;
  private String author;
  private boolean checkedStatus;
  private DistributionType distributionType;
  private List<Customer> waitList;

  Item(){}
  Item(String title){
      setTitle(title);
  }

  Item(String title,String author){
      this(title);
      setAuthor(author);
  }
  Item(String title,String author,boolean status){
      this(title,author);
      setCheckedStatus(status);
  }
  Item(String title,String author,boolean status,DistributionType type){
      this(title,author,status);
     setDistributionType(type);
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
}