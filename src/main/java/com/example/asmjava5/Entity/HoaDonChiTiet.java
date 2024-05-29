package com.example.asmjava5.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @Column(name = "MaHDCT")
    private String hdct_maHDCT;
    @Column(name = "MaHDBan")
    private String hdct_maHDBan;
    @Column(name = "MaSP")
    private String hdct_maSP;
    @Column(name = "SoLuong")
    private Integer hdct_soLuong;
    @Column(name = "GiamGia")
    private Double hdct_giamGia;

}
