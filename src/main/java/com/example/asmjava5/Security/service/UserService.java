package com.example.asmjava5.Security.service;

import com.example.asmjava5.Entity.KhachHang;
import com.example.asmjava5.Entity.NhanVien;
import com.example.asmjava5.Service.ServiceImpl.KhachHangServiceImpl;
import com.example.asmjava5.Service.ServiceImpl.NhanVienServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private KhachHangServiceImpl KhachHangServiceImpl;

    @Autowired
    private NhanVienServiceImpl nhanVienServiceImpl;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        KhachHang kh = KhachHangServiceImpl.getLoginByEmail(username);
        if (kh != null) {
            return new KhachHangDetails(kh);
        }

        NhanVien nv = nhanVienServiceImpl.findByMaNV(username);
        if (nv != null) {
            return new NhanVienDetails(nv);
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
