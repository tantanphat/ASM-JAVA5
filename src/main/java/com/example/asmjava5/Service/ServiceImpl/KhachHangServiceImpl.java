package com.example.asmjava5.Service.ServiceImpl;

import com.example.asmjava5.Entity.KhachHang;
import com.example.asmjava5.Model.request.DangKyKhachHang;
import com.example.asmjava5.Model.request.KhachHangThongTin;
import com.example.asmjava5.Repository.KhachHangRepository;

import com.example.asmjava5.Service.KhachHangService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class KhachHangServiceImpl implements KhachHangService {
    // Khởi tạo một đối tượng mã hóa bcrypt
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
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
    public KhachHang updateInfo(KhachHangThongTin khachHang) {
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

    @Override
    public void dangKyKhachHangMoi(DangKyKhachHang dkkh) {
        String autoMaKH = khachHangRepository.AUTO_MaKH();
        KhachHang kh = new KhachHang();
        kh.setMaKH(autoMaKH);
        kh.setTenKH(dkkh.getTenKH());
        kh.setDiaChi(dkkh.getDiaChi());
        kh.setSdt(dkkh.getSdt());
        kh.setEmail(dkkh.getEmail());
        kh.setMatKhau(encoder.encode(dkkh.getMatKhau()));
        kh.setThanhVien(false);
        khachHangRepository.insertKH(kh.getTenKH(), kh.getDiaChi(), kh.getSdt(), kh.getEmail(),kh.getMatKhau(), kh.isThanhVien());
    }


}
