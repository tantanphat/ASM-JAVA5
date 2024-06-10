package com.example.asmjava5.API;

import com.example.asmjava5.Entity.NhanVien;
import com.example.asmjava5.Repository.NhanVienRepository;
import com.example.asmjava5.Service.NhanVienService;
import com.example.asmjava5.Service.ServiceImpl.NhanVienServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/nhan-vien")
public class NhanVienAPI {

    @Autowired
    private NhanVienService nvService;


    @GetMapping("")
    public List<NhanVien> getAllNhanVien() {
        return nvService.getALlNhanVien();
    }

    @GetMapping("/{manv}")
    public NhanVien getOneNhanVien(@PathVariable("manv") String manv) {
        return nvService.findByMaNV(manv);
    }

    @PostMapping("/add")
    public ResponseEntity<NhanVien> addNhanVien(@RequestBody NhanVien nhanVien) {
        NhanVien newNhanVien = nvService.addNhanVien(nhanVien);
        return ResponseEntity.ok(newNhanVien);
    }

    @PutMapping("/update/{maNV}")
    public ResponseEntity<NhanVien> updateNhanVien(@PathVariable("maNV") String maNV, @RequestBody NhanVien nhanVien) {
        NhanVien updatedNhanVien = nvService.updateNhanVien(maNV, nhanVien);
        if (updatedNhanVien != null) {
            return ResponseEntity.ok(updatedNhanVien);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delele/{maNV}")
    public ResponseEntity<Void> deleteNhanVien(@PathVariable("maNV") String maNV) {
        nvService.deleteNhanVien(maNV);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/listMaNV")
    public List<String> listMaNV() {
        List<NhanVien> nv = nvService.getALlNhanVien();
        List<String> manv = new ArrayList<>();
        for (NhanVien v : nv) {
            manv.add(v.getMaNV());
        }
        return manv;
    }
}