package ru.isys.task16.entities;

public class Song {
    private String name;
    private long size;

    public Song(String name, long size){
        this.name = name;
        this.size = size;
    }

    public Song(Song song){
        this.name = song.getName();
        this.size = song.getSize();
    }

    public String getName(){
        return this.name;
    }

    public long getSize(){
        return this.size;
    }

}
