package com.anatkh.history.xreader;

/**
 * Created by Administrator on 2017/1/8 0008.
 */

public class CatalogFile {
    private String path;
    private String type;
    private int cover_resource;

    public CatalogFile(String path,String type){
        this.path=path;
        this.type=type;
    }

    public String getPath(){
        return this.path;
    }

    public String getType(){
        return this.type;
    }

    public int getCover(){ return this.cover_resource;}

    public String getName(){
        String[] buffArray=this.path.split("/");
        return buffArray[buffArray.length-1];
    }

    public String toString(){
        return this.getName();
    }
}
