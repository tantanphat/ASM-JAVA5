package com.example.asmjava5.Controller;

import com.example.asmjava5.Service.ServiceImpl.NhanVienServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/khach-hang")
public class KhachHangController {

    @Autowired
    private NhanVienServiceImpl nvServiceImpl;

    @GetMapping("")
    public String hienThiTrangQLNV() {
        return "views/Admin/customerManager";
    }
}
