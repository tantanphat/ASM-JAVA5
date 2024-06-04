package com.example.asmjava5.API;


import com.example.asmjava5.Entity.HoaDonChiTiet;
import com.example.asmjava5.Entity.SanPham;
import com.example.asmjava5.Service.ThongKeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/thong-ke")
public class ThongKeAPI {

    @Autowired
     private ThongKeService thongKeService;

    @GetMapping("nhan-vien-chi-tiet/{thang}")
    public List<HoaDonChiTiet> thongKeNhanVienChiTietThang(@PathVariable int thang) {
        return thongKeService.thongKeNhanVienChiTietThang(thang);
    }

    @GetMapping("nhan-vien-co-don/{thang}")
    public List<HoaDonChiTiet> thongKeNhanVienCoDonThang(@PathVariable int thang) {
        return thongKeService.thongKeNhanVienCoDonThang(thang);
    }

    @GetMapping("doanh-thu/{thang}")
    public List<HoaDonChiTiet> thongKeDoanhThu(@PathVariable int thang) {
        return thongKeService.thongKeDoanhThu(thang);
    }

    @GetMapping("san-pham-ban/{thang}")
    public List<HoaDonChiTiet> thongKeSanPhamBan(@PathVariable int thang) {
        return thongKeService.thongKeSanPhamBan(thang);
    }

    @GetMapping("san-pham-ban-duoc/{thang}")
    public List<SanPham> thongKeSanPhamBanDuoc(@PathVariable int thang) {
        return thongKeService.thongKeSanPhamBanDuoc(thang);
    }

    @GetMapping("san-pham-khong-ban/{thang}")
    public List<SanPham> thongKeSanPhamKBanDuoc(@PathVariable int thang) {
        return thongKeService.thongKeSanPhamKBanDuoc(thang);
    }
}
