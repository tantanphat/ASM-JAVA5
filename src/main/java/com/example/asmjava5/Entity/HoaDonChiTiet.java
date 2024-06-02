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
    @Column(name = "MaHDBan")
    private String hdct_maHDBan;
    @Column(name = "MaSP")
    private String hdct_maSP;
    @Column(name = "SoLuong")
    private Integer hdct_soLuong;
    @Column(name = "GiamGia")
    private Double hdct_giamGia;

    @ManyToOne
    @JoinColumn(name="MaSP", referencedColumnName = "MaSP", insertable = false, updatable = false)
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private SanPham sanPham;

}
