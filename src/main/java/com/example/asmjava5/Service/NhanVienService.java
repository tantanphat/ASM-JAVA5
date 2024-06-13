package com.example.asmjava5.Service;

import com.example.asmjava5.Entity.NhanVien;

import java.util.List;

public interface NhanVienService {

    List<NhanVien> getALlNhanVien();

    NhanVien findByMaNV(String MaNV);

    //Thêm một nhân viên
    NhanVien addNhanVien(NhanVien nhanVien);

    NhanVien updateNhanVien(String maNV, NhanVien nhanVien);

    void deleteNhanVien(String maNV);

    List<NhanVien> getALlNhanVienIsActive();

}
