package com.example.asmjava5.Service.ServiceImpl;

import com.example.asmjava5.Model.request.TimKiemSP;
import com.example.asmjava5.Repository.SanPhamRepository;
import com.example.asmjava5.Entity.SanPham;
import com.example.asmjava5.Service.SanPhamService;
import jakarta.servlet.http.HttpSession;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class SanPhamServiceImpl implements SanPhamService {

    @Autowired
    private SanPhamRepository sanPhamRepository;

    @Override
    public List<SanPham> getAllSanPham() {
        return sanPhamRepository.findAll();
    }

    @Override
    public SanPham getSanPhamById(String maSP) {
        return sanPhamRepository.findSanPhambyMaSP(maSP);
    }

    @Override
    public TimKiemSP timSPinHDCCT(String masp) {
        SanPham sp= sanPhamRepository.findSanPhambyMaSP(masp);
        TimKiemSP tk = new TimKiemSP(sp.getTenSP(), sp.getGiaBan());
        return tk;
    }


}