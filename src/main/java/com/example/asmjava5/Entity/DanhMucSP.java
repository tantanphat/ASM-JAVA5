package com.example.asmjava5.Entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "DanhMucSP")
public class DanhMucSP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaDM")
    private int maDM;

    @Column(name = "TenDanhMuc")
    private String tenDanhMuc;
}
