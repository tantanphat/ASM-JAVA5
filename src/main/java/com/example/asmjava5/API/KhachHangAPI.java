package com.example.asmjava5.API;

import com.example.asmjava5.Constant.MailConstant;
import com.example.asmjava5.Entity.KhachHang;
import com.example.asmjava5.Model.mapper.KhachHangMapper;
import com.example.asmjava5.Model.request.DangKyKhachHang;
import com.example.asmjava5.Model.request.KhachHangThongTin;
import com.example.asmjava5.Service.MailService;
import com.example.asmjava5.Service.ServiceImpl.KhachHangServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class KhachHangAPI {
    @Autowired
    HttpSession session;
    @Autowired
    HttpServletRequest req;
    @Autowired
    HttpServletResponse resp;
    @Autowired
    private MailService emailService;
    @Autowired
    private KhachHangServiceImpl khachHangServiceImpl;


    //Lấy thông tin khách hàng qua email
    @GetMapping("/user")
    public ResponseEntity<?> getInfoKhachHang(Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            KhachHang kh = khachHangServiceImpl.getLoginByEmail(username);
            return ResponseEntity.ok(KhachHangMapper.khMapper(kh));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    //Update thông tin khách hàng
    @PostMapping("/updateKhachHang")
    public ResponseEntity<?> updateKhachHang(@RequestBody KhachHangThongTin khachHangThongTin) {
        KhachHang khh = khachHangServiceImpl.updateInfo(khachHangThongTin);
        return ResponseEntity.ok(KhachHangMapper.khMapper(khh));

    }

    @GetMapping("/list-khach-hang")
    public List<KhachHang> getAllKhachHang(){
        return khachHangServiceImpl.getAllKhachHang();
    }

    @GetMapping("/check-khach-hang")
    public Boolean getMailKH(String mail){
        KhachHang kh = khachHangServiceImpl.getLoginByEmail(mail);
        if (kh != null) {
            return true;
        }
        return false;
    }

    @PostMapping("/add-khach-hang")
    public ResponseEntity<?>  addKhachHang(@RequestBody DangKyKhachHang dangKyKhachHang) {
//        emailService.sendMail(MailConstant.KEY_MAIL_SIGNIN_WELCOME,dangKyKhachHang.getEmail());
        khachHangServiceImpl.dangKyKhachHangMoi(dangKyKhachHang);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
