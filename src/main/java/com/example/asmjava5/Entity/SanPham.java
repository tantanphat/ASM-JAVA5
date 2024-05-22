package com.example.asmjava5.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "SanPham")
public class SanPham {
    @Id
    @Column(name = "MaSP", length = 10)
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
}
