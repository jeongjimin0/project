package inzent.project.test;

import java.io.File;
import java.text.SimpleDateFormat;

import inzent.project.swing.CreateSwing;

public class fileTest {
	
	public static void main(String[] args) {
	System.out.println("kkk");
		
	File file = new File("C:\\test");		
	File[] list = file.listFiles();
	System.out.println(list.length);
	
	for(int i = 0; i < list.length; i++) {
		long lastModifiedDate = file.lastModified(); 
		String lastModified = new SimpleDateFormat("yyyy-MM-dd a hh:mm").format(lastModifiedDate);
		System.out.print(lastModified + " \t");
		
		long size = list[i].length();

		if(list[i].isDirectory()) {
			System.out.print("<DIR>" + "\t" + "\t");
		} else {
			System.out.print("\t" + size + "\t");
		}
		
		System.out.println(list[i].getName());
		}
	}
	

}
