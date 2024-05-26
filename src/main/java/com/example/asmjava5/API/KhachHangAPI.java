package com.example.asmjava5.API;

import com.example.asmjava5.Entity.KhachHang;
import com.example.asmjava5.Model.mapper.KhachHangMapper;
import com.example.asmjava5.Model.request.KhachHangDto;
import com.example.asmjava5.Repository.KhachHangDao;
import com.example.asmjava5.Service.ServiceImpl.KhachHangServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class KhachHangAPI {
    @Autowired
    HttpSession session;
    @Autowired
    HttpServletRequest req;
    @Autowired
    HttpServletResponse resp;
    @Autowired
    private KhachHangServiceImpl khachHangServiceImpl;
    @Autowired
    private KhachHangDao khachHangDao;

    //Lấy thông tin khách hàng qua email
    @GetMapping("/user")
    public ResponseEntity<?> getInfoKhachHang(Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            KhachHang kh = khachHangServiceImpl.getLoginByEmail(username);
            return ResponseEntity.ok(KhachHangMapper.khMapper(kh));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    //Update thông tin khách hàng
    @PostMapping("/updateKhachHang")
    public ResponseEntity<?> updateKhachHang(@RequestBody KhachHangDto khachHangDto) {
        KhachHang khh = khachHangServiceImpl.updateInfo(khachHangDto);
        return ResponseEntity.ok(KhachHangMapper.khMapper(khh));
    }
}
