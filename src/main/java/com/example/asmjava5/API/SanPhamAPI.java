package com.example.asmjava5.API;

import com.example.asmjava5.Entity.SanPham;
import com.example.asmjava5.Service.SanPhamService;
import com.example.asmjava5.Service.ServiceImpl.SanPhamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/san-pham")
public class SanPhamAPI {

    @Autowired
    private SanPhamService sanPhamService;

    @GetMapping("/{keyword}")
    public List<SanPham> timKiemSanPham(@PathVariable String keyword) {
        return sanPhamService.timKiemSanPham(keyword);
    }
}
