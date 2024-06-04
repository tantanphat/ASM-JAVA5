package com.example.asmjava5.Service;

import com.example.asmjava5.Entity.SanPham;
import com.example.asmjava5.Model.request.TimKiemSP;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface SanPhamService {
    List<SanPham> getAllSanPham();

    SanPham getSanPhamById(String maSP);

    TimKiemSP timSPinHDCCT(String masp);
}
