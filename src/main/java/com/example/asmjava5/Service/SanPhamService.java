package com.example.asmjava5.Service;

import com.example.asmjava5.Entity.SanPham;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface SanPhamService {
    List<SanPham> getAllSanPham();

    SanPham getSanPhamById(String maSP);


}
