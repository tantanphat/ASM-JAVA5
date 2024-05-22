package com.example.asmjava5.Service.ServiceImpl;

import com.example.asmjava5.Entity.KhachHang;
import com.example.asmjava5.Repository.KhachHangDao;

import com.example.asmjava5.Service.KhachHangService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class KhachHangServiceImpl implements KhachHangService {
    @Autowired
    private KhachHangDao khachHangRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<KhachHang> getAllKhachHang() {
        return  khachHangRepository.findAll();
    }

    @Override
    public KhachHang getLoginByEmail(String email) {
        return khachHangRepository.findByEmail(email);
    }


//    public UserDetailsManager userDetailsManager(String email, String pass) {
//        UserDetails user = User.builder()
//                .username(email)
//                .password("{noop}" + pass)  // Không mã hóa mật khẩu (chỉ dùng cho mục đích minh họa)
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }


}
