package com.example.asmjava5.Controller;

import com.example.asmjava5.Entity.DanhMucSP;
import com.example.asmjava5.Entity.KhachHang;
import com.example.asmjava5.Entity.SanPham;
import com.example.asmjava5.Security.KhachHangDetails;
import com.example.asmjava5.Service.ServiceImpl.DanhMucSPServceImpl;
import com.example.asmjava5.Service.ServiceImpl.KhachHangServiceImpl;
import com.example.asmjava5.Service.ServiceImpl.SanPhamServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    HttpServletRequest req;

    @Autowired
    HttpServletResponse resp;

    @Autowired
    HttpSession session;

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


//        // Kiểm tra xem người dùng đã đăng nhập thành công chưa
//        if (principal == null) {
//            return "redirect:/Dang-nhap"; // Chuyển hướng đến trang đăng nhập nếu chưa đăng nhập
//        }
//
//        // Lấy tên người dùng từ Principal
//        String userName = principal.getName();
//        System.out.println("User Name: " + userName);
//
        // Lấy thông tin chi tiết của người dùng
//        KhachHangDetails loginedUser = (KhachHangDetails) ((Authentication) principal).getPrincipal();
//        System.out.println("Hello" +loginedUser.getUsername());

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            model.addAttribute("CURRENT_USER",username);
        }

        return "views/index";
    }

    @GetMapping("/Gioi-thieu")
    public String hienThiGioiThieu () {
        return "views/introduce";
    }

    @GetMapping("/demo")
    public ResponseEntity<?> demoApi () {
        SanPham kh = sanPhamServiceImpl.getSanPhamById("SP001");
        return ResponseEntity.ok(kh);
    }

    @GetMapping("/views/product")
    public String productDel() {
        return "views/productDeltails";
    }

    @GetMapping("/views/bill")
    public String Bill(){ return "views/Admin/Bill";}


}

