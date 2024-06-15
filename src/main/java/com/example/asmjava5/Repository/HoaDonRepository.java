package com.example.asmjava5.Repository;

import com.example.asmjava5.Entity.HoaDon;
import com.example.asmjava5.Entity.HoaDonChiTiet;
import com.example.asmjava5.Entity.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, Object> {
    @Query("SELECT hd FROM HoaDon hd WHERE hd.hd_MaHDBan = ?1")
    HoaDon findByMaHD(String MaHDBan);

    @Query(value = "SELECT dbo.AUTO_MaHD() AS newId")
    String AUTO_MaHD();

    @Query("SELECT hd FROM HoaDon hd WHERE hd.khachHang.maKH =?1")
    List<HoaDon> getAllHoaDonByMaKH(@Param("MaKH") String MaKH);


}
