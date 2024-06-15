package com.example.asmjava5.Service;

import com.example.asmjava5.Entity.HoaDon;

import java.time.LocalDate;
import java.util.List;

public interface HoaDonService {

    List<HoaDon> getAllHoaDon();

    HoaDon getHoaDonByID(String hd_MaHDBan);

    HoaDon addHoaDon(HoaDon hoaDon);

    String AU_MaHD();

    void ThanhToanHoaDon(String maHDBan, String hd_MaNV , LocalDate hd_NgayBan, String hd_MaKH);

    void deleteHoaDon(String mahd);

    void creatHD(String maNV, String maKH);

    void updateHD(HoaDon hd);

    List<HoaDon> getHistoryHoaDonKhachHang(String maKH);
}
