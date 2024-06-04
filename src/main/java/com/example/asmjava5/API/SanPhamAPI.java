package com.example.asmjava5.API;

import com.example.asmjava5.Entity.SanPham;
import com.example.asmjava5.Model.request.TimKiemSP;
import com.example.asmjava5.Service.SanPhamService;
import com.example.asmjava5.Service.ServiceImpl.SanPhamServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/san-pham")
public class SanPhamAPI {
    @Autowired
     private SanPhamService sanPhamService;

    @GetMapping("")
    public SanPham getSanPham(@RequestParam("maSP") String maSP) {
        return sanPhamService.getSanPhamById(maSP);
    }

    @GetMapping("/timkiems")
    public TimKiemSP timSPInHDCCT(@RequestParam("maSP") String maSP) {
        if (maSP.equalsIgnoreCase("")) {
            return null;
        }
        TimKiemSP sp = sanPhamService.timSPinHDCCT(maSP);
        return sp;
    }


}
