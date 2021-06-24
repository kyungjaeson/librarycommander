package com.librarycommander.app;

public class Audio extends Media{
    // generated AudioType field
    private AudioType audio;

    public Audio(){
        setItemType(ItemType.AUDIO);
        setCheckedStatus(false);
    }

    public Audio(String title, String author, AudioType audioType){
        setTitle(title);
        setAuthor(author);
        setAudioType(audioType);
    }
    public Audio(String title, String author, AudioType audioType, DistributionType distributionType){
        this(title,author,audioType);
        setDistributionType(distributionType);
    }

    public Audio(AudioType audioType)
    {
        setAudioType(audioType);
    }

    //getters and setters
    public AudioType getAudioType() {
        return audio;
    }

    public void setAudioType(AudioType audio) {
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

    @Override
    public String toString() {
        return super.toString() +
                ", audio=" + audio ;
    }
}
