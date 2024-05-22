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
@Table(name = "DanhMucSP")
public class DanhMucSP {
    @Id
    @Column(name = "MaDM")
    private int maDM;

    @Column(name = "TenDanhMuc")
    private String tenDanhMuc;
}
