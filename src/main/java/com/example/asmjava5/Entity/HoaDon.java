package com.example.asmjava5.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
}
