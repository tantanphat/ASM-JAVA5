package com.example.asmjava5.API;

import com.example.asmjava5.Entity.HoaDonChiTiet;
import com.example.asmjava5.Model.request.ThemHDCT;
import com.example.asmjava5.Service.HoaDonChiTietService;
import com.example.asmjava5.Service.HoaDonService;
import com.example.asmjava5.Service.ServiceImpl.HoaDonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hoa-don-chi-tiet")
public class HoaDonChiTietAPI {
    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;

    @GetMapping("")
    public List<HoaDonChiTiet> getHoadonchitiet() { return hoaDonChiTietService.getAllHoaDonChiTiet();}

    @GetMapping("/{mahdct}")
    public HoaDonChiTiet getHoadonchitiet(@PathVariable int mahdct) {
        return hoaDonChiTietService.getHoaDonChiTietById(mahdct);
    }

    @GetMapping("/mahd/{maHDBan}")
    public List<HoaDonChiTiet> getAllHDCTByMaHDBan(@PathVariable("maHDBan") String maHDBan) {
        return hoaDonChiTietService.getAllHDCTByMaHD(maHDBan);
    }

    @PostMapping("/add-hdct")
    public ResponseEntity<?> addHoaDonChiTiet(@RequestBody ThemHDCT hoaDonChiTiet) {
        hoaDonChiTietService.themHDCTByStaff(hoaDonChiTiet);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/update-hdct")
    public ResponseEntity<?> updateHDCT(@RequestBody HoaDonChiTiet hdct) {
        try {
            hoaDonChiTietService.updateHDCT(hdct);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đã xảy ra lỗi khi cập nhật hóa đơn chi tiết");
        }
    }

    @DeleteMapping("/delete-hdct")
    public ResponseEntity<?> deleteHDCT(@RequestParam("mahdct") int mahdct) {
        hoaDonChiTietService.deleteHDCT(mahdct);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
