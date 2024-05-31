package com.example.asmjava5.Model.mapper;

import com.example.asmjava5.Entity.KhachHang;
import com.example.asmjava5.Model.request.KhachHangModel;

public class KhachHangMapper {
    public static KhachHangModel khMapper(KhachHang kh){
        KhachHangModel khDto = new KhachHangModel();
        khDto.setEmail(kh.getEmail());
        khDto.setHoTen(kh.getTenKH());
        khDto.setDiaChi(kh.getDiaChi());
        khDto.setSoDienThoai(kh.getSdt());
        return khDto;
    }
}


