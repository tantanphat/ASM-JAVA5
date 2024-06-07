package com.example.asmjava5.API;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@MultipartConfig
@RequestMapping("/api/upload")
public class UpLoadAPI {
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/assets/sanpham";
    @Autowired
    HttpServletRequest req;
    @Autowired
    HttpServletResponse resp;

    @PostMapping(value = "", consumes = { "multipart/form-data" })
    @ResponseBody
    public void uploadFile(@RequestParam("file") MultipartFile file)throws IOException {
        StringBuilder fileNames = new StringBuilder();
        String fileName = file.getOriginalFilename();
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, fileName);
        fileNames.append(file.getOriginalFilename());
        Files.write(fileNameAndPath, file.getBytes());
       System.out.println("Đã upload thành công");
    }
}
