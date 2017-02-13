package com.anatkh.history.xreader;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
        ImageView image1=(ImageView) view.findViewById(R.id.image1);
        ImageView image2=(ImageView) view.findViewById(R.id.image2);
        ImageView image3=(ImageView) view.findViewById(R.id.image3);
        TextView text1=(TextView)view.findViewById(R.id.text1);
        TextView text2=(TextView)view.findViewById(R.id.text2);
        TextView text3=(TextView)view.findViewById(R.id.text3);
        ArrayList<Book> list=layer.getList();
        for(int i=0;i<list.size();i++){
            switch (i){
                case 0:
                    image1.setImageResource(list.get(i).getImageId());
                    image1.setTag(position);
                    text1.setText(list.get(i).getName());
                    break;
                case 1:
                    image2.setImageResource(list.get(i).getImageId());
                    image2.setTag(position);
                    text2.setText(list.get(i).getName());
                    break;
                case 2:
                    image3.setImageResource(list.get(i).getImageId());
                    image3.setTag(position);
                    text3.setText(list.get(i).getName());
                    break;
            }
        }
        image1.setOnClickListener(listener);
        image2.setOnClickListener(listener);
        image3.setOnClickListener(listener);
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
