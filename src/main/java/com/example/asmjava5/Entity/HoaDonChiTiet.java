package com.example.asmjava5.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Table(name = "HoaDonChiTiet")
public class HoaDonChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaHDCT")
    private Integer hdct_maHDCT;
    @Column(name = "MaHDBan" ,nullable = true)
    private String hdct_maHDBan;
    @Column(name = "MaSP")
    private String hdct_maSP;
    @Column(name = "SoLuong")
    private Integer hdct_soLuong;
    @Column(name = "GiamGia")
    private Double hdct_giamGia;

//    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//    @JoinColumn(name="MaHDBan", referencedColumnName = "MaHDBan", insertable = false, updatable = false)
//    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
//    private HoaDon hoaDon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MaSP", referencedColumnName = "MaSP", insertable = false, updatable = false)
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private SanPham sanPham;

//    @ManyToOne
//    @JoinColumn(name="MaHDBan", referencedColumnName = "MaHDBan", insertable = false, updatable = false)
//    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
//    private HoaDon hoaDon;
//
//    @Transient
//    private NhanVien nhanVien;
//
//    @PostLoad
//    private void postLoad() {
//        if (nhanVien != null) {
//            this.nhanVien = hoaDon.getNhanVien();
//        }
//    }

}
