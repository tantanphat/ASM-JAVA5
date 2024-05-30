package com.example.asmjava5.Repository;

import com.example.asmjava5.Entity.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, Long> {

    @Query("SELECT k FROM KhachHang k WHERE k.email = ?1")
    KhachHang findByEmail(String email);

    @Query("SELECT kh FROM KhachHang kh WHERE kh.maKH = ?1")
    KhachHang findByMaKH(String maKH);
}

