package com.example.asmjava5.Service;

import com.example.asmjava5.Entity.HoaDon;
import com.example.asmjava5.Entity.KhachHang;
import com.example.asmjava5.Model.request.LichSuMuaHang;

import java.util.List;

public interface LichSuMuaHangService {

    List<Object> getAllHoaDonByEmail(String email);

    List<Object> getAllItemsSP(String MaHDBan,String email);

}
