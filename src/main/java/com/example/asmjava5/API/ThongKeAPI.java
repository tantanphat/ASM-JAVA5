package com.example.asmjava5.API;


import com.example.asmjava5.Entity.HoaDonChiTiet;
import com.example.asmjava5.Entity.SanPham;
import com.example.asmjava5.Service.ThongKeService;
import com.example.asmjava5.Utils.ExcelBaoCaoDoanhThuUtils;
import com.example.asmjava5.Utils.ExcelBaoCaoSanPhamUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/thong-ke/")
public class ThongKeAPI {
    @Autowired
    ExcelBaoCaoDoanhThuUtils excelBaoCaoDoanhThuUtils;
    @Autowired
    ExcelBaoCaoSanPhamUtils excelBaoCaoSanPhamUtils;

    @Autowired
     private ThongKeService thongKeService;

    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    @GetMapping("nhan-vien-chi-tiet/{thang}")
    public List<HoaDonChiTiet> thongKeNhanVienChiTietThang(@PathVariable int thang) {
        return thongKeService.thongKeNhanVienChiTietThang(thang);
    }

    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    @GetMapping("nhan-vien-co-don/{thang}")
    public List<HoaDonChiTiet> thongKeNhanVienCoDonThang(@PathVariable int thang) {
        return thongKeService.thongKeNhanVienCoDonThang(thang);
    }

    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    @GetMapping("doanh-thu/{thang}")
    public List<HoaDonChiTiet> thongKeDoanhThu(@PathVariable int thang) {
        return thongKeService.thongKeDoanhThu(thang);
    }

    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    @GetMapping("san-pham-ban/{thang}")
    public List<HoaDonChiTiet> thongKeSanPhamBan(@PathVariable int thang) {
        return thongKeService.thongKeSanPhamBan(thang);
    }

    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    @GetMapping("san-pham-ban-duoc/{thang}")
    public List<Object[]> thongKeSanPhamBanDuoc(@PathVariable int thang) {
        return thongKeService.thongKeSanPhamBanDuoc(thang);
    }

    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    @GetMapping("san-pham-khong-ban/{thang}")
    public List<Object[]> thongKeSanPhamKBanDuoc(@PathVariable int thang) {
        return thongKeService.thongKeSanPhamKBanDuoc(thang);
    }


    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    @GetMapping("doanh-thu-thang-theo-nam")
    public List<Object[]> thongKeSanPhamKBanDuoc(@RequestParam("month") int month , @RequestParam(name = "year",defaultValue = "2021") int year) throws IOException {;
        return thongKeService.thongKeDoanhThuThangTheoNam(month, year);
    }

    //Xuất thống kê doanh thu trong năm theo từng tháng
    @GetMapping("excel-thong-ke")
    public ResponseEntity<?> xuatThoongKe(@RequestParam(name = "year") int year) throws IOException {
        try {
            excelBaoCaoDoanhThuUtils.export(year,"D:/DoanhThu.xlsx");
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (IOException  e) {
            return ResponseEntity.badRequest().body("Tệp đang được sử dụng vui lòng tắt để xuất ra file");
        }
    }

    //Xuất ra sản phẩm đã được bán và không bán được theo tháng
    @GetMapping("excel-thong-ke-san-pham")
    public ResponseEntity<?> xuatExcelSanPham(@RequestParam(name = "month") int month) throws IOException {
        try {
            excelBaoCaoSanPhamUtils.export(month,"D:/Sản Phẩm.xlsx");
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (IOException  e) {
            return ResponseEntity.badRequest().body("Tệp đang được sử dụng vui lòng tắt để xuất ra file");
        }
    }
}
