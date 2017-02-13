package com.anatkh.history.xreader;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/1/2 0002.
 */

public class BookLayer {
    private ArrayList<Book> list=new ArrayList<Book>();

    public boolean add(Book book){
        if (list.size()>=3) return false;
        list.add(book);
        return true;
    }

    public ArrayList<Book> getList(){
        return this.list;
    }
}
