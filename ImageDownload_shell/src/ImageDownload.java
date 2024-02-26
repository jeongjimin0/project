import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import javax.imageio.ImageIO;

public class ImageDownload {
	public static void main(String[] args) {
		downloadImage();
	}
	
	
	private static List<File> findImageFiles(File directory) {
		List<File> imageFiles = new ArrayList<File>();
	       if (directory.isDirectory()) {
	            File[] files = directory.listFiles();
	            if (files != null) {  	
	                for (File file : files) {
	                    if (file.isDirectory()) {
	                    	// 디렉토리일 경우 재귀함수를 써 파일을 찾아냄
	                        imageFiles.addAll(findImageFiles(file));
	                    } else {
	                            imageFiles.add(file);
	                            System.out.println(imageFiles);
	                    	}
	                	}
	            	}
	            }

	        return imageFiles;
	    }

	private static void downloadImage() {
		
		String directoryPath = "C:\\test\\Image";
		List<File> imageFiles = findImageFiles(new File(directoryPath));
		String imagePath;
		String outputFolder;
		int k = 0;
		
		for (File files : imageFiles) {
		
		// 가져온 이미지 파일 이름들을 출력합니다.
//        for (String name : imageNames) {
			imagePath = imageFiles.get(k).getAbsolutePath();
			System.out.println(imagePath);
			
			String name = imageFiles.get(k).getName();
            String year = name.substring(0, 4);
            String Month = name.substring(4, 6);
            String day = name.substring(6, 8);
            
            outputFolder = "C:\\test\\Image\\" + year + "\\" + Month + "\\" + day + "\\";
            
            // 가져온 이미지 파일 이름들을 출력함
            try {
            	
            	// 이미지파일 읽기

            	BufferedImage image = ImageIO.read(new File(imagePath));
            	
            	if (image != null) {
            		System.out.println("이미지가 성공적으로 로드되었습니다.");
            		
            		// 저장할 이미지 파일 경로 설정
            		String outputImagePath = outputFolder + name;
            		System.out.println("저장될 이미지의 경로 : " + outputImagePath);
            		
            		// 저장할 폴더가 없는 경우 생성합니다.
            		File outputDir = new File(outputFolder);
            		
            		// 폴더가 없으면 생성
            		if (!outputDir.exists()) {
            			if (outputDir.mkdirs()) {
	            			System.out.println("폴더가 생성되었습니다." + outputFolder);
	            		} else {
	            			System.out.println("폴더를 생성하지 못했습니다.");
	            			return;
	            		}
            		}
            		
            		// 이미지 파일을 outputFile 이라는 객체에 
            		File outputFile = new File(outputImagePath);
            		ImageIO.write(image, "png", outputFile);
            		System.out.println("이미지가 성공적으로 저장되었습니다.");
            		k++;         		
            	} else {
            		System.out.println("이미지를 로드할 수 없습니다.");
            	}
            } catch (IOException e) {
            	e.printStackTrace();
            }
        	
        	}
		}
		
	

}
