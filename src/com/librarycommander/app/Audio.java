package com.librarycommander.app;

public class Audio extends Media{
    // generated AudioType field
    private AudioType audio;

    public Audio(){
        super();
    }

    public Audio(AudioType audioType)
    {
        setAudio(audioType);
    }

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
