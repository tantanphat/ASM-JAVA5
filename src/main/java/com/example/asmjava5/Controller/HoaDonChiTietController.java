package com.example.asmjava5.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/hoa-don-chitiet")
public class HoaDonChiTietController {
    @GetMapping("")
    public String hienThiTrangQLHDCT(){ return "views/Admin/BillChiTiet";}
}
