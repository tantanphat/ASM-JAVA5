package com.example.asmjava5.Controller;

import com.example.asmjava5.Entity.NhanVien;
import com.example.asmjava5.Service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")

public class AdminController {
    @Autowired
    private NhanVienService nhanVienService;

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
//    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    public String hienThiTrangQLHD() {
        return "views/Admin/Bill";
    }

    @GetMapping("/hoa-don/{mahd}")
    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    public String hienThiTrangQLHDByID(@PathVariable("mahd") String mahd) {
        return "views/Admin/Bill";
    }

//    @GetMapping("/hoa-don-chi-tiet")
//    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
//    public String hienThiTrangQLHDCT() {
//        return "views/Admin/BillChiTiet";
//    }

    @GetMapping("/san-pham")
    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    public String hienThiTrangQLSP() {
        return "views/Admin/productMaganer";
    }

    @GetMapping("/san-pham/masp/{masp}")
    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    public String hienThiTrangQLSPByID(@PathVariable("masp") String masp) {
        return "views/Admin/productMaganer";
    }

//    @GetMapping("/hoa-don-chi-tiet/{mahdct}")
//    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
//    public String hienThiTrangQLHDCTByID(@PathVariable("mahdct") String mahdct) {return "views/Admin/BillChiTiet";}
//
//    @GetMapping("/hoa-don-chi-tiet/mahd/{mahd}")
//    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
//    public String hienThiTrangQLHDCTByMaHD(@PathVariable("mahd") String mahd) {
//        return "views/Admin/BillChiTiet";
//    }
}
