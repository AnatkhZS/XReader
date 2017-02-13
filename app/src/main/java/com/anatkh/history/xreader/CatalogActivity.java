package com.anatkh.history.xreader;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.catalog);
        final ListView catalogList=(ListView)findViewById(R.id.catalogList);
        Button cancel=(Button)findViewById(R.id.cancel);
        Button back=(Button)findViewById(R.id.back);
        cancel.setOnClickListener(this);
        TextView current_path=(TextView)findViewById(R.id.current_path);

        Intent intent=getIntent();
        String path=intent.getStringExtra("current_path");
        currentPath=path;
        current_path.setText(currentPath);

        getDir();

        ArrayAdapter<CatalogFile> adapter=new ArrayAdapter<CatalogFile>(CatalogActivity.this,android.R.layout.simple_list_item_1,catalog);
        catalogList.setAdapter(adapter);
        catalogList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CatalogFile file=catalog.get(position);
                if(file.getType()=="catalog"){
                    finish();
                    Intent intent=new Intent(CatalogActivity.this,CatalogActivity.class);
                    intent.putExtra("current_path",file.getPath());
                    startActivity(intent);
                }else if(file.getType()=="file"){
                    MyDatabaseHelper dbHelper=new MyDatabaseHelper(CatalogActivity.this,"Reader.db",null,1);
                    db=dbHelper.getWritableDatabase();
                    ContentValues values=new ContentValues();
                    values.put("name",file.getName());
                    values.put("path",file.getPath());
                    db.insert("Book",null,values);
                    finish();
                }

            }
        });


    }

    public void getDir(){
        File file=new File(currentPath);
        System.out.println(currentPath);
        if(file!=null){
            File[] fileList=file.listFiles();
            for(int i=0;i<fileList.length;i++){
                if(fileList[i].isDirectory()){
                    CatalogFile catalogFile=new CatalogFile(fileList[i].toString(),"catalog");
                    catalog.add(catalogFile);
                }else{
                    CatalogFile catalogFile=new CatalogFile(fileList[i].toString(),"file");
                    catalog.add(catalogFile);
//                    String[] buffArray=catalogFile.getPath().split(".");
//                    if(buffArray.length>=1) {
//                        if (buffArray[buffArray.length - 1] == "txt") {
//                            catalog.add(catalogFile);
//                        }
//                    }
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
                break;
        }
    }
}