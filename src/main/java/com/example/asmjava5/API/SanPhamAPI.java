package com.example.asmjava5.API;

import com.example.asmjava5.Entity.DanhMucSP;
import com.example.asmjava5.Entity.SanPham;
import com.example.asmjava5.Service.DanhMucSPService;
import com.example.asmjava5.Service.SanPhamService;
import com.example.asmjava5.Utils.ImageUploadUtils;
import jakarta.servlet.annotation.MultipartConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@MultipartConfig
@RequestMapping("/api/san-pham")
public class SanPhamAPI {
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/assets/sanpham";
    @Autowired
    private SanPhamService sanPhamService;
    @Autowired
    private DanhMucSPService danhMucSPService;

    @GetMapping("")
    public List<SanPham> getAllSanPham(@RequestParam(defaultValue = "0") Integer pageNo,
                                       @RequestParam(defaultValue = "5") Integer pageSize,
                                       @RequestParam(defaultValue = "maSP") String sortBy,
                                       @RequestParam(defaultValue = "") String sortOrder) {
        return sanPhamService.getAllSP(pageNo, pageSize, sortBy, sortOrder);
    }

    @GetMapping("/lengthSP")
    public int getTotalSanPhamCount() {
        return sanPhamService.getAllSanPham().size();
    }

    @GetMapping("/c")
    public SanPham getSanPham(@RequestParam("maSP") String maSP) {
    return sanPhamService.getSanPhamById(maSP);
    }

    @GetMapping("/masp/{masp}")
    public SanPham getOneSanPham(@PathVariable("masp") String masp) {
        return sanPhamService.getSanPhamById(masp);
    }


    @GetMapping("/{keyword}")
    public List<SanPham> timKiemSanPham(@PathVariable String keyword) {
        return sanPhamService.timKiemSanPham(keyword);
    }

    @GetMapping("/danh-muc-sp")
    public List<DanhMucSP> getSanPhamByDanhMuc() {
        return danhMucSPService.findAllDMSP();
    }


    @PostMapping(value = "/upload-anh-sp", consumes = "multipart/form-data")
    public ResponseEntity<String> uploadAnhSanPham(@RequestParam("file") MultipartFile file) {
        try {
            // Lưu ảnh vào thư mục UPLOAD_DIRECTORY
//            File newImg = new File(UPLOAD_DIRECTORY, file.getOriginalFilename());
//            file.transferTo(newImg);

            ImageUploadUtils imageUploadUtils = new ImageUploadUtils();
            imageUploadUtils.uploadImage(file);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file: " + e.getMessage());
        }
    }

    @PutMapping("/updateSP")
    public ResponseEntity<?> updateSP(@RequestBody SanPham sp){
        SanPham up = sanPhamService.getSanPhamById(sp.getMaSP());
        up.setSize(sp.getSize());
        up.setGhiChu(sp.getGhiChu());
        up.setMaDM(sp.getMaDM());
        up.setAnh(sp.getAnh());
        up.setGiaBan(sp.getGiaBan());
        up.setTenSP(sp.getTenSP());
        sanPhamService.updateSP(up);
        return ResponseEntity.ok("Cập nhập thành công");
    }

    @PostMapping("/adSP")
    public ResponseEntity<?> adSP(@RequestBody SanPham sp){
        sanPhamService.addSP(sp);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/deleteSP")
    public void deleteSP(@RequestParam("mssp") String masp) {
        sanPhamService.deleteSP(masp);
    }

    @GetMapping("/timkiems")
    public SanPham layTenSP(@RequestParam("mssp") String mssp) {
        return sanPhamService.getSanPhamById(mssp);
    }

    @GetMapping("/danh-muc-sp/{maDM}")
    public ResponseEntity<List<SanPham>> getSanPhamByDanhMuc(@PathVariable int maDM) {
        List<SanPham> sanPhams;
        switch (maDM) {
            case 1:
                sanPhams = sanPhamService.findByMaDM(1);
                break;
            case 2:
                sanPhams = sanPhamService.findByMaDM(2);
                break;
            case 3:
                sanPhams = sanPhamService.findByMaDM(3);
                break;
            case 4:
                sanPhams = sanPhamService.findByMaDM(4);
                break;
            case 5:
                sanPhams = sanPhamService.findByMaDM(5);
                break;
            case 6:
                sanPhams = sanPhamService.findByMaDM(6);
                break;
            default:
                sanPhams = sanPhamService.getAllSanPham(); // Tìm kiếm tất cả sản phẩm
                break;
        }
        return new ResponseEntity<>(sanPhams, HttpStatus.OK);
    }

}


