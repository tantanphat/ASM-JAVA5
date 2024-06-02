package com.example.asmjava5.Repository;

import com.example.asmjava5.Entity.HoaDonChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, Long> {
    @Query("SELECT hdct FROM HoaDonChiTiet hdct WHERE hdct.hdct_maHDCT = ?1")
    HoaDonChiTiet findByMaHDCT(int MaHDCT);

    @Query("SELECT hdct FROM HoaDonChiTiet hdct WHERE hdct.hdct_maHDBan = ?1")
    List<HoaDonChiTiet> findAllByMaHDBan(String MaHDBan);  // Use String type here
}
