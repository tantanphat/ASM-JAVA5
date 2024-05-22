package com.example.asmjava5.API;

import com.example.asmjava5.Constant.SessionAttr;
import com.example.asmjava5.Entity.KhachHang;
import com.example.asmjava5.Security.SecurityConfig;
import com.example.asmjava5.Service.ServiceImpl.KhachHangServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class KhachHangAPI {
    @Autowired
    HttpSession session;
    @Autowired
    HttpServletRequest req;
    @Autowired
    HttpServletResponse resp;
    @Autowired
    private KhachHangServiceImpl khachHangServiceImpl;

    @PostMapping( "/Dang-nhap")
    public ResponseEntity<?> login(@RequestBody Map<String, String> payload) {
        String email = payload.get("username"); // Lấy giá trị 'username' từ payload
        String password = payload.get("password"); // Lấy giá trị 'password' từ payload
        KhachHang kh = khachHangServiceImpl.getLoginByEmail(email);
        if (kh != null && kh.getMatKhau().equals(password)) {
            return ResponseEntity.ok(kh); // Trả về đối tượng KhachHang nếu đăng nhập thành công
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null); // Trả về mã lỗi 401 nếu đăng nhập thất bại
        }
    }

}
