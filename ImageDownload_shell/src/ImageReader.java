import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.imageio.ImageIO;

public class ImageReader {
	public static void main(String[] args) {
		String directoryPath = "C:\\test\\Image";
		String imagePath;
		
		
		
		ExecutorService executorService = Executors.newFixedThreadPool(10); // 10개의 스레드 사용
        
		ArrayList<String> imageNames = findImageNames(directoryPath);
        String outputFolder;
        
        // 가져온 이미지 파일 이름들을 출력합니다.
        for (String name : imageNames) {
            imagePath = directoryPath + "\\" + name;
            String year = name.substring(0, 4);
            String Month = name.substring(4, 6);
            String day = name.substring(6, 8);
            outputFolder = "C:\\test\\Image\\" + year + "\\" + Month + "\\" + day + "\\";
            
            // 가져온 이미지 파일 이름들을 출력함
            try {
            	BufferedImage image = ImageIO.read(new File(imagePath));
            	
            	if (image != null) {
            		System.out.println("이미지가 성공적으로 로드되었습니다.");
            		
            		// 저장할 이미지 파일 경로 설정
            		String outputImagePath = outputFolder + name;
            		
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
            	} else {
            		System.out.println("이미지를 로드할 수 없습니다.");
            	}
            } catch (IOException e) {
            	e.printStackTrace();
            }
        }
        
	}
	
	
	private static List<File> findImageFiles(File directory) {
		List<File> imageFiles = new ArrayList<File>();
		
	       if (directory.isDirectory()) {
	            File[] files = directory.listFiles();
	            if (files != null) {
	                for (File file : files) {
	                    if (file.isDirectory()) {
//	                        // 하위 디렉토리에 대해 재귀적으로 탐색
//	                    	// 재귀적 탐색이 필요없을 경우 삭제
	                		System.out.println("file.get_name : " + file.getName());
	                        imageFiles.addAll(findImageFiles(file));
	                    } else {
	                        // 파일이 이미지 파일인지 확인
//	                        String fileName = file.getName().toLowerCase();
//	                        if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") ||
//	                                fileName.endsWith(".png") || fileName.endsWith(".gif") ||
//	                                fileName.endsWith(".bmp") || fileName.endsWith(".tif")) {
	                            imageFiles.add(file);
	                            System.out.println(imageFiles);
//	                        }
	                    	}
	                	}
	            	}
	            }
//	        }

	        return imageFiles;
	    }
	
	
	private static ArrayList<String> findImageNames(String directoryPath) {
		
		// 이미지 파일 경로
		List<File> imageFiles = findImageFiles(new File(directoryPath));
		ArrayList<String> imageNames = new ArrayList<String>();
		if (imageFiles != null && !imageFiles.isEmpty()) {
			System.out.println("다음 이미지 파일들을 찾았습니다:");
			for (File file : imageFiles) {			
				imageNames.add(file.getName());
			}
			
		} else {
			System.out.println("이미지 파일을 찾을 수 없습니다.");
		}
		
		return imageNames;
	}
	
}
