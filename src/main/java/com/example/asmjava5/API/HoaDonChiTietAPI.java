package com.example.asmjava5.API;

import com.example.asmjava5.Entity.HoaDonChiTiet;
import com.example.asmjava5.Service.HoaDonChiTietService;
import com.example.asmjava5.Service.HoaDonService;
import com.example.asmjava5.Service.ServiceImpl.HoaDonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/hoa-don-chi-tiet")
public class HoaDonChiTietAPI {
    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;

    @GetMapping("")
    public List<HoaDonChiTiet> getHoadonchitiet() { return hoaDonChiTietService.getAllHoaDonChiTiet();}
}
