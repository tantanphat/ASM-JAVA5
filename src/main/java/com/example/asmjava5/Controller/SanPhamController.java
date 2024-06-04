package com.example.asmjava5.Controller;

import com.example.asmjava5.Entity.SanPham;
import com.example.asmjava5.Service.ServiceImpl.SanPhamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SanPhamController {
    @Autowired
    private SanPhamServiceImpl sanPhamServiceImpl;

    @GetMapping("/product")
    public String hienThiSanPhamChiTiet(@RequestParam("maSP") String maSP, Model model) {
        SanPham sp = sanPhamServiceImpl.getSanPhamById(maSP);
        model.addAttribute("spDeltail",sp);
        return "views/productDeltails";
    }
    @GetMapping("/search")
    public String timKiemSanPham(@RequestParam("key") String key, Model model) {
        List<SanPham> sanPhams = sanPhamServiceImpl.timKiemSanPham(key);
        model.addAttribute("sanPhams", sanPhams);
        return "views/searchResults";
    }

}
