package com.example.asmjava5.Model.mapper;

import com.example.asmjava5.Entity.KhachHang;
import com.example.asmjava5.Model.request.KhachHangThongTin;

public class KhachHangMapper {
    public static KhachHangThongTin khMapper(KhachHang kh){
        KhachHangThongTin khDto = new KhachHangThongTin();
        khDto.setEmail(kh.getEmail());
        khDto.setHoTen(kh.getTenKH());
        khDto.setDiaChi(kh.getDiaChi());
        khDto.setSoDienThoai(kh.getSdt());
        return khDto;
    }
}


