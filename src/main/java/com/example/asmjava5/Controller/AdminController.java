package com.example.asmjava5.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/admin")

public class AdminController {

    @GetMapping("")
    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    public String doGetAdminController() {
//        SecurityContext context = SecurityContextHolder.getContext();
//        Authentication authentication = context.getAuthentication();
//        String auth = authentication.getAuthorities().toString();
        return "views/Admin/MainAdmin";
    }

    @GetMapping("/report/doanh-thu")
    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    public String doGetAdminDoanhThuController() {
        return "views/Admin/adminDoanhThu";
    }

    @GetMapping("/report/nhan-vien")
    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    public String doGetAdminNhanVienController() {
        return "views/Admin/adminNhanVien";
    }

    @GetMapping("/report/san-pham")
    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    public String doGetAdminSanPhamController() {
        return "views/Admin/adminSanPham";
    }

    @GetMapping("/hoa-don")
    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    public String hienThiTrangQLKH() {
        return "views/Admin/Bill";
    }

    @GetMapping("/hoa-don-chi-tiet")
    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    public String hienThiTrangQLHDCT(){ return "views/Admin/BillChiTiet";}
}
