package com.example.asmjava5.Repository;

import com.example.asmjava5.Entity.HoaDon;
import com.example.asmjava5.Entity.HoaDonChiTiet;
import com.example.asmjava5.Entity.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, String> {
    @Query("SELECT hd FROM HoaDon hd WHERE hd.hd_MaHDBan = ?1")
     HoaDon findByMaHD(String MaHDBan);

    @Query(value = "SELECT dbo.AUTO_MaHD() AS newId")
    String AUTO_MaHD();
}
