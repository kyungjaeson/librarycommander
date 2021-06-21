package com.librarycommander.app;

public class Video extends Media{
    //private fields
    private ResolutionType resolution;
    //setters/getters
    public ResolutionType getResolution() {
        return resolution;
    }

    public void setResolution(ResolutionType resolution) {
        this.resolution = resolution;
    }
    //Customer waitlist implementation for video
    @Override
    public boolean addToWaitList(Customer customer) {
        return false;
    }

    @Override
    public boolean removeFromWaitList(Customer customer) {
        return false;
    }
}