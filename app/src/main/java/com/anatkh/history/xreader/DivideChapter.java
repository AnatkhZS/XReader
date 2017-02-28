package com.anatkh.history.xreader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DivideChapter {
	private static String regX="第[零〇一二三四五六七八九十]{1,}章";
	private static Pattern pattern=Pattern.compile(regX);
	public static void main(String args[]){
		String filePath="H:\\赘婿.txt";
		new DivideChapter().readFile(filePath);
	}
	void readFile(String filePath){
		File file=new File(filePath);
		BufferedReader reader=null;
		try {
			reader=new BufferedReader(new FileReader(file));
			String line=null;
			while((line=reader.readLine())!=null){
				//System.out.println(line);
				Matcher match=pattern.matcher(line);
				boolean rs = match.find();
				if(rs){
					System.out.println(line);
				}
			}
		} catch (IOException e) {
			System.out.println("no such file");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
