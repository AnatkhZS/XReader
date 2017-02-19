package com.anatkh.history.xreader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2017/2/19 0019.
 */

public class PageActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page);
        TextView pageText=(TextView)findViewById(R.id.main_page);
        Intent intent=getIntent();
        String path=intent.getStringExtra("Path");
        readFile(path,pageText);
    }
    private void readFile(String path,TextView pageText){
        String content="";
        String encoding="GBK";
        File file=new File(path);
        try {
            InputStreamReader read = new InputStreamReader(new FileInputStream((file)),encoding);
            BufferedReader br=new BufferedReader(read);
            String line=null;
            while((line=br.readLine())!=null){
                content=content+line+"\r\n";
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        pageText.setText(content);
    }
}
