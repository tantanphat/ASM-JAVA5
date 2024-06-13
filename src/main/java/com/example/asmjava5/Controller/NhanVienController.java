package com.example.asmjava5.Controller;

import com.example.asmjava5.Entity.KhachHang;
import com.example.asmjava5.Entity.NhanVien;
import com.example.asmjava5.Service.NhanVienService;
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
    private NhanVienService nvService;

    @GetMapping("")
    public String hienThiTrangQLNV(@RequestParam(value = "maNV",required = false) String maNV,Model model) {

        return "views/Admin/employeeManager";
    }

    @GetMapping("/add")
    public String hienThiTrangQLNVADD() {
        return "views/Admin/employeeManager";
    }

//    @GetMapping("/{manv}")
//    public String hienThiTrangQLNVMaNV(@PathVariable String manv) {
//
//        return "views/Admin/employeeManager";
//    }

    @GetMapping("/update/{manv}")
    public String hienThiTrangQLNVlUD(@PathVariable("manv") String manv) {
        return "views/Admin/employeeManager";
    }

    @GetMapping("/delete/{manv}")
    public String hienThiTrangQLNVlDE(@PathVariable("manv") String manv) {
        return "views/Admin/employeeManager";
    }
}
