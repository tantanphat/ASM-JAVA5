package com.example.asmjava5.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

    @RequestMapping("/admin")
    public String doGetAdminController() {
        return "/views/Admin/MainAdmin";
    }
    @RequestMapping("/admin/report/doanh-thu")
    public String doGetAdminDoanhThuController() {
        return "/views/Admin/adminDoanhThu";
    }

    @RequestMapping("/admin/report/nhan-vien")
    public String doGetAdminNhanVienController() {
        return "/views/Admin/adminNhanVien";
    }

    @RequestMapping("/admin/report/san-pham")
    public String doGetAdminSanPhamController() {
        return "/views/Admin/adminSanPham";
    }
}
