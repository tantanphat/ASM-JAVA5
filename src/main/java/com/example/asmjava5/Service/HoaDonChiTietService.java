package com.example.asmjava5.Service;

import com.example.asmjava5.Entity.HoaDon;
import com.example.asmjava5.Entity.HoaDonChiTiet;
import com.example.asmjava5.Model.request.ThemHDCT;

import java.util.List;

public interface HoaDonChiTietService {
    List<HoaDonChiTiet> getAllHoaDonChiTiet();

    HoaDonChiTiet getHoaDonChiTietById(int id);

    List<HoaDonChiTiet> getAllHDCTByMaHD(String MaHDBan);

    void saveHoaDonChiTiet(String maHDBan,String maSP,int count,Double giamGia);

    void themHDCTByStaff(ThemHDCT hoaDonChiTiet);

    void updateHDCT(HoaDonChiTiet hoaDonChiTiet );

    void deleteHDCT(int mahdct);
}
