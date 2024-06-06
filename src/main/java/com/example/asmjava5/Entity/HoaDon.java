package com.example.asmjava5.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "HoaDon")
public class HoaDon {

    @Id
    @Column(name = "MaHDBan")
    private String hd_MaHDBan;

    @Column(name = "MaNV")
    private String hd_MaNV;

    @Column(name = "NgayBan")
    private LocalDate hd_NgayBan;

    @Column(name = "MaKH", nullable = false, length = 10)
    private String hd_MaKH;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name="MaHDBan", referencedColumnName = "MaHDBan")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private List<HoaDonChiTiet> hoaDonChiTiet;

    @ManyToOne
    @JoinColumn(name="MaNV", referencedColumnName = "MaNV", insertable = false, updatable = false)
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private NhanVien nhanVien;

    @ManyToOne
    @JoinColumn(name="MaKH", referencedColumnName = "MaKH", insertable = false, updatable = false)
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private KhachHang khachHang;
}