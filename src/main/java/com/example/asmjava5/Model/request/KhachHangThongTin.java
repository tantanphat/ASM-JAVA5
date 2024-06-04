package com.example.asmjava5.Model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//Khách hàng update thông tin
public class KhachHangThongTin {
    private String email;
    private String hoTen;
    private String diaChi;
    private String soDienThoai;
}
