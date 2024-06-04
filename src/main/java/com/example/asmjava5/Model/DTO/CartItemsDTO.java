package com.example.asmjava5.Model.DTO;

import com.example.asmjava5.Entity.SanPham;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@ToString
public class CartItemsDTO {

    private String maSP;
    private String tenSP;
    private float giaBan;
    private int quantity;
    private String anh;
    private String size;

    public CartItemsDTO(String maSP, String tenSP, float giaBan, int quantity, String anh, String size) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.giaBan = giaBan;
        this.quantity = quantity;
        this.anh = anh;
        this.size = size;
    }
}

