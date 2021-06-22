package com.librarycommander.app;

import java.time.Duration;

public abstract class Media extends Item {
    private String length;

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return super.toString()+
                ", length='" + length + '\'';
    }
}