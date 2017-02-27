package com.anatkh.history.xreader;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Administrator on 2017/1/8 0008.
 */

public class CatalogActivity extends Activity implements View.OnClickListener{
    private ArrayList<CatalogFile> catalog=new ArrayList<CatalogFile>();
    private String currentPath="";
    private SQLiteDatabase db;
    private SerializableStack stack;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.catalog);
        final ListView catalogList=(ListView)findViewById(R.id.catalogList);
        Button cancel=(Button)findViewById(R.id.cancel);
        Button back=(Button)findViewById(R.id.back);
        cancel.setOnClickListener(this);
        back.setOnClickListener(this);
        TextView current_path=(TextView)findViewById(R.id.current_path);

        Intent intent=getIntent();
        currentPath=intent.getStringExtra("current_path");
        current_path.setText(currentPath);
        stack= (SerializableStack) intent.getSerializableExtra("path_stack");
        stack.push(currentPath);
        getDir();

        ArrayAdapter<CatalogFile> adapter=new ArrayAdapter<CatalogFile>(CatalogActivity.this,android.R.layout.simple_list_item_1,catalog);
        catalogList.setAdapter(adapter);
        catalogList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CatalogFile file=catalog.get(position);
                if(file.getType()=="catalog"){
                    finish();
                    String path=file.getPath();
                    Intent intent=new Intent(CatalogActivity.this,CatalogActivity.class);
                    intent.putExtra("current_path",path);
                    intent.putExtra("path_stack",stack);
                    startActivity(intent);
                }else if(file.getType()=="file"){
                    MyDatabaseHelper dbHelper=new MyDatabaseHelper(CatalogActivity.this,"Reader.db",null,1);
                    db=dbHelper.getWritableDatabase();
                    Cursor cursor=db.rawQuery("select * from Book where name=?",new String[]{file.getName()});
                    if(cursor.getCount()>0){
                        //提示已存在文件
                    }else{
                        ContentValues values=new ContentValues();
                        values.put("name",file.getName().split("\\.")[0]);
                        values.put("path",file.getPath());
                        db.insert("Book",null,values);
                        cursor.close();
                        finish();
                    }
                }

            }
        });
    }

    public void getDir(){
        File file=new File(currentPath);
        System.out.println(currentPath);
        if(file!=null){ //遍历2次分别获得文件夹和文件，能否只遍历一次
            File[] fileList=file.listFiles();
            for(int i=0;i<fileList.length;i++){
                if(fileList[i].isDirectory()){
                    CatalogFile catalogFile=new CatalogFile(fileList[i].toString(),"catalog");
                    catalog.add(catalogFile);
                }
            }
            for(int i=0;i<fileList.length;i++){
                if(!fileList[i].isDirectory()){
                    CatalogFile catalogFile=new CatalogFile(fileList[i].toString(),"file");
                    String path=catalogFile.getPath();
                    if(path.length()>4){
                        String type=path.substring(path.length()-4);
                        if (type.equals(".txt")){
                            catalog.add(catalogFile);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.cancel:
                this.finish();
                break;
            case R.id.back:
                this.finish();
                stack.pop();
                if(stack.getLength()<=0){
                    break;
                }else{
                    String path=(String)stack.peek();
                    Intent intent=new Intent(CatalogActivity.this,CatalogActivity.class);
                    intent.putExtra("current_path",path);
                    intent.putExtra("path_stack",stack);
                    startActivity(intent);
                    break;
                }
        }
    }
}
