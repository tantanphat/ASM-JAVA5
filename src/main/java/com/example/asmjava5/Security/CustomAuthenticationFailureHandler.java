package com.example.asmjava5.Security;

import com.example.asmjava5.Entity.KhachHang;
import com.example.asmjava5.Entity.NhanVien;
import com.example.asmjava5.Repository.KhachHangRepository;
import com.example.asmjava5.Service.ServiceImpl.KhachHangServiceImpl;
import com.example.asmjava5.Service.ServiceImpl.NhanVienServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Autowired
    private KhachHangServiceImpl khachHangServiceImpl;

    @Autowired
    private NhanVienServiceImpl nhanVienServiceImpl;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception)
            throws IOException, ServletException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String errorMessage= "Lỗi xác thực";
        String path = request.getServletPath();

        switch (path) {
            case "/Dang-nhap":
                KhachHang kh = khachHangServiceImpl.getLoginByEmail(username);
                if ( kh == null ) {
                    errorMessage = "Không tìm thấy người dùng trong hệ thống";
                } else if (exception instanceof BadCredentialsException) {
                    errorMessage = "Mật khẩu không chính xác";
                }
                response.getWriter().write("{\"error\": \"" + errorMessage + "\"}");
                break;
            case "/admin/Login":
                NhanVien nv = nhanVienServiceImpl.findByMaNV(username);
                if ( nv == null ) {
                    errorMessage = "Không tìm thấy người dùng trong hệ thống";
                } else if (exception instanceof BadCredentialsException) {
                    errorMessage = "Mật khẩu không chính xác";
                }
                response.getWriter().write("{\"error\": \"" + errorMessage + "\"}");
                break;
        }
    }

}
