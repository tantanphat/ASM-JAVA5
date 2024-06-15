package com.example.asmjava5.Controller;

import com.example.asmjava5.Entity.HoaDon;
import com.example.asmjava5.Entity.KhachHang;
import com.example.asmjava5.Model.request.LichSuMuaHang;
import com.example.asmjava5.Service.HoaDonService;
import com.example.asmjava5.Service.KhachHangService;
import com.example.asmjava5.Service.LichSuMuaHangService;
import com.example.asmjava5.Service.ServiceImpl.KhachHangServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private KhachHangService khachHangService;
    @Autowired
    private HoaDonService hoaDonService;

    @GetMapping("/myaccount")
    public String hienThiTrangAccountKhachHang() {
        return "/views/myaccount";
    }

    @GetMapping("/Doi-mat-khau")
    public String hienThiTrangchangePassword() {

        return "/views/changePassword";
    }

    @Autowired
    private LichSuMuaHangService muaHangService;


    @GetMapping("/Lich-su-mua-hang")
    public String hienThiLichSuMuaHang(Principal principal,Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        List<Object> hoaDons = muaHangService.getAllHoaDonByEmail(userName);
        model.addAttribute("history",hoaDons);
        return "/user/LichSuMuaHang";
    }

    @GetMapping("/San-pham-hoa-don")
    public String hienThiLichSuMuaHangChiTiet(Principal principal,Model model) {

        return "/user/ItemsHoaDonHistory";
    }



}
