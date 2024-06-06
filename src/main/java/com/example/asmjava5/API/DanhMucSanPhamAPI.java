package com.example.asmjava5.API;

import com.example.asmjava5.Entity.DanhMucSP;
import com.example.asmjava5.Service.DanhMucSPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/danh-muc-san-pham")
public class DanhMucSanPhamAPI {

    @Autowired
    private DanhMucSPService danhMucSanPhamService;

    @GetMapping("")
    public List<DanhMucSP> getListDanhMucSanPham() {
    return danhMucSanPhamService.findAllDMSP();
    }

    @GetMapping("/madm")
    public DanhMucSP getListDanhMucSanPham(@RequestParam("madm") int madm) {
        return danhMucSanPhamService.findDMSPByID(madm);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createDMSP(@RequestBody DanhMucSP danhMucSP) {
        danhMucSanPhamService.createDMSP(danhMucSP);
        return  ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateDMSP(@RequestBody DanhMucSP danhMucSP) {
        danhMucSanPhamService.updateDMSP(danhMucSP);
        return  ResponseEntity.ok(HttpStatus.OK);
    }
}
