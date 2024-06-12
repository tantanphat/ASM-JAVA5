package com.example.asmjava5.Utils;

import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;
import jakarta.mail.Multipart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public class ImageUploadUtils {

    private Cloudinary cloudinary;

    public ImageUploadUtils() {
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dtnf47wll",
                "api_key", "217654742889353",
                "api_secret", "uUbFmOu7M0dJH2W4oRG9DhAwPVE"));
    }

    public String uploadImage(MultipartFile file) throws IOException {
        String name = file.getOriginalFilename();
        //Lấy tên gốc của ảnh
        String cloudName = name.substring(0,name.lastIndexOf("."));
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
                "public_id",cloudName,
                    "folder","sanpham"
        ));
        return (String) uploadResult.get("url");
    }
}
