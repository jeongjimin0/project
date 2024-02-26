import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.imageio.ImageIO;

public class multithread {
    public static void main(String[] args) {
        String directoryPath = "C:\\test\\Image";
        List<String> imageNames = findImageNames(directoryPath);

        // 멀티스레드를 사용하여 이미지 파일 처리
        ExecutorService executorService = Executors.newFixedThreadPool(10); // 10개의 스레드를 사용

        for (String imageName : imageNames) {
            executorService.submit(() -> {
                String filePath = directoryPath + File.separator + imageName;
                String year = imageName.substring(0, 4);
                String month = imageName.substring(4, 6);
                String day = imageName.substring(6, 8);
                String outputFolder = "C:\\test\\Image\\" + year + "\\" + month + "\\" + day + "\\";

                try {
                    File outputFile = new File(outputFolder, imageName);
                    Path source = Paths.get(filePath);
                    Path target = Paths.get(outputFolder, imageName);
                    Files.createDirectories(target.getParent());
                    Files.copy(source, target);
                    System.out.println("이미지가 성공적으로 저장되었습니다: " + target);
                } catch (IOException e) {
                    System.out.println("이미지를 저장하는 도중 오류가 발생했습니다: " + e.getMessage());
                }
            });
        }

        executorService.shutdown();
    }

    private static List<String> findImageNames(String directoryPath) {
        List<String> imageNames = new ArrayList<>();

        File directory = new File(directoryPath);
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    String fileName = file.getName().toLowerCase();
                    if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") ||
                            fileName.endsWith(".png") || fileName.endsWith(".gif") ||
                            fileName.endsWith(".bmp") || fileName.endsWith(".tif")) {
                        imageNames.add(file.getName());
                    }
                }
            }
        }

        return imageNames;
    }
}

