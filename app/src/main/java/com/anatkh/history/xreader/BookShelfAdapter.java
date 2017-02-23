package com.anatkh.history.xreader;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/2 0002.
 */

public class BookShelfAdapter extends ArrayAdapter<BookLayer>{

    private int resourceId;
    private MyClickListener listener;

    public BookShelfAdapter(Context context,int textViewResourceId,List<BookLayer> objects,MyClickListener listener){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
        this.listener=listener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        BookLayer layer=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,null);
        LinearLayout column1=(LinearLayout)view.findViewById(R.id.column1);
        LinearLayout column2=(LinearLayout)view.findViewById(R.id.column2);
        LinearLayout column3=(LinearLayout)view.findViewById(R.id.column3);
        TextView text1=(TextView)view.findViewById(R.id.text1);
        TextView text2=(TextView)view.findViewById(R.id.text2);
        TextView text3=(TextView)view.findViewById(R.id.text3);
        ArrayList<Book> list=layer.getList();
        for(int i=0;i<list.size();i++){
            switch (i){
                case 0:
                    column1.setBackgroundResource(R.drawable.blue);
                    column1.setTag(position);
                    text1.setText(list.get(i).getName());
                    break;
                case 1:
                    column2.setBackgroundResource(R.drawable.blue);
                    column2.setTag(position);
                    text2.setText(list.get(i).getName());
                    break;
                case 2:
                    column3.setBackgroundResource(R.drawable.blue);
                    column3.setTag(position);
                    text3.setText(list.get(i).getName());
                    break;
            }
        }
        column1.setOnClickListener(listener);
        column2.setOnClickListener(listener);
        column3.setOnClickListener(listener);
        return view;
    }

    public static abstract class MyClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            myOnClick((int)v.getTag(),v);
        }
        public abstract void myOnClick(int position, View v);
    }
}
