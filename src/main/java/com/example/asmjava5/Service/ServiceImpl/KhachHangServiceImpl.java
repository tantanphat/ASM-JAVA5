package com.example.asmjava5.Service.ServiceImpl;

import com.example.asmjava5.Entity.KhachHang;
import com.example.asmjava5.Model.request.ChangePassword;
import com.example.asmjava5.Model.request.DangKyKhachHang;
import com.example.asmjava5.Model.request.KhachHangThongTin;
import com.example.asmjava5.Repository.KhachHangRepository;
import com.example.asmjava5.Service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class KhachHangServiceImpl implements KhachHangService {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
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
    public KhachHang updateInfo(KhachHangThongTin khachHang) {
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

    @Override
    public String AUTO_MAKH() {
        return khachHangRepository.AUTO_MaKH();
    }

    @Override
    public KhachHang timKiemKH(String key) {
        return khachHangRepository.searchKH(key);
    }

    @Override
    public KhachHang findBySDY(String sdt) {
        return khachHangRepository.findBySDT(sdt);
    }

    @Override
    public KhachHang changePassword(ChangePassword changePassword) {
        KhachHang kh = findBymaKH(changePassword.getMaKH());
        kh.setMatKhau(encoder.encode(changePassword.getMatKhauMoi()));
        return khachHangRepository.save(kh);
    }

    @Override
    public void forgotPassword(String email, String password) {
        KhachHang kh = khachHangRepository.findByEmail(email);
        kh.setMatKhau(encoder.encode(password));
        khachHangRepository.save(kh);
    }

}
