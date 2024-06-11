package com.example.asmjava5.Service;

import com.example.asmjava5.Entity.SanPham;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface SanPhamService {
    List<SanPham> getAllSanPham();

    SanPham getSanPhamById(String maSP);

    List<SanPham> timKiemSanPham(String key);

    void updateSP(SanPham sp);

    void addSP(SanPham sp);

    void deleteSP(String sp);

    String AUTO_MASP();

    List<SanPham> getAllSP(Integer pageNo, Integer pageSize, String sortBy, String sortOrder);

    List<SanPham> findByMaDM(int maDM);



}
