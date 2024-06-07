package com.example.asmjava5;


import com.example.asmjava5.Entity.KhachHang;
import com.example.asmjava5.Service.KhachHangService;
import jakarta.servlet.http.HttpSession;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class AsmJava5Application {
    public static void main(String[] args) {
        SpringApplication.run(AsmJava5Application.class, args);
    }


}
