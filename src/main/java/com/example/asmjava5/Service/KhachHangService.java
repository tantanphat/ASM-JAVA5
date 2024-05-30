package com.example.asmjava5.Service;

import com.example.asmjava5.Entity.KhachHang;
import com.example.asmjava5.Entity.NhanVien;
import com.example.asmjava5.Model.request.KhachHangDto;

import java.util.List;

public interface KhachHangService {
    List<KhachHang> getAllKhachHang();

    KhachHang getLoginByEmail(String email);

    KhachHang updateInfo(KhachHangDto khachHang);

    KhachHang findByMaKH(String MaKH);
}
