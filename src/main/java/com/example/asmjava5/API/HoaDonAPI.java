package com.example.asmjava5.API;

import com.example.asmjava5.Entity.HoaDon;
import com.example.asmjava5.Service.HoaDonService;
import com.example.asmjava5.Service.ServiceImpl.HoaDonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/hoa-don")
public class HoaDonAPI {

    @Autowired
    private HoaDonService hoaDonService;

    @GetMapping("")
    public List<HoaDon> getAllHoadon() {
        return hoaDonService.getAllHoaDon();
    }
}
