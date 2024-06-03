package com.example.asmjava5.Service.ServiceImpl;

import com.example.asmjava5.Entity.HoaDon;
import com.example.asmjava5.Entity.HoaDonChiTiet;
import com.example.asmjava5.Repository.HoaDonChiTietRepository;
import com.example.asmjava5.Service.HoaDonChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoaDonChiTietServiceImpl implements HoaDonChiTietService {
    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;

    @Override
    public List<HoaDonChiTiet> getAllHoaDonChiTiet() {
        return hoaDonChiTietRepository.findAll();
    }

    @Override
    public HoaDonChiTiet getHoaDonChiTietById(int id) {
        return hoaDonChiTietRepository.findByMaHDCT(id);
    }

    @Override
    public List<HoaDonChiTiet> getAllHDCTByMaHD(String MaHDBan) {
        return hoaDonChiTietRepository.findAllByMaHDBan(MaHDBan);
    }

    @Override
    public void saveHoaDonChiTiet(HoaDonChiTiet hoaDonChiTiet) {
        hoaDonChiTietRepository.save(hoaDonChiTiet);
    }
}
