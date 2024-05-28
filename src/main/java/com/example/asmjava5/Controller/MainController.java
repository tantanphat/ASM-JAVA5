package com.example.asmjava5.Controller;

import com.example.asmjava5.Entity.DanhMucSP;
import com.example.asmjava5.Entity.SanPham;
import com.example.asmjava5.Service.ServiceImpl.DanhMucSPServceImpl;
import com.example.asmjava5.Service.ServiceImpl.KhachHangServiceImpl;
import com.example.asmjava5.Service.ServiceImpl.SanPhamServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {


    @Autowired
    HttpServletRequest req;

    @Autowired
    HttpServletResponse resp;

    @Autowired
    private KhachHangServiceImpl khachHangServiceImpl;

    @Autowired
    private SanPhamServiceImpl sanPhamServiceImpl;

    @Autowired
    private DanhMucSPServceImpl danhMucSPServceImpl;


    @GetMapping("/Trang-chu")
    public String hienThiAllSP(Model model) {
        List<SanPham> danhSachSanPham = sanPhamServiceImpl.getAllSanPham();
        model.addAttribute("danhSachSanPham", danhSachSanPham);

        List<DanhMucSP> dmsp = danhMucSPServceImpl.findAllDMSP();
        model.addAttribute("danhMucSP", dmsp);
        return "views/index";
    }

    @GetMapping("/Gioi-thieu")
    public String hienThiGioiThieu () {
        return "views/introduce";
    }
    @GetMapping("/Lien-he")
    public String hienThiLienHe() { return "views/contact";}
    @GetMapping("/demo")
    public ResponseEntity<?> demoApi () {
        SanPham kh = sanPhamServiceImpl.getSanPhamById("SP001");
        return ResponseEntity.ok(kh);
    }

    @GetMapping("/views/product")
    public String productDel() {
        return "views/productDeltails";
    }




}

