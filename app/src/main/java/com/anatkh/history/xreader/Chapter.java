package com.anatkh.history.xreader;

public class Chapter {
	private int startPos;
	private String chapterName;
	
	public Chapter(int pos,String name){
		this.startPos=pos;
		this.chapterName=name;
	}
	
	public void setStartPos(int pos){
		this.startPos=pos;
	}
	
	public void setChapterName(String name){
		this.chapterName=name;
	}
	
	public int getStartPos(){
		return this.startPos;
	}
	
	public String getChapterName(){
		return this.chapterName;
	}
}
