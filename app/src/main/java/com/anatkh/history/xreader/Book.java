package com.anatkh.history.xreader;

/**
 * Created by Administrator on 2017/1/2 0002.
 */

public class Book {
    private String name;
    private int imageId;

    public Book(String name, int imageId){
        this.name=name;
        this.imageId=imageId;
    }

    public String getName(){
        return this.name;
    }

    public int getImageId(){
        return this.imageId;
    }
}
