package com.example.toy.controller;

import com.example.toy.mapper.ViewVo;
import com.example.toy.model.Information;
import com.example.toy.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.PushBuilder;
import javax.swing.text.View;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class ViewController {

    public final ViewVo viewVo;
    public final TestService testService;
    ResourceLoader resourceLoader;

    @Autowired
    public void FileController (ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @RequestMapping("/download/{fileName}")
    public ResponseEntity<Resource> Download(@PathVariable String fileName, HttpServletRequest request, @RequestParam(value = "ELEMENTID", required = false) String kw) {

        fileName = kw;
        String user_home = System.getProperty("user.home");

        Information code = testService.findAllPost11(fileName).get();

        String ext = code.getFILE_EXT();



        try {

            Resource resource = resourceLoader.getResource("file:/" + user_home + "/Downloads/" + fileName);
            File file = resource.getFile();


//                return ResponseEntity.ok()
//                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
//                        .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.length()))
//                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM.toString())
//                        .body(resource);

            if (ext.equals("png") || ext.equals("PNG")) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getName() + "\"")
                        .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.length()))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_PNG.toString())
                        .body(resource);
            } else if (ext.equals("jpg") || ext.equals("jpeg") || ext.equals("JPG") || ext.equals("JPEG")) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getName() + "\"")
                        .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.length()))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_JPEG.toString())
                        .body(resource);

            } else if (ext.equals("txt")) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getName() + "\"")
                        .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.length()))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML.toString())
                        .body(resource);
            } else if (ext.equals("pdf") || ext.equals("PDF")) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getName() + "\"")
                        .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.length()))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF.toString())
                        .body(resource);
            } else {
                System.out.println("else 타기");
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getName() + "." + ext)
                        .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.length()))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM.toString())
                        .body(resource);
            }


//            return ResponseEntity.ok()
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getName() + "\"")
//                    .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.length()))
//                    .header(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_JPEG.toString())
//                    .body(resource);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }



}
