package com.example.asmjava5.Model.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DangKyKhachHang {
    private String diaChi;
    private String email;
    private String matKhau;
    private String sdt;
    private String tenKH;
    private boolean thanhVien = false;
}
