package com.example.asmjava5.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name = "NhanVien")
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaNV")
    private String MaNV;

    @Column(name = "TenNV")
    private String TenNV;

    @Column(name = "GioiTinh")
    private Boolean GioiTinh;

    @Column(name = "DiaChi")
    private String DiaChi;

    @Column(name = "NgaySinh")
    private LocalDate NgaySinh;

    @Column(name = "Matkhau")
    private String Matkhau;

    @Column(name = "VaiTro")
    private Boolean VaiTro;

}

