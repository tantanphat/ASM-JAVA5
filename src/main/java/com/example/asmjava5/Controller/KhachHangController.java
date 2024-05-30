package com.example.asmjava5.Controller;

import com.example.asmjava5.Service.ServiceImpl.NhanVienServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/khach-hang")
public class KhachHangController {

    @Autowired
    private NhanVienServiceImpl nvServiceImpl;

    @GetMapping("")
    public String hienThiTrangQLKH() {
        return "views/Admin/customerManager";
    }

    @GetMapping("/{makh}")
    public String hienThiTrangQLKHL(@PathVariable("makh") String makh) {
        return "views/Admin/customerManager";
    }
}
