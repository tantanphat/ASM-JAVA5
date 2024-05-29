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

    @Column(name = "MaKH")
    private String hd_MaKH;

    @OneToMany(cascade= {CascadeType.ALL})
    @JoinColumn(name="MaHDBan", referencedColumnName = "MaHDBan")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private List<HoaDonChiTiet> hoaDonChiTiet;
}
