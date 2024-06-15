package com.example.asmjava5.Repository;

import com.example.asmjava5.Entity.KhachHang;
import com.example.asmjava5.Model.request.LichSuMuaHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, Long> {
    public static final String KHACH_HANG = "KhachHang";
    public static final String AUTO_MA_KH = "[dbo].[AUTO_MaKH]()";

    @Query("SELECT k FROM KhachHang k WHERE k.email = ?1")
    KhachHang findByEmail(String email);

    @Query("SELECT kh FROM KhachHang kh WHERE kh.maKH = ?1")
    KhachHang findByMaKH(String maKH);

    @Query("SELECT kh FROM KhachHang kh WHERE kh.sdt = ?1")
    KhachHang findBySDT(String sdt);

    @Query("SELECT kh FROM KhachHang kh WHERE kh.maKH LIKE ?1 OR kh.email LIKE %?1% OR kh.sdt LIKE ?1")
    KhachHang searchKH(String key);

    @Query(value = "SELECT dbo.AUTO_MaKH() AS newId")
    String AUTO_MaKH();

    @Query(value = "INSERT INTO " + KHACH_HANG  + " (MaKH, TenKH, DiaChi, Sdt, Email,MatKhau, Thanhvien) VALUES (" + AUTO_MA_KH + ", :tenKH, :diaChi, :sdt, :email,:matKhau, :thanhvien)", nativeQuery = true)
    @Modifying
    void  insertKH(@Param("tenKH") String tenKH, @Param("diaChi") String diaChi, @Param("sdt") String sdt, @Param("email") String email, @Param("matKhau") String matKhau , @Param("thanhvien") boolean thanhvien);

}

