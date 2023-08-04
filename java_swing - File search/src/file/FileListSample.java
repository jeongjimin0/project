package file;

import java.io.File;
import java.util.Scanner;

import java.io.File;
import java.io.FilenameFilter;

public class FileListSample {

	public static void main(String[] args) {
		String dirPath = "C:\\test";
		
		File dir = new File(dirPath);
		File[] files = dir.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				if(name.toUpperCase().startsWith("test")) {
					File f = new File(dir, name);
					return f.isFile();
				}
				
				return false;
			}
		});
		
		for(File f : files) {
			System.out.println(f.getName());
		}
	}
}