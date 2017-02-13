package com.anatkh.history.xreader;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

/**
 * Created by Administrator on 2017/1/4 0004.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper{

    public static final String CREATE_BOOK="create table Book (" +
            "id integer primary key autoincrement, " +
            "name text, " +
            "cover integer, " +
            "path text)";

    public MyDatabaseHelper(Context context, String name, CursorFactory factory, int version){
        super(context,name,factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_BOOK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
