package com.example.asmjava5.Service;

import com.example.asmjava5.Entity.KhachHang;
import com.example.asmjava5.Model.request.KhachHangModel;

import java.util.List;

public interface KhachHangService {
    List<KhachHang> getAllKhachHang();

    KhachHang getLoginByEmail(String email);

    KhachHang updateInfo(KhachHangModel khachHang);

    KhachHang findByMaKH(String MaKH);
}
