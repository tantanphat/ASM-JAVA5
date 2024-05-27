package com.example.asmjava5.Service.ServiceImpl;

import com.example.asmjava5.Entity.NhanVien;
import com.example.asmjava5.Repository.NhanVienRepository;
import com.example.asmjava5.Service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NhanVienServiceImpl implements NhanVienService {
    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Override
    public List<NhanVien> getALlNhanVien() {
        return nhanVienRepository.findAll();
    }

    @Override
    public NhanVien findByMaNV(String MaNV) {
        return nhanVienRepository.findByMaNV(MaNV);
    }
}
