package com.example.asmjava5.Service.ServiceImpl;

import com.example.asmjava5.Entity.HoaDon;
import com.example.asmjava5.Entity.HoaDonChiTiet;
import com.example.asmjava5.Model.request.ThemHDCT;
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
    public void saveHoaDonChiTiet(String maHDBan, String maSP, int count, Double giamGia) {
        HoaDonChiTiet hdct = new HoaDonChiTiet();
        hdct.setHdct_maHDBan(maHDBan);
        hdct.setHdct_maSP(maSP);
        hdct.setHdct_soLuong(count);
        hdct.setHdct_giamGia(giamGia);
        hoaDonChiTietRepository.save(hdct);
    }

    @Override
    public void themHDCTByStaff(ThemHDCT hoaDonChiTiet) {
        HoaDonChiTiet hdct = new HoaDonChiTiet();
        hdct.setHdct_maHDBan(hoaDonChiTiet.getMaHDBan());
        hdct.setHdct_maSP(hoaDonChiTiet.getMaSP());
        hdct.setHdct_soLuong(hoaDonChiTiet.getSoLuong());
        hdct.setHdct_giamGia(hoaDonChiTiet.getGiamGia());
        hoaDonChiTietRepository.save(hdct);
    }

    @Override
    public void updateHDCT(HoaDonChiTiet hoaDonChiTiet ) {
        HoaDonChiTiet hdct = hoaDonChiTietRepository.findByMaHDCT(hoaDonChiTiet.getHdct_maHDCT());
        hdct.setHdct_maSP(hoaDonChiTiet.getHdct_maSP());
        hdct.setHdct_soLuong(hoaDonChiTiet.getHdct_soLuong());
        hdct.setHdct_giamGia(hoaDonChiTiet.getHdct_giamGia());
        hoaDonChiTietRepository.save(hdct);
    }

    @Override
    public void deleteHDCT(int mahdct) {
        HoaDonChiTiet hdct = hoaDonChiTietRepository.findByMaHDCT(mahdct);
        hoaDonChiTietRepository.delete(hdct);
    }

}
