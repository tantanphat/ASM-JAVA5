package com.example.asmjava5.Service;

import com.example.asmjava5.Entity.NhanVien;

import java.util.List;

public interface NhanVienService {

    List<NhanVien> getALlNhanVien();

    NhanVien findByMaNV(String MaNV);


}
