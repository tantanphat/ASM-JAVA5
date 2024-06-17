package com.example.asmjava5.API;

import com.example.asmjava5.Constant.MailConstant;
import com.example.asmjava5.Entity.HoaDon;
import com.example.asmjava5.Entity.KhachHang;
import com.example.asmjava5.Model.mapper.KhachHangMapper;
import com.example.asmjava5.Model.request.ChangePassword;
import com.example.asmjava5.Model.request.DangKyKhachHang;
import com.example.asmjava5.Model.request.KhachHangThongTin;
import com.example.asmjava5.Repository.KhachHangRepository;
import com.example.asmjava5.Service.HoaDonService;
import com.example.asmjava5.Service.KhachHangService;
import com.example.asmjava5.Service.LichSuMuaHangService;
import com.example.asmjava5.Service.MailService;
import com.example.asmjava5.Utils.ExcelKhachHangUtils;
import com.example.asmjava5.Utils.SendMa;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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
    private LichSuMuaHangService muaHangService;
    @Autowired
            private MailService emailService;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

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
    public ResponseEntity<?> getAllKhachHang(){
//        HashMap<String,Object> map = new HashMap<String,Object>();
//        map.put("data",khachHangServiceImpl.getAllKhachHang());
        return ResponseEntity.ok(khachHangServiceImpl.getAllKhachHang());
    }

    @GetMapping("/demoKH")
    public ResponseEntity<?> demoKH(){
        return ResponseEntity.ok(khachHangServiceImpl.getAllKhachHang());
    }

    @GetMapping("/{maKH}")
    public ResponseEntity<KhachHang> getOneNhanVien(@PathVariable("maKH") String maKH) {
        return Optional.ofNullable(khachHangServiceImpl.findBymaKH(maKH))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
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

    //Đăng ký khách hàng
    @PostMapping("/add-khach-hang")
    public ResponseEntity<?> addKhachHang(@RequestBody DangKyKhachHang dangKyKhachHang) {
        try {
            emailService.sendMail(MailConstant.KEY_MAIL_SIGNIN_WELCOME,dangKyKhachHang.getEmail());//Gửi mail khi khách hàng đăng ký thành công
            khachHangServiceImpl.dangKyKhachHangMoi(dangKyKhachHang);//Xử lý thông tin và thêm khách hàng
            return ResponseEntity.ok(HttpStatus.OK);//Trả về 200 khi thành công
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());//Trả về 401 khi có ngoại lệ
        }
    }

    @GetMapping("/xuat-ra-excel")
    public ResponseEntity<?> xuatListKhachHangRaExcel() {
        List<KhachHang> kh = khachHangServiceImpl.getAllKhachHang();
        String excelFilePath = "D:/Khách Hàng.xlsx";
        try {
            ExcelKhachHangUtils.writeExcel(kh,excelFilePath);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
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

    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePassword changePassword, Principal principal) {
        String userName = principal.getName();
        KhachHang kh = khachHangServiceImpl.getLoginByEmail(userName);


        changePassword.setMaKH(kh.getMaKH());

        // So sánh mật khẩu cũ của người dùng với mật khẩu đã được mã hóa trong cơ sở dữ liệu
        if (!encoder.matches(changePassword.getMatKhauCu(), kh.getMatKhau())) {
            return ResponseEntity.badRequest().body("Mật khẩu cũ không đúng.");
        } else {
            khachHangServiceImpl.changePassword(changePassword);
            return ResponseEntity.ok(HttpStatus.OK);
        }
    }

    //Lấy email và gửi mã xác nhận cho khách hàng
    @GetMapping("/mail-for-forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam("email") String email) {
        //Lấy email mà khách hàng quên mật khẩu
        KhachHang kh = khachHangServiceImpl.getLoginByEmail(email);
        //Nếu email có thì bắt đầu gửi mã xác nhận
        if (kh!= null) {
            //Lấy mã xác nhận ngẫu nhiên
            String ma = SendMa.MaXacNhan();
            //Lưu mã xác nhận vào session
            session.setAttribute("MaXacNhan",ma);
            //Gửi mã xác nhận về email
            emailService.sendMailCode(MailConstant.CODE_FORGOT_PASSWORD,email,ma);
            return ResponseEntity.ok(HttpStatus.OK);
        } else {
            return ResponseEntity.badRequest().body("Email không tồn tại.");
        }
    }

    //Xác nhận mã xác nhận của khách hàng
    @GetMapping("/conform-code-password")
    public ResponseEntity<?> conformCodePassword() {
        //Lâý mã xác nhận từ session
        String maXN = (String)session.getAttribute("MaXacNhan");
        //Trả mã xác nhận để xác thực
        return ResponseEntity.ok(maXN);
    }

    //Sau khi xác nhận thì nhập mật khẩu mới và tiến hành đổi mật khẩu
    @PutMapping("/forgor-password-new-password")
    public ResponseEntity<?> forgorPasswordNewPassword(@RequestParam("email")String email,@RequestParam("pass") String pass) {
        try {
            //Xác nhận và đổi mật khẩu thành công
            khachHangServiceImpl.forgotPassword(email, pass);
            return ResponseEntity.ok("Đổi mật khẩu thành công");
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Đã có lỗi xảy ra");
        }
    }
    @GetMapping("/San-pham-hoa-don")
    public ResponseEntity<?> hienThiLichSuMuaHang(@RequestParam("MaDH") String MaDH) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        return Optional.ofNullable(muaHangService.getAllItemsSP(MaDH,email))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
