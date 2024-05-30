package com.example.asmjava5.Service.ServiceImpl;

import com.example.asmjava5.Entity.KhachHang;
import com.example.asmjava5.Entity.NhanVien;
import com.example.asmjava5.Model.request.KhachHangDto;
import com.example.asmjava5.Repository.KhachHangRepository;

import com.example.asmjava5.Service.KhachHangService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class KhachHangServiceImpl implements KhachHangService {

    @Autowired
    private KhachHangRepository khachHangRepository;
    @Override
    public List<KhachHang> getAllKhachHang() {
        return  khachHangRepository.findAll();
    }

    @Override
    public KhachHang getLoginByEmail(String email) {
        return khachHangRepository.findByEmail(email);
    }

    @Override
    public KhachHang updateInfo(KhachHangDto khachHang) {
        KhachHang kh = khachHangRepository.findByEmail(khachHang.getEmail());
        kh.setTenKH(khachHang.getHoTen());
        kh.setDiaChi(khachHang.getDiaChi());
        kh.setSdt(khachHang.getSoDienThoai());
        khachHangRepository.save(kh);
        return kh;
    }

    @Override
    public KhachHang findByMaKH(String MaKH) {
        return khachHangRepository.findByMaKH(MaKH);
    }


}
