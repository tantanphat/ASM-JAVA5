package com.example.asmjava5.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("")
    public String doGetAdminController() {
        return "/views/Admin/MainAdmin";
    }

    @GetMapping("/report/doanh-thu")
    public String doGetAdminDoanhThuController() {
        return "/views/Admin/adminDoanhThu";
    }

    @GetMapping("/report/nhan-vien")
    public String doGetAdminNhanVienController() {
        return "/views/Admin/adminNhanVien";
    }

    @GetMapping("/report/san-pham")
    public String doGetAdminSanPhamController() {
        return "/views/Admin/adminSanPham";
    }
    }
