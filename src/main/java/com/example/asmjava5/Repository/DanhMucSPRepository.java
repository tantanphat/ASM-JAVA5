package com.example.asmjava5.Repository;

import com.example.asmjava5.Entity.DanhMucSP;
import com.example.asmjava5.Entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DanhMucSPRepository extends JpaRepository<DanhMucSP,String> {

    @Query("select d from DanhMucSP d where d.maDM = :maDM")
     DanhMucSP findDanhMucSPbyMaDM(@Param("maDM") String maDM);

    @Query("select d from DanhMucSP d where d.maDM = :maDM")
    DanhMucSP getAllDanhMucSP(@Param("maDM") int maDM);

    @Query("select s from SanPham s")
    List<SanPham> findAllSanPham();
}
