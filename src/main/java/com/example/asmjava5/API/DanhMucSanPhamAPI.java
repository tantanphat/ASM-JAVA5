package com.example.asmjava5.API;

import com.example.asmjava5.Entity.DanhMucSP;
import com.example.asmjava5.Service.DanhMucSPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
