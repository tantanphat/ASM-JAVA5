package com.example.asmjava5.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name = "NhanVien")
public class NhanVien {
    @Id
    @Column(name = "MaNV", nullable = false, length = 10)
    private String MaNV;

    @Column(name = "TenNV")
    private String TenNV;

    @Column(name = "GioiTinh")
    private Boolean GioiTinh;

    @Column(name = "DiaChi")
    private String DiaChi;

    @Column(name = "DienThoai")
    private String DienThoai;

    @Column(name = "NgaySinh")
    private LocalDate NgaySinh;

    @Column(name = "Matkhau")
    private String Matkhau;

    @Column(name = "isActive")
    private Boolean isActive = true;

    @Column(name = "VaiTro")
    private Boolean VaiTro;




}

