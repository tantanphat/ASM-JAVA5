package com.example.asmjava5.API;

import com.example.asmjava5.Constant.SessionAttr;
import com.example.asmjava5.Entity.HoaDon;
import com.example.asmjava5.Entity.KhachHang;
import com.example.asmjava5.Entity.SanPham;
import com.example.asmjava5.Model.DTO.CartItemsDTO;
import com.example.asmjava5.Model.request.DangKyKhachHang;
import com.example.asmjava5.Model.request.TaoHoaDon;
import com.example.asmjava5.Service.HoaDonChiTietService;
import com.example.asmjava5.Service.HoaDonService;
import com.example.asmjava5.Service.KhachHangService;
import com.example.asmjava5.Service.ServiceImpl.SanPhamServiceImpl;
import com.example.asmjava5.Utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private SessionUtils session;
    @Autowired
    private SessionUtils sessionUtils;
    @Autowired
    private SanPhamServiceImpl sanPhamServiceImpl;

    BCryptPasswordEncoder encoder =  new BCryptPasswordEncoder();


    @GetMapping("")
    public List<HoaDon> getAllHoadon() {
        return hoaDonService.getAllHoaDon();
    }

    @GetMapping("/{hd_MaHDBan}")
    public HoaDon getOneHoaDon(@PathVariable("hd_MaHDBan") String MaHDBan) {
        return hoaDonService.getHoaDonByID(MaHDBan);
    }



    @PostMapping("/add")
    public ResponseEntity<HoaDon> addHoaDon(@RequestBody HoaDon hoaDon) {
        HoaDon newHoaDon = hoaDonService.addHoaDon(hoaDon);
        return ResponseEntity.ok(newHoaDon);
    }

    Map<String,SanPham> map =new HashMap<>();

    @PostMapping("/thanh-toan")
    @SuppressWarnings("unchecked")
    public ResponseEntity<?> thanhToan(String maHD, String mailKH) {
        List<CartItemsDTO> cartItems = (List<CartItemsDTO>) sessionUtils.laySession(SessionAttr.SESSION_KEY_CART);
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
                hoaDonChiTietService.saveHoaDonChiTiet(maHD,key,quantityMap.get(key),0.0);
                session.removeSession(SessionAttr.SESSION_KEY_CART);
            }
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }

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

    @PostMapping("/createHD")
    public ResponseEntity<?> createHD(@RequestBody TaoHoaDon hoaDon) {
            try {
                String makh = khachHangService.AUTO_MAKH();
//                KhachHang khachHangIsExist = khachHangService.findBymaKH(hoaDon.getMaKH());
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
                } else {
                    hoaDonService.creatHD(hoaDon.getMaNV(),hoaDon.getMaKH());
                }

                return ResponseEntity.ok(HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đã xảy ra lỗi khi Thêm hóa đơn");
            }
        }
}
