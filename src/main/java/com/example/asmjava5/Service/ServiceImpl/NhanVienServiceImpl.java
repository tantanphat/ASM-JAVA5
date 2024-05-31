package com.example.asmjava5.Service.ServiceImpl;

import com.example.asmjava5.Entity.NhanVien;
import com.example.asmjava5.Repository.NhanVienRepository;
import com.example.asmjava5.Service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NhanVienServiceImpl implements NhanVienService {
    @Autowired
    private NhanVienRepository nhanVienRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String generateNewMaNV() {
        return jdbcTemplate.queryForObject("SELECT dbo.AUTO_MaNV()", String.class);
    }

    @Override
    public List<NhanVien> getALlNhanVien() {
        return nhanVienRepository.findAll();
    }

    @Override
    public NhanVien findByMaNV(String MaNV) {
        return nhanVienRepository.findByMaNV(MaNV);
    }

    @Override
    public NhanVien addNhanVien(NhanVien nhanVien) {
        String newMaNV = generateNewMaNV();
        nhanVien.setMaNV(newMaNV);
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
            nv.setMatkhau(nhanVien.getMatkhau());
            nv.setVaiTro(nhanVien.getVaiTro());
            // Lưu và trả về nhân viên đã được cập nhật
            return nhanVienRepository.saveAndFlush(nv);
        }
        return null;
    }

    @Override
    public void deleteNhanVien(String maNV) {
        NhanVien existingNhanVien = findByMaNV(maNV);
        if (existingNhanVien != null) {
            nhanVienRepository.delete(existingNhanVien);
        }
    }
}
