package com.example.asmjava5.Controller;

import com.example.asmjava5.Service.ServiceImpl.NhanVienServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/khach-hang")
public class KhachHangController {

    @Autowired
    private NhanVienServiceImpl nvServiceImpl;

    @GetMapping("")
    public String hienThiTrangQLKH(@RequestParam(name = "maKH",required = false) String maKH) {
        return "views/Admin/QuanLyKhachHang";
    }

    @GetMapping("/{makh}")
    public String hienThiTrangQLKHL(@PathVariable("makh") String makh) {
        return "views/Admin/QuanLyKhachHang";
    }
}
