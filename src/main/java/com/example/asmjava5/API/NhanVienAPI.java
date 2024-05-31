package com.example.asmjava5.API;

import com.example.asmjava5.Entity.NhanVien;
import com.example.asmjava5.Repository.NhanVienRepository;
import com.example.asmjava5.Service.ServiceImpl.NhanVienServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/nhan-vien")
public class NhanVienAPI {

    @Autowired
    private NhanVienServiceImpl nvServiceImpl;

    @GetMapping("")
    public List<NhanVien> getAllNhanVien() {
        return nvServiceImpl.getALlNhanVien();
    }

    @GetMapping("/{manv}")
    public NhanVien getOneNhanVien(@PathVariable("manv") String manv) {
        return nvServiceImpl.findByMaNV(manv);
    }

    @PostMapping("/add")
    public ResponseEntity<NhanVien> addNhanVien(@RequestBody NhanVien nhanVien) {
        NhanVien newNhanVien = nvServiceImpl.addNhanVien(nhanVien);
        return ResponseEntity.ok(newNhanVien);
    }

    @PutMapping("/{maNV}")
    public ResponseEntity<NhanVien> updateNhanVien(@PathVariable("maNV") String maNV, @RequestBody NhanVien nhanVien) {
        NhanVien updatedNhanVien = nvServiceImpl.updateNhanVien(maNV, nhanVien);
        if (updatedNhanVien != null) {
            return ResponseEntity.ok(updatedNhanVien);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{maNV}")
    public ResponseEntity<Void> deleteNhanVien(@PathVariable("maNV") String maNV) {
        nvServiceImpl.deleteNhanVien(maNV);
        return ResponseEntity.noContent().build();
    }
}
