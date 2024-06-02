package com.example.asmjava5.Service;

import com.example.asmjava5.Entity.HoaDon;

import java.util.List;

public interface HoaDonService {

    List<HoaDon> getAllHoaDon();

    HoaDon getHoaDonByID(String hd_MaHDBan);

    HoaDon addHoaDon(HoaDon hoaDon);
}
