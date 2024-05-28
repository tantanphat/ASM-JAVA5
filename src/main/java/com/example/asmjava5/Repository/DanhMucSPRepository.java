package com.example.asmjava5.Repository;

import com.example.asmjava5.Entity.DanhMucSP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DanhMucSPRepository extends JpaRepository<DanhMucSP,String> {

    @Query("select d from DanhMucSP d where d.maDM = :maDM")
     DanhMucSP findDanhMucSPbyMaDM(@Param("maDM") String maDM);

}
