package com.example.asmjava5.Service.ServiceImpl;

import com.example.asmjava5.Entity.KhachHang;
import com.example.asmjava5.Entity.NhanVien;
import com.example.asmjava5.Model.request.KhachHangDto;
import com.example.asmjava5.Repository.KhachHangRepository;

import com.example.asmjava5.Service.KhachHangService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class KhachHangServiceImpl implements KhachHangService {

    @Autowired
    private KhachHangRepository khachHangRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

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
    public KhachHang findBymaKH(String MaKH) {
        return khachHangRepository.findByMaKH(MaKH);
    }

    public String generateNewMaKH() {
        return jdbcTemplate.queryForObject("SELECT dbo.AUTO_MaKH()", String.class);
    }

    @Override
    public KhachHang addKhachHang(KhachHang khachHang) {
        String newMaKH = generateNewMaKH();
        khachHang.setMaKH(newMaKH);
        return khachHangRepository.save(khachHang);
    }

    @Override
    public KhachHang updateKhachHang(String maKH, KhachHang khachHang) {
        KhachHang kh = findBymaKH(maKH);
        if (kh != null) {
            // Cập nhật các thông tin mới từ đối tượng khách hàng
            kh.setTenKH(khachHang.getTenKH());
            kh.setDiaChi(khachHang.getDiaChi());
            kh.setSdt(khachHang.getSdt());
            kh.setEmail(khachHang.getEmail());
            kh.setThanhVien(khachHang.isThanhVien());
            // Lưu và trả về khách hàng đã được cập nhật
            return khachHangRepository.saveAndFlush(kh);
        }
        return null;
    }

    @Override
    public void deleteKhachHang(String maKH) {
        KhachHang kh = findBymaKH(maKH);
        if (kh != null) {
            khachHangRepository.delete(kh);
        }
    }


}
