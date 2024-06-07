package com.example.asmjava5.Model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePassword {
    String maKH;
    String matKhauCu;
    String matKhauMoi;
}
