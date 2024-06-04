package com.example.asmjava5.Repository;

import com.example.asmjava5.Entity.HoaDonChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThongKeRepository extends JpaRepository<HoaDonChiTiet, Long> {
}
