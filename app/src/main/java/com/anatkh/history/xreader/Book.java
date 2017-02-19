package com.anatkh.history.xreader;

/**
 * Created by Administrator on 2017/1/2 0002.
 */

public class Book {
    private String name;
    private String path;
    private int imageId;

    public Book(String name, String path, int imageId){
        this.name=name;
        this.path=path;
        this.imageId=imageId;
    }

    public String getName(){
        return this.name;
    }

    public String getPath(){return this.path;}

    public int getImageId(){
        return this.imageId;
    }
}
