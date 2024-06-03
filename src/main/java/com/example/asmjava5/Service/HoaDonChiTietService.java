package com.example.asmjava5.Service;

import com.example.asmjava5.Entity.HoaDon;
import com.example.asmjava5.Entity.HoaDonChiTiet;

import java.util.List;

public interface HoaDonChiTietService {
    List<HoaDonChiTiet> getAllHoaDonChiTiet();

    HoaDonChiTiet getHoaDonChiTietById(int id);

    List<HoaDonChiTiet> getAllHDCTByMaHD(String MaHDBan);

    void saveHoaDonChiTiet(HoaDonChiTiet hoaDonChiTiet);
}
