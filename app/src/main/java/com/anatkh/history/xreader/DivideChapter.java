package com.anatkh.history.xreader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DivideChapter {
	//private String chapterSignal="��";
	private int pos=0;
	private static String regX="��[һ�����������߰˾�ʮ�㩖]{1,}��";
	private static Pattern pattern=Pattern.compile(regX);
	private ChapterList chapterList=new ChapterList();
	public static void main(String args[]){
		String filePath="H:\\ѩ�к�����.txt";
		String line="ʮ����";
		Matcher match=pattern.matcher(line);
		boolean rs = match.find();
		System.out.println(rs);
		new DivideChapter().divideChapter(filePath);
	}
	void divideChapter(String filePath){
		File file=new File(filePath);
		BufferedReader reader=null;
		try {
			reader=new BufferedReader(new FileReader(file));
			String line=null;
			while((line=reader.readLine())!=null){
				Matcher match=pattern.matcher(line);
				boolean rs = match.find();
				if(rs){
					chapterList.addChapter(pos, line);
				}
				pos=pos+line.length();
			}
		} catch (IOException e) {
			System.out.println("no such file");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Iterator<Chapter> iter=chapterList.getList().iterator();iter.hasNext();){
			Chapter chapter=(Chapter) iter.next();
			System.out.println(chapter.getStartPos()+" "+chapter.getChapterName());
		}
	}
	
	void dividePage(){
		//ȷ��ÿһҳ����ʼλ��
		//ȷ��һ�ζ��������ַ���
		//ȷ��ÿһҳ���Ű���ٸ��ֽ�
		//ȷ���½���Ϣ
		//ÿ�¿�ʼ����һҳ
	}
}
