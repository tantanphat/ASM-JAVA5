package com.example.asmjava5.Service;

import com.example.asmjava5.Entity.HoaDonChiTiet;
import com.example.asmjava5.Entity.SanPham;

import java.util.List;

public interface ThongKeService {
    List<HoaDonChiTiet> thongKeNhanVienChiTietThang(int thang);

    List<HoaDonChiTiet> thongKeNhanVienCoDonThang(int thang);

    List<HoaDonChiTiet> thongKeDoanhThu(int thang);

    List<HoaDonChiTiet> thongKeSanPhamBan(int thang);

    List<Object[]> thongKeSanPhamBanDuoc(int thang);

    List<Object[]> thongKeSanPhamKBanDuoc(int thang);

    List<Object[]> thongKeDoanhThuThangTheoNam(int thang , int year);
}
