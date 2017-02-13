package com.anatkh.history.xreader;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ArrayList<Book> bookList=new ArrayList<Book>(); //保持列表对象的唯一性
    private ArrayList<BookLayer> layerList=new ArrayList<BookLayer>(); //保持列表对象的唯一性
    BookShelfAdapter adapter;
    private SQLiteDatabase db;
    private int pos=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int permissionCheck1 = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int permissionCheck2 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(permissionCheck1 != PackageManager.PERMISSION_GRANTED||permissionCheck2!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},124);
        }

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initial();
        adapter=new BookShelfAdapter(MainActivity.this, R.layout.shelf, layerList,mListener);
        ListView listView=(ListView)findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        Button addBook=(Button) findViewById(R.id.add_book);
        addBook.setOnClickListener(this);
    }

    @Override
    protected void onResume(){
        super.onResume();
        initial();
        adapter.notifyDataSetChanged();
    }

    private BookShelfAdapter.MyClickListener mListener = new BookShelfAdapter.MyClickListener() {
        @Override
        public void myOnClick(int position,View view) {
            switch(view.getId()){
                case R.id.image1:
                    pos=0;
                    break;
                case R.id.image2:
                    pos=1;
                    break;
                case R.id.image3:
                    pos=2;
                    break;
            }

            BookLayer layer=layerList.get(position);
            Book book=layer.getList().get(pos);
            System.out.println(book.getName());

            pos=-1;
        }
    };

    void initial(){

        MyDatabaseHelper dbHelper=new MyDatabaseHelper(this,"Reader.db",null,1);
        db=dbHelper.getWritableDatabase();
        Cursor cursor=db.query("Book",null,null,null,null,null,null);
        int j=0;
        if(cursor.moveToFirst()){
            do{
                j++;
                String name=cursor.getString(cursor.getColumnIndex("name"));
                System.out.println(name);
                int id=cursor.getInt(cursor.getColumnIndex("cover"));
                String path=cursor.getString(cursor.getColumnIndex("path"));
                if(id==0){
                    System.out.println("Yes");
                    id=R.drawable.blue;
                }
                Book book=new Book(name,id);
                boolean flag=false; //此种方法实现错误，应该清空列表，重新添加；由数据库保证数据的唯一性
                for(int i=0;i<bookList.size();i++){
                    Log.d("Main","name a is "+bookList.get(i).getName()+" name b is "+book.getName());
                    if(bookList.get(i).getName().equals(book.getName())){
                        flag=true;
                    }
                }
                if(!flag){
                    bookList.add(book);
                }
            }while(cursor.moveToNext());
        }
        cursor.close();
        Log.d("Main","booklist length is "+bookList.size());
        Log.d("Main","data number is "+j);
        //test
        int book_num=bookList.size();
        Log.d("Main","book_num is "+book_num);
        if(book_num>0) {
            int layer_num = 0;
            if (book_num % 3 != 0) {
                layer_num = book_num / 3 + 1;
            } else {
                layer_num = book_num / 3;
            }
            Log.d("Main","layer_num is "+layer_num);
            BookLayer[] layerBuffList = new BookLayer[layer_num];
            for (int i = 0; i < layerBuffList.length; i++) {
                layerBuffList[i]=new BookLayer();
            }
            for (int i = 0; i < bookList.size(); i++) {
                layerBuffList[i / 3].add(bookList.get(i));
            }
            layerList.clear(); //清空列表
            for (int i = 0; i < layerBuffList.length; i++) {
                layerList.add(layerBuffList[i]);
            }
        }
//        Book book1=new Book("history",R.drawable.blue);
//        Book book2=new Book("math",R.drawable.yellow);
//        Book book3=new Book("english",R.drawable.blue);
//        Book book4=new Book("chinese",R.drawable.yellow);
//
//        bookList.add(book1);
//        bookList.add(book2);
//        bookList.add(book3);
//        bookList.add(book4);
//
//        for(int i=0;i<bookList.size();i++){
//
//        }

//        BookLayer layer1=new BookLayer();
//        BookLayer layer2=new BookLayer();
//
//        layer1.add(book1);
//        layer1.add(book2);
//        layer1.add(book3);
//
//        layer2.add(book4);
//        layer2.add(book1);
//
//        layerList.add(layer1);
//        layerList.add(layer2);
//        layerList.add(layer1);
//        layerList.add(layer2);
//        layerList.add(layer1);
//        layerList.add(layer2);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.add_book:
                Intent intent=new Intent(MainActivity.this,CatalogActivity.class);
                File root= Environment.getExternalStorageDirectory();
                intent.putExtra("current_path",root.getPath());
                startActivity(intent);
                break;
        }
    }
}
