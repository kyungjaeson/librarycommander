package com.librarycommander.app;

import java.util.List;

public abstract class Item {
  private String title;
  private String author;
  private boolean checkedStatus;
  DistributionType distributionType;
  List<Customer> waitList;

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
}