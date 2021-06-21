package com.librarycommander.app;

public class Audio extends Item{
    // generated AudioType field
    private AudioType audio;

    //getters and setters
    public AudioType getAudio() {
        return audio;
    }

    public void setAudio(AudioType audio) {
        this.audio = audio;
    }

    //Item Class necessary implementation
    @Override
    public boolean addToWaitList(Customer customer) {
        return false;
    }

    @Override
    public boolean removeFromWaitList(Customer customer) {
        return false;
    }
}