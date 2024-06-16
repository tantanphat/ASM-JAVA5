package com.example.asmjava5.Controller;

import com.example.asmjava5.Constant.SessionAttr;
import com.example.asmjava5.Entity.DanhMucSP;
import com.example.asmjava5.Entity.KhachHang;
import com.example.asmjava5.Entity.SanPham;

import com.example.asmjava5.Service.KhachHangService;
import com.example.asmjava5.Service.MailService;
import com.example.asmjava5.Service.NhanVienService;
import com.example.asmjava5.Service.SanPhamService;
import com.example.asmjava5.Service.ServiceImpl.DanhMucSPServceImpl;
import com.example.asmjava5.Service.ServiceImpl.KhachHangServiceImpl;
import com.example.asmjava5.Service.ServiceImpl.SanPhamServiceImpl;
import com.example.asmjava5.Utils.ExcelKhachHangUtils;
import com.example.asmjava5.Utils.SessionUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {
    private ExcelKhachHangUtils excel;
    @Autowired
    HttpServletRequest req;
    @Autowired
    HttpServletResponse resp;

    SessionUtils sessionUtils;
    @Autowired
    private SanPhamService sanPhamServiceImpl;
    @Autowired
    private DanhMucSPServceImpl danhMucSPServceImpl;

    @GetMapping("/Trang-chu")
    public String hienThiAllSP(Model model) {
        List<SanPham> danhSachSanPham = sanPhamServiceImpl.getAllSanPham();
        model.addAttribute("danhSachSanPham", danhSachSanPham);

        List<DanhMucSP> dmsp = danhMucSPServceImpl.findAllDMSP();
        model.addAttribute("danhMucSP", dmsp);

        List<SanPham> ao = sanPhamServiceImpl.findByMaDM(4);
        model.addAttribute("ao", ao);

        List<SanPham> quan = sanPhamServiceImpl.findByMaDM(3);
        model.addAttribute("quan", quan );

        List<SanPham>non = sanPhamServiceImpl.findByMaDM(6);
        model.addAttribute("non", non);

//        // Kiểm tra xem người dùng đã đăng nhập thành công chưa
//        if (principal == null) {
//            return "redirect:/Dang-nhap"; // Chuyển hướng đến trang đăng nhập nếu chưa đăng nhập
//        }
//        // Lấy tên người dùng từ Principal
//        String userName = principal.getName();
//        System.out.println("User Name: " + userName);
//        KhachHangDetails loginedUser = (KhachHangDetails) ((Authentication) principal).getPrincipal();
//        System.out.println("Hello" +loginedUser.getUsername());
//        SecurityContext context = SecurityContextHolder.getContext();
//        Authentication authentication = context.getAuthentication();
//        if (authentication != null && authentication.isAuthenticated()) {
//            String username = authentication.getName();
//            model.addAttribute("CURRENT_USER",username);
//        }
//        System.out.println("Hello "+sessionUtils.laySession(SessionAttr.CURRENT_USER));
        return "views/index";
    }

    @GetMapping("/Danh-muc-san-pham")
    public String hienThiSanPham (Model model) {
        List<SanPham> danhSachSanPham = sanPhamServiceImpl.getAllSanPham();
        model.addAttribute("danhSachSanPham", danhSachSanPham);
        List<DanhMucSP> dmsp = danhMucSPServceImpl.findAllDMSP();
        model.addAttribute("danhMucSP", dmsp);
        return "views/listCategory";
    }


    @GetMapping("/Gioi-thieu")
    public String hienThiGioiThieu () {
        return "views/introduce";
    }

    @GetMapping("/views/product")
    public String productDel() {
        return "views/productDeltails";
    }


    @GetMapping("/error")
    String error() {
        return "views/demo/error";
    }

    @GetMapping("/Lien-he")
    public String hienThiLienHe() {
        return "views/contact";
    }

    @GetMapping("/Search")
    public String hienThiTrangTimKiemSP(@RequestParam(value = "key",required = false) String key) {
        return "views/TimKiemSP";
    }

}

