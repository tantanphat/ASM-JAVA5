package com.example.asmjava5.Service;

import com.example.asmjava5.Entity.KhachHang;

import java.util.List;

public interface KhachHangService {
    List<KhachHang> getAllKhachHang();

    KhachHang getLoginByEmail(String email);
}
