package demoforltp4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class DeleteSpace {
@SuppressWarnings("resource")
public static void main(String args[]){
	String filePath = "D:/111/11.txt";
	try {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
		String str;
		
	
		while((str=br.readLine())!=null) {
		String s =str;
		s.replace("\r","");
		s.replace("\t","");
		s.replace(" ", "");
		System.out.println(s);
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	String file = "D:/111";
	File srcFile = new File(file);
	boolean b = srcFile.exists();
	if(b&&srcFile.isDirectory()){
	    File[] fileset = srcFile.listFiles();
	     for(int i = 0; i<fileset.length;i++){
	           File f= fileset[i];  //这个就是循环出来的文件夹里的文件。然后用上面的方法读它。
	           
	           
	    }
	}
	
}
}
