package com.example.asmjava5.Controller;

import com.example.asmjava5.Entity.KhachHang;
import com.example.asmjava5.Service.ServiceImpl.KhachHangServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private KhachHangServiceImpl khachHangServiceImpl;

    @GetMapping("/myaccount")
    public String hienThiTrangAccountKhachHang(Model model) {
        return "/views/myaccount";
    }


}
