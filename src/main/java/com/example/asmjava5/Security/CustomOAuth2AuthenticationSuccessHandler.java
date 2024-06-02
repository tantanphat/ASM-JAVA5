package com.example.asmjava5.Security;

import com.example.asmjava5.Entity.KhachHang;
import com.example.asmjava5.Service.KhachHangService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class CustomOAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private KhachHangService khachHangService;

    public CustomOAuth2AuthenticationSuccessHandler(KhachHangService khachHangService) {
        this.khachHangService = khachHangService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        String email = token.getPrincipal().getAttribute("email");

//        Optional<KhachHang> existingUser = khachHangService.getLoginByEmail(email);

//        if (!existingUser.isPresent()) {
//
//        }

        response.sendRedirect("/Trang-chu");
    }
}
