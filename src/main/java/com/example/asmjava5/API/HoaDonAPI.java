package com.example.asmjava5.API;

import com.example.asmjava5.Service.ServiceImpl.HoaDonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HoaDonAPI {

    @Autowired
    private HoaDonServiceImpl hoaDonServiceImpl;

    @GetMapping("/api/hoadon")
    public ResponseEntity<?> getAllHoaDon() {
        return ResponseEntity.ok(hoaDonServiceImpl.getAllHoaDon());
    }

}
