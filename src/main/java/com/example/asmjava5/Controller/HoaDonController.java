package com.example.asmjava5.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/hoa-don")
public class HoaDonController {

    @GetMapping("")
    public String hienThiTrangQLKH() {
        return "views/Admin/Bill";
    }
}