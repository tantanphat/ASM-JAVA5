package com.example.asmjava5.Repository;

import com.example.asmjava5.Entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, Object> {
        public static final String SAN_PHAM = "SanPham";
        public static final String AUTO_MA_SP = "[dbo].[AUTO_MaSP]()";

        @Query("select s from SanPham s where s.maSP = :maSP")
        SanPham findSanPhambyMaSP(@Param("maSP") String maSP);

        @Query("SELECT s FROM SanPham s WHERE s.tenSP LIKE %:keyword%")
        List<SanPham> timKiemSanPham(@Param("keyword") String keyword);

        @Query(value = "SELECT dbo.AUTO_MaSP() AS newId")
        String AUTO_MA_SP();

        @Query("SELECT s FROM SanPham s WHERE s.danhMucSP = ?1")
        List<SanPham> listSPByMaDMSP(@Param("maDMSP") int maDMSP);

        // Tìm kiếm sản phẩm theo mã danh mục
        @Query("SELECT s FROM SanPham s WHERE s.maDM = ?1")
        List<SanPham> findByMaDM(@Param("maDM") int maDM);



}





