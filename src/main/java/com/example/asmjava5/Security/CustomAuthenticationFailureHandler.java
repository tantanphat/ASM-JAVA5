package com.example.asmjava5.Security;

import com.example.asmjava5.Entity.KhachHang;
import com.example.asmjava5.Repository.KhachHangRepository;
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
    private KhachHangRepository khachHangRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception)
            throws IOException, ServletException {

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
//        Map<String, Object> data = new HashMap<>();
//        data.put(
//                "timestamp",
//                Calendar.getInstance().getTime());
//        data.put(
//                "exception",
//                exception.getMessage());
//
//        response.getOutputStream()
//                .println(objectMapper.writeValueAsString(data));

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        KhachHang kh = khachHangRepository.findByEmail(username);

        String errorMessage= "Lỗi xác thực";
        if ( kh == null ) {
            errorMessage = "Không tìm thấy người dùng trong hệ thống";
        } else if (exception instanceof BadCredentialsException) {
            errorMessage = "Mật khẩu không chính xác";
        }

        response.getWriter().write("{\"error\": \"" + errorMessage + "\"}");

    }

}
