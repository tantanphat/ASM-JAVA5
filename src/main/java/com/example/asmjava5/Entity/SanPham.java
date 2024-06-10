package com.example.asmjava5.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "SanPham")
public class SanPham {
    @Id
    @Column(name = "MaSP",nullable = false, length = 10)
    private String maSP;

    @Column(name = "TenSP", length = 50, nullable = false)
    private String tenSP;

    @Column(name = "SoLuong")
    private int soLuong;

    @Column(name = "DonGiaBan")
    private float giaBan;

    @Column(name = "HinhAnh")
    private String anh;

    @Column(name = "GhiChu", length = 50)
    private String ghiChu;

    @Column(name = "MaDM")
    private int maDM;

    @Column(name = "KichCo", length = 50)
    private String size;

    @ManyToOne
    @JoinColumn(name="MaDM", referencedColumnName = "MaDM", insertable = false, updatable = false)
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private DanhMucSP danhMucSP;

//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name="MaSP", referencedColumnName = "MaSP" , updatable = false)
//    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
//    private List<HoaDonChiTiet> hoaDonChiTiet;

}