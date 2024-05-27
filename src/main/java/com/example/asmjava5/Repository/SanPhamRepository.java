package com.example.asmjava5.Repository;

import com.example.asmjava5.Entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, String> {
        @Query("select s from SanPham s where s.maSP = :maSP")
        SanPham findSanPhambyMaSP(@Param("maSP") String maSP);
}
