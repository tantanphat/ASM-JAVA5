package com.example.asmjava5.Service.ServiceImpl;

import com.example.asmjava5.Entity.HoaDon;
import com.example.asmjava5.Repository.HoaDonRepository;
import com.example.asmjava5.Service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    @Override
    public String AU_MaHD() {
        return hoaDonRepository.AUTO_MaHD();
    }

    @Override
    public void ThanhToanHoaDon(String maHDBan, String hd_MaNV, LocalDate hd_NgayBan, String hd_MaKH) {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setHd_MaHDBan(maHDBan);
        hoaDon.setHd_MaNV(hd_MaNV);
        hoaDon.setHd_NgayBan(hd_NgayBan);
        hoaDon.setHd_MaKH(hd_MaKH);
        hoaDonRepository.save(hoaDon);
    }

    @Override
    public void deleteHoaDon(String mahd) {
        hoaDonRepository.deleteById(mahd);
    }

    @Override
    public void creatHD(String maNV, String maKH) {
        HoaDon  hoaDon = new HoaDon();
        hoaDon.setHd_MaHDBan(generateNewMaNV());
        hoaDon.setHd_NgayBan(LocalDate.now());
        hoaDon.setHd_MaNV(maNV);
        hoaDon.setHd_MaKH(maKH);
        hoaDonRepository.save(hoaDon);
    }

    @Override
    public void updateHD(HoaDon hd) {
        HoaDon update = hoaDonRepository.getById(hd.getHd_MaHDBan());
        update.setHd_MaKH(hd.getHd_MaKH());
        update.setHd_MaNV(hd.getHd_MaNV());
        update.setHd_NgayBan(hd.getHd_NgayBan());
        hoaDonRepository.save(update);
    }

    @Override
    public List<HoaDon> getHistoryHoaDonKhachHang(String maKH) {

        return hoaDonRepository.getAllHoaDonByMaKH(maKH);
    }


}
