//package com.example.asmjava5.Security;
//
//import com.example.asmjava5.Entity.KhachHang;
//import com.example.asmjava5.Service.KhachHangService;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
//import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.util.Collections;
//import java.util.Map;
//import java.util.Optional;
//
//@Component
//public class CustomOAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {
//    @Autowired
//    private KhachHangService khachHangService;
//
//    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//
////    public CustomOAuth2AuthenticationSuccessHandler(KhachHangService khachHangService) {
////        this.khachHangService = khachHangService;
////    }
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//
//        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
//        OAuth2User oAuth2User = token.getPrincipal();
//        Map<String, Object> attributes = oAuth2User.getAttributes();
//        String email = (String) attributes.get("email");
//        String name = (String) attributes.get("name");
//        String address = (String) attributes.get("address");
//
//        KhachHang kh = khachHangService.getLoginByEmail(email);
//        if (kh == null) {
//            kh = new KhachHang();
//            kh.setEmail(email);
//            kh.setTenKH(name);
//            kh.setDiaChi(address);
//            kh.setMatKhau(encoder.encode("123"));
//            khachHangService.addKhachHang(kh);
//        }
//        Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
//        response.sendRedirect("/Trang-chu");
//    }
//}
