package com.example.asmjava5.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

    @RequestMapping("/admin")
    public String doGetAdminController() {
        return "/views/Admin/admin";
    }
    @RequestMapping("/admin/doanhthu")
    public String doGetAdminDoanhThuController() {
        return "/views/Admin/adminDoanhThu";
    }
    @RequestMapping("/admin/nhanvien")
    public String doGetAdminNhanVienController() {
        return "/views/Admin/adminNhanVien";
    }
    @RequestMapping("/admin/sanpham")
    public String doGetAdminSanPhamController() {
        return "/views/Admin/adminSanPham";
    }
}
