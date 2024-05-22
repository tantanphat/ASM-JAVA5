package com.example.asmjava5.Repository;

import com.example.asmjava5.Entity.DanhMucSP;
import com.example.asmjava5.Entity.KhachHang;
import com.example.asmjava5.Entity.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NhanVienDao extends JpaRepository<NhanVien, String> {
    @Repository
    public interface NhanVienRepository extends JpaRepository<NhanVien, String> {

        @Query("SELECT k FROM NhanVien k WHERE k.MaNV = ?1")
        NhanVien findByMaNV(String MaNV);
    }
}
