package com.example.asmjava5.API;

import com.example.asmjava5.Entity.KhachHang;
import com.example.asmjava5.Model.mapper.KhachHangMapper;
import com.example.asmjava5.Model.request.DangKyKhachHang;
import com.example.asmjava5.Model.request.KhachHangThongTin;
import com.example.asmjava5.Repository.KhachHangRepository;
import com.example.asmjava5.Service.KhachHangService;
import com.example.asmjava5.Utils.ExcelKhachHangUtils;
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
@RequestMapping("/api/khach-hang")
public class KhachHangAPI {
    @Autowired
    HttpSession session;
    @Autowired
    HttpServletRequest req;
    @Autowired
    HttpServletResponse resp;
    @Autowired
    private KhachHangService khachHangServiceImpl;
    @Autowired
    private KhachHangRepository khachHangRepository;

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
    public ResponseEntity<?> updateKhachHang(@RequestBody KhachHangThongTin khachHangDto) {
        KhachHang khh = khachHangServiceImpl.updateInfo(khachHangDto);
        return ResponseEntity.ok(KhachHangMapper.khMapper(khh));

    }

    @GetMapping("")
    public List<KhachHang> getAllKhachHang(){
        return khachHangServiceImpl.getAllKhachHang();
    }

    @GetMapping("/demoKH")
    public ResponseEntity<?> demoKH(){
        return ResponseEntity.ok(khachHangServiceImpl.getAllKhachHang());
    }

    @GetMapping("/{maKH}")
    public KhachHang getOneNhanVien(@PathVariable("maKH") String maKH) {
        return khachHangServiceImpl.findBymaKH(maKH);
    }

    @PostMapping("/add")
    public ResponseEntity<KhachHang> addKhachHang(@RequestBody KhachHang khachHang) {
        KhachHang newKhachHang = khachHangServiceImpl.addKhachHang(khachHang);
        return ResponseEntity.ok(newKhachHang);
    }

    @PutMapping("/{maKH}")
    public ResponseEntity<KhachHang> updateKhachHang(@PathVariable("maKH") String maKH, @RequestBody KhachHang khachHang) {
        KhachHang updateKhachHang = khachHangServiceImpl.updateKhachHang(maKH, khachHang);
        if (updateKhachHang != null) {
            return ResponseEntity.ok(updateKhachHang);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{maKH}")
    public ResponseEntity<Void> deleteKhachHang(@PathVariable("maKH") String maKH) {
        khachHangServiceImpl.deleteKhachHang(maKH);
        return ResponseEntity.noContent().build();
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
    public ResponseEntity<?> addKhachHang(@RequestBody DangKyKhachHang dangKyKhachHang) {
//        emailService.sendMail(MailConstant.KEY_MAIL_SIGNIN_WELCOME,dangKyKhachHang.getEmail());
        khachHangServiceImpl.dangKyKhachHangMoi(dangKyKhachHang);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @GetMapping("/xuat-ra-excel")
    public void xuatListKhachHangRaExcel() {
        List<KhachHang> kh = khachHangServiceImpl.getAllKhachHang();
        String excelFilePath = "E:/Khachhang.xlsx";
        try {
            ExcelKhachHangUtils.writeExcel(kh,excelFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/tim-kiem")
    public KhachHang timKiemKH(@RequestParam("key") String  key) {
        return khachHangServiceImpl.timKiemKH(key);
    }

    @GetMapping("/findBySDT")
    public KhachHang findBySDT(@RequestParam("sdt") String  sdt) {
        return khachHangServiceImpl.findBySDY(sdt);
    }
}
