package com.example.asmjava5.Service.ServiceImpl;

import com.example.asmjava5.Entity.NhanVien;
import com.example.asmjava5.Repository.NhanVienRepository;
import com.example.asmjava5.Service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NhanVienServiceImpl implements NhanVienService {
    @Autowired
    private NhanVienRepository nhanVienRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public List<NhanVien> getALlNhanVien() {
        return nhanVienRepository.findAll();
    }

    @Override
    public NhanVien findByMaNV(String MaNV) {
        return nhanVienRepository.findByMaNV(MaNV);
    }

    public String generateNewMaNV() {
        return jdbcTemplate.queryForObject("SELECT dbo.AUTO_MaNV()", String.class);
    }

    @Override
    public NhanVien addNhanVien(NhanVien nhanVien) {
        String newMaNV = nhanVienRepository.AUTO_MaNV();
        nhanVien.setMaNV(newMaNV);
        nhanVien.setIsActive(true);
        nhanVien.setMatkhau(encoder.encode(nhanVien.getMatkhau()));
        return nhanVienRepository.save(nhanVien);
    }

    @Override
    public NhanVien updateNhanVien(String maNV, NhanVien nhanVien) {
        NhanVien nv = findByMaNV(maNV);
        if (nv != null) {
            // Cập nhật các thông tin mới từ đối tượng nhanVien
            nv.setTenNV(nhanVien.getTenNV());
            nv.setGioiTinh(nhanVien.getGioiTinh());
            nv.setDiaChi(nhanVien.getDiaChi());
            nv.setDienThoai(nhanVien.getDienThoai());
            nv.setNgaySinh(nhanVien.getNgaySinh());
            nv.setVaiTro(nhanVien.getVaiTro());
            nv.setIsActive(true);
            // Lưu và trả về nhân viên đã được cập nhật
            return nhanVienRepository.saveAndFlush(nv);
        }
        return null;
    }

    @Override
    public void deleteNhanVien(String maNV) {
        NhanVien nv = findByMaNV(maNV);
        nv.setIsActive(false);
        nhanVienRepository.saveAndFlush(nv);
    }

    @Override
    public List<NhanVien> getALlNhanVienIsActive() {

        return nhanVienRepository.getALlNhanVienIsActive();
    }
}
