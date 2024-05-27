package com.example.asmjava5.Controller;

import com.example.asmjava5.Entity.KhachHang;
import com.example.asmjava5.Entity.NhanVien;
import com.example.asmjava5.Service.ServiceImpl.NhanVienServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/nhan-vien")
public class NhanVienController {
    @Autowired
    private NhanVienServiceImpl nvServiceImpl;

    @GetMapping("")
    public String hienThiTrangQLNV() {
        return "views/Admin/employeeManager";
    }

    @GetMapping("/{manv}")
    public String hienThiTrangQLNVl(@PathVariable("manv") String manv) {
        return "views/Admin/employeeManager";
    }

}
