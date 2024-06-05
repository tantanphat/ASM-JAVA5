package com.example.asmjava5.API;

import com.example.asmjava5.Entity.DanhMucSP;
import com.example.asmjava5.Entity.SanPham;
import com.example.asmjava5.Service.DanhMucSPService;
import com.example.asmjava5.Service.SanPhamService;
import jakarta.servlet.annotation.MultipartConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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
    public List<SanPham> getAllSanPham() {return sanPhamService.getAllSanPham();}
//    @GetMapping("/masp/{masp}")
//    public SanPham getOneSanPham(@PathVariable("masp") String masp) {
//        return sanPhamService.getSanPhamById(masp);
//    }
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
    public void uploadAnhSanPham(@RequestParam("file") MultipartFile file) throws IOException {
        StringBuilder fileNames = new StringBuilder();
        String nameImg = file.getOriginalFilename();
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, nameImg);
        fileNames.append(file.getOriginalFilename());
        Files.write(fileNameAndPath, file.getBytes());
        System.out.println("Đã upload thành công");
    }

    @PostMapping("/updateSP")
    public void updateSP(@RequestBody SanPham sp,@RequestParam("file") MultipartFile file) throws IOException{
        SanPham up = sanPhamService.getSanPhamById(sp.getMaSP());

        if (file.isEmpty()) {
            System.out.println("Chưa chọn file");
            up.setSize(sp.getSize());
            up.setGhiChu(sp.getGhiChu());
            up.setMaDM(sp.getMaDM());
            up.setAnh(up.getAnh());
            up.setGiaBan(sp.getGiaBan());
            up.setTenSP(sp.getTenSP());
            sanPhamService.updateSP(up);
        } else{
            StringBuilder fileNames = new StringBuilder();
            String nameImg = file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, nameImg);
            fileNames.append(file.getOriginalFilename());
            Files.write(fileNameAndPath, file.getBytes());

            up.setSize(sp.getSize());
            up.setGhiChu(sp.getGhiChu());
            up.setMaDM(sp.getMaDM());
            up.setAnh(nameImg);
            up.setGiaBan(sp.getGiaBan());
            up.setTenSP(sp.getTenSP());

        }
        sanPhamService.updateSP(up);
    }

    @PostMapping("/adSP")
    public void adSP(@RequestBody SanPham sp){
        sp.setMaSP(sanPhamService.AUTO_MASP());
        sanPhamService.addSP(sp);
    }

    @DeleteMapping("/deleteSP")
    public void deleteSP(@RequestParam("mssp") String masp){
        sanPhamService.deleteSP(masp);
    }
}
