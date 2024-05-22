package com.example.asmjava5.Service;

import com.example.asmjava5.Entity.SanPham;

import java.util.List;

public interface SanPhamService {
    List<SanPham> getAllSanPham();

    SanPham getSanPhamById(String maSP);
}
