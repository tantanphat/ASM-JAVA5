package com.example.asmjava5.Model.mapper;

import com.example.asmjava5.Entity.KhachHang;
import com.example.asmjava5.Model.request.KhachHangDto;

public class KhachHangMapper {
    public static KhachHangDto khMapper(KhachHang kh){
        KhachHangDto khDto = new KhachHangDto();
        khDto.setEmail(kh.getEmail());
        khDto.setHoTen(kh.getTenKH());
        khDto.setDiaChi(kh.getDiaChi());
        khDto.setSoDienThoai(kh.getSdt());
        return khDto;
    }
}


