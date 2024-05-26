package com.example.asmjava5.API;

import com.example.asmjava5.Entity.KhachHang;
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

    @GetMapping("/user")
    public ResponseEntity<?> getInfoKhachHang(Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            KhachHang kh = khachHangServiceImpl.getLoginByEmail(username);
            return ResponseEntity.ok(kh);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    //Update thông tin khách hàng
    @PostMapping( "/user")
    public ResponseEntity<?> updateKhachHang() {

        return null;
    }






}
