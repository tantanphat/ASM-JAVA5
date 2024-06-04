package com.example.asmjava5.Repository;

import com.example.asmjava5.Entity.HoaDonChiTiet;
import jakarta.persistence.NamedStoredProcedureQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, Long> {
    @Query("SELECT hdct FROM HoaDonChiTiet hdct WHERE hdct.hdct_maHDCT = ?1")
    HoaDonChiTiet findByMaHDCT(int MaHDCT);

    @Query("SELECT hdct FROM HoaDonChiTiet hdct WHERE hdct.hdct_maHDBan = ?1")
    List<HoaDonChiTiet> findAllByMaHDBan(String MaHDBan);

    @Query("UPDATE HoaDonChiTiet SET hdct_maSP = ?1,hdct_soLuong = ?2,hdct_giamGia = ?3 WHERE hdct_maHDCT = ?4")
    void updateHoaDonChiTiet( String maSP,int SoLuong, Double GiamGia, int MaHDCT);


}
