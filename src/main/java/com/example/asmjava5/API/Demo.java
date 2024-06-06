package com.example.asmjava5.API;

import com.example.asmjava5.Entity.SanPham;
import com.example.asmjava5.Service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Demo {
    @Autowired
    private DemoService demoService;

    @GetMapping("/list")
    public ResponseEntity<List<SanPham>> handleList(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "giaBan") String sortBy,
            @RequestParam(defaultValue = "") String sortOrder // Added default value "asc"
    ) {
        List<SanPham> sp = demoService.getAllSP(pageNo, pageSize, sortBy, sortOrder);
        return ResponseEntity.ok(sp);
    }
}
