package com.example.asmjava5.API;

import com.example.asmjava5.Entity.NhanVien;
import com.example.asmjava5.Repository.NhanVienRepository;
import com.example.asmjava5.Service.NhanVienService;
import com.example.asmjava5.Service.ServiceImpl.NhanVienServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/api/nhan-vien")
public class NhanVienAPI {

    @Autowired
    private NhanVienService nvService;


    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    @GetMapping("")
    public List<NhanVien> getAllNhanVien() {
        return nvService.getALlNhanVien();
    }

    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    @GetMapping("/{manv}")
    public ResponseEntity<NhanVien> getOneNhanVien(@PathVariable("manv") String manv) {
        return Optional.ofNullable(nvService.findByMaNV(manv))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<NhanVien> addNhanVien(@RequestBody NhanVien nhanVien) {
        nvService.addNhanVien(nhanVien);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    @PutMapping("/update/{maNV}")
    public ResponseEntity<NhanVien> updateNhanVien(@PathVariable("maNV") String maNV, @RequestBody NhanVien nhanVien) {
        NhanVien updatedNhanVien = nvService.updateNhanVien(maNV, nhanVien);
        if (updatedNhanVien != null) {
            return ResponseEntity.ok(updatedNhanVien);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    @PutMapping("/delete/{maNV}")
    public ResponseEntity<?> deleteNhanVien(@PathVariable("maNV") String maNV) {
        try {
            nvService.deleteNhanVien(maNV);
            System.out.println("Delete Nhan Vien:"+maNV);
            return ResponseEntity.ok("Xóa thành công");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Đã xảy ra lỗi khi xóa nhân viên");
        }
    }

    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    @GetMapping("/listMaNV")
    public List<String> listMaNV() {
        List<NhanVien> nv = nvService.getALlNhanVien();
        List<String> manv = new ArrayList<>();
        for (NhanVien v : nv) {
            manv.add(v.getMaNV());
        }
        return manv;
    }
    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    @GetMapping("/isActive")
    public List<NhanVien> getAllNhanVienIsActive() {
        return nvService.getALlNhanVienIsActive();
    }
}