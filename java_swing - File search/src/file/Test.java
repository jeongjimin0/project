package file;



import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;



public class Test {
	
    public static void main(String[] args) {
    	
    	while (true) {
    	System.out.println("=== 검색할 단어를 입력하세요. ===");
		Scanner scan = new Scanner(System.in);
		String name = scan.nextLine();
    	System.out.println(FileSearchAll("C:\\", name));
    	}

    }

    
    public static int FileSearchAll(String rootPath, String name)

    {

    File directory = new File(rootPath);
    File[] fList = directory.listFiles();
    
	int num = 0;
	String result;
	String result2;

	
	if (fList == null) {
		
	} else {
	
    for (int i = 0; i < fList.length; i++) {
		File file = fList[i];
		
        if (file.isFile()) {
        	
        	result = file.getAbsolutePath();
            if (result.contains(name)) {
            	System.out.println("==== 해당하는 파일 : " + result);

            } 
       
        }

        else {
        		 if (file.isDirectory()) {
        			 result2 = file.getAbsolutePath();
        			 if (result2.contains(name)) {
	                     System.out.println("해당하는 디렉토리 : " + result2);

            		 }
        			 FileSearchAll(file.getPath(), name);
            	}
                     
            }
        }
        
	}
		
		return num;

    }
        
        

}

