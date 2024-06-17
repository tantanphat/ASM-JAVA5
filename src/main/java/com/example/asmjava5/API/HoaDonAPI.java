package com.example.asmjava5.API;

import com.example.asmjava5.Constant.MailConstant;
import com.example.asmjava5.Constant.SessionAttr;
import com.example.asmjava5.Entity.HoaDon;
import com.example.asmjava5.Entity.KhachHang;
import com.example.asmjava5.Entity.SanPham;
import com.example.asmjava5.Model.DTO.CartItemsDTO;
import com.example.asmjava5.Model.request.DangKyKhachHang;
import com.example.asmjava5.Model.request.LichSuMuaHang;
import com.example.asmjava5.Model.request.TaoHoaDon;
import com.example.asmjava5.Service.*;
import com.example.asmjava5.Utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.security.Principal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/hoa-don")
public class HoaDonAPI {
    @Autowired
    private HoaDonService hoaDonService;
    @Autowired
    private KhachHangService khachHangService;
    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;
    @Autowired
    private MailService mailService;
    @Autowired
    private SessionUtils session;
    @Autowired
    private SessionUtils sessionUtils;


    BCryptPasswordEncoder encoder =  new BCryptPasswordEncoder();


    @GetMapping("")
    public List<HoaDon> getAllHoadon() {
        return hoaDonService.getAllHoaDon();
    }

    //Đổ hóa đơn theo mã hóa đơn
    @GetMapping("/{hd_MaHDBan}")
    public HoaDon getOneHoaDon(@PathVariable("hd_MaHDBan") String MaHDBan) {
        return hoaDonService.getHoaDonByID(MaHDBan);
    }

    //Chức năng thêm hóa đơn
    @PostMapping("/add")
    public ResponseEntity<HoaDon> addHoaDon(@RequestBody HoaDon hoaDon) {
        HoaDon newHoaDon = hoaDonService.addHoaDon(hoaDon);
        return ResponseEntity.ok(newHoaDon);
    }

    //Chức năng xóa hóa đơn
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteHoaDon(@RequestParam("mahd") String mahd) {
        try {
            hoaDonService.deleteHoaDon(mahd);
            return ResponseEntity.ok("Xóa hóa đơn thành công");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đã xảy ra lỗi khi xóa hóa đơn");
        }
    }

    //Chức năng tạo hóa đơn
    @PostMapping("/createHD")
    public ResponseEntity<?> createHD(@RequestBody TaoHoaDon hoaDon) {
        try {
            String makh = khachHangService.AUTO_MAKH();
            //Nếu là khách hàng mới thì đăng ký khách hàng mới với hóa đơn
            if (hoaDon.getMaKH().equalsIgnoreCase("")) {
                DangKyKhachHang kha = new DangKyKhachHang();
                kha.setTenKH(hoaDon.getTenKH());
                kha.setDiaChi(hoaDon.getDiaChi());
                kha.setSdt(hoaDon.getSdt());
                kha.setEmail(hoaDon.getEmail());
                kha.setMatKhau(encoder.encode(hoaDon.getSdt()));
                kha.setThanhVien(false);
                khachHangService.dangKyKhachHangMoi(kha);
                hoaDonService.creatHD(hoaDon.getMaNV(),makh);
            }
            else {
                hoaDonService.creatHD(hoaDon.getMaNV(),hoaDon.getMaKH());
            }

            return ResponseEntity.ok(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đã xảy ra lỗi khi Thêm hóa đơn");
        }
    }

    //Chức năng update hóa đơn
    @PutMapping("/updateHD")
    public ResponseEntity<?> updateHoaDon(@RequestBody HoaDon hoaDon) {
        hoaDonService.updateHD(hoaDon);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/thanh-toan")
    @SuppressWarnings("unchecked")
    public ResponseEntity<?> thanhToan(String maHD, String mailKH) {
        //Lấy giỏ hàng từ session
        List<CartItemsDTO> cartItems = (List<CartItemsDTO>) sessionUtils.laySession(SessionAttr.SESSION_KEY_CART);

        //Lấy mã KH
        KhachHang kh = khachHangService.getLoginByEmail(mailKH);

        String maKH = kh.getMaKH();
        if (cartItems.isEmpty()) {
            System.out.println("Giỏ hàng đang trống");
        } else {
            System.out.println("Giỏ hàng gồm: " + cartItems);

            hoaDonService.ThanhToanHoaDon(maHD,"NV001", LocalDate.now(),maKH);

            // Sử dụng Map để lưu trữ số lượng của maSP
            Map<String, Integer> quantityMap = new HashMap<>();
            for (CartItemsDTO cart : cartItems) {
                String maSP = cart.getMaSP();
                int quantity = cart.getQuantity();
                // Kiểm tra maSP đã tồn tại trong Map hay chưa
                if (quantityMap.containsKey(maSP)) {
                    // Nếu tồn tại, cộng số lượng hiện tại vào
                    int currentQuantity = quantityMap.get(maSP);
                    quantityMap.put(maSP, currentQuantity + quantity);
                } else {
                    // Nếu chưa tồn tại, thêm mới vào Map
                    quantityMap.put(maSP, quantity);
                }
            }

            for (String key : quantityMap.keySet()) {
                System.out.println("SP: " + key + " Quantity: " + quantityMap.get(key));
                //Lưu sản phẩm vào trong hóa đơn chi tiết
                hoaDonChiTietService.saveHoaDonChiTiet(maHD,key,quantityMap.get(key),0.0);
                //Thanh toán xong thì xóa List trong giỏ hàng
                session.removeSession(SessionAttr.SESSION_KEY_CART);
            }
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/Gui-hoa-don-mail")
    public ResponseEntity<?> guiHoaDonMail(@RequestBody byte[] fileBytes, Principal principal) {
        String email = principal.getName();
        mailService.sendMailFile(MailConstant.FILE_MAIL_HOA_DON, "nguyen04tan04phat03@gmail.com", fileBytes);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
