package com.example.asmjava5.Service;

import com.example.asmjava5.Entity.SanPham;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface SanPhamService {
    List<SanPham> getAllSanPham();

    SanPham getSanPhamById(String maSP);

    void addToCart(String maSP, String size, int soLuong, HttpSession session);

    void removeFromCart(String maSP, String size, HttpSession session);

    float calculateTotal(HttpSession session);

    List<SanPham> getCartItems(HttpSession session);
    SanPham findSanPhamByMaSP(String maSP);
    List<SanPham> timKiemSanPham(String key);
}
