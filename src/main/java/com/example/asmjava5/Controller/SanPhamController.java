package com.example.asmjava5.Controller;

import com.example.asmjava5.Entity.DanhMucSP;
import com.example.asmjava5.Entity.SanPham;
import com.example.asmjava5.Repository.DanhMucSPRepository;
import com.example.asmjava5.Repository.SanPhamRepository;
import com.example.asmjava5.Service.ServiceImpl.SanPhamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SanPhamController {
    @Autowired
    private SanPhamServiceImpl sanPhamServiceImpl;

    @Autowired
    private SanPhamRepository sanPhamRepository;

    @Autowired
    private DanhMucSPRepository danhMucSPRepository;

    @GetMapping("/product")
    public String hienThiSanPhamChiTiet() {
        return "views/productDeltails";
    }


    @GetMapping("/")
    public String index(Model model) {
        List<SanPham> products = sanPhamRepository.findAll();
        model.addAttribute("products", products);

        List<DanhMucSP> categories = danhMucSPRepository.findAll();
        model.addAttribute("categories", categories);

        return "index";
    }
    @GetMapping("/danh-muc/{maDM}")
    public ResponseEntity<List<SanPham>> getSanPhamByDanhMuc(@PathVariable int maDM) {
        List<SanPham> sanPhams = sanPhamServiceImpl.findAllByDanhMuc(maDM);
        return new ResponseEntity<>(sanPhams, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<SanPham>> getAllSanPham() {
        List<SanPham> sanPhams = sanPhamServiceImpl.findAll();
        return new ResponseEntity<>(sanPhams, HttpStatus.OK);
    }



}