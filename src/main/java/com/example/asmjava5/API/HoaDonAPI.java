package com.example.asmjava5.API;

import com.example.asmjava5.Entity.HoaDon;
import com.example.asmjava5.Entity.NhanVien;
import com.example.asmjava5.Service.HoaDonService;
import com.example.asmjava5.Service.ServiceImpl.HoaDonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{hd_MaHDBan}")
    public HoaDon getOneHoaDon(@PathVariable("hd_MaHDBan") String MaHDBan) {
        return hoaDonService.getHoaDonByID(MaHDBan);
    }

    @PostMapping("/add")
    public ResponseEntity<HoaDon> addHoaDon(@RequestBody HoaDon hoaDon) {
        HoaDon newHoaDon = hoaDonService.addHoaDon(hoaDon);
        return ResponseEntity.ok(newHoaDon);
    }

    @PostMapping("/thanh-toan")
    public ResponseEntity<?> thanhToan() {

        return ResponseEntity.ok(null);
    }
}
