package file;


import java.io.File;
import java.io.IOException;
import java.util.Scanner;
 
public class Run {
 
    public static void main(String[] args) {
        String filePath = "C:\\test";
        subDirList(filePath);
    }
 
    public static void subDirList(String filePath){
 
        File file = new File(filePath); 
        File[] fileList = file.listFiles();
        System.out.println("=== 검색하실 파일명을 입력하세요 ===");
		Scanner scan = new Scanner(System.in);
		String name = scan.nextLine();
		int num = 0;
        
        for (int i = 0; i < fileList.length; i++) {
			File f = fileList[i];
			if (f.getName().contains(name)) {
				if (f.isFile()) {
					System.out.println("파일 = " + f.getName());
					num++;
				} else if (f.isDirectory()) {
					
					System.out.println("디렉토리 =" + f.getAbsolutePath());
					num++;
				}
      
				
			}
		
			
		}
		System.out.println("== 찾은 파일의 총 개수 : " + num + " ==");
    }
   
}


