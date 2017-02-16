package com.anatkh.history.xreader;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Created by Administrator on 2017/2/14 0014.
 */

public class SerializableStack  implements Serializable{
    private ArrayList<String> stack;
    public SerializableStack(){
        this.stack=new ArrayList<String>();
    }
    public void push(String item){
        if(this.stack.size()>0){
            String result=this.stack.get(this.stack.size()-1);
            if(!result.equals(item)){
                this.stack.add(item);
            }
        }else{
            this.stack.add(item);
        }
    }
    public String pop(){
        if(this.stack.size()>0){
            String result=this.stack.get(this.stack.size()-1);
            this.stack.remove(result);
            return result;
        }else{
            return null;
        }
    }
    public String peek(){
        if(this.stack.size()>0){
            String result=this.stack.get(this.stack.size()-1);
            return result;
        }else{
            return null;
        }
    }
    public int getLength(){
        return this.stack.size();
    }
    public void print(){
        for(int i=0;i<this.stack.size();i++){
            Log.v("Stack",this.stack.get(i));
        }
    }
}
