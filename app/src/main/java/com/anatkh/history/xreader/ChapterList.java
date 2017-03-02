package com.anatkh.history.xreader;

import java.util.ArrayList;

public class ChapterList{
	private ArrayList<Chapter> chapterList=null;
	
	public ChapterList(){
		this.chapterList=new ArrayList<Chapter>();
	}
	
	public void addChapter(int pos,String name){
		this.chapterList.add(new Chapter(pos,name));
	}
	
	public ArrayList<Chapter> getList(){
		return this.chapterList;
	}
	
}
