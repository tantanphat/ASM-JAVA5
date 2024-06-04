package com.example.asmjava5.Service;

import com.example.asmjava5.Entity.HoaDonChiTiet;

import java.util.List;

public interface ThongKeService {
    public List<HoaDonChiTiet> thongKeNhanVienChiTietThang(int thang);

    public List<HoaDonChiTiet> thongKeNhanVienCoDonThang(int thang);
}
