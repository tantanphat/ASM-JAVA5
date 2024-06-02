package com.example.asmjava5.Service.ServiceImpl;

import com.example.asmjava5.Entity.HoaDon;
import com.example.asmjava5.Entity.HoaDonChiTiet;
import com.example.asmjava5.Entity.NhanVien;
import com.example.asmjava5.Repository.HoaDonRepository;
import com.example.asmjava5.Service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoaDonServiceImpl implements HoaDonService {
    @Autowired
    private HoaDonRepository hoaDonRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<HoaDon> getAllHoaDon() {
        return hoaDonRepository.findAll();
    }

    @Override
    public HoaDon getHoaDonByID(String hd_MaHDBan) {
        return hoaDonRepository.findByMaHD(hd_MaHDBan);
    }

    public String generateNewMaNV() {
        return jdbcTemplate.queryForObject("SELECT dbo.AUTO_MaHD()", String.class);
    }

    @Override
    public HoaDon addHoaDon(HoaDon hoaDon) {
        String newMaHD = generateNewMaNV();
        hoaDon.setHd_MaHDBan(newMaHD);
        return hoaDonRepository.save(hoaDon);
    }


}
