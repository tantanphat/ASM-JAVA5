package com.example.asmjava5.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "KhachHang")
public class KhachHang {

    @Id
    @Column(name = "MaKH", nullable = false, length = 10)
    private String maKH;

    @Column(name = "TenKH")
    private String tenKH;

    @Column(name = "DiaChi")
    private String diaChi;

    @Column(name = "Sdt")
    private String sdt;

    @Column(name = "Email")
    private String email;

    @Column(name = "MatKhau")
    private String matKhau;

    @Column(name = "Thanhvien")
    private boolean thanhVien = false;

//    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
//    @JoinColumn(name="MaKH", referencedColumnName = "MaKH")
//    private List<HoaDon> hoaDon;

}
