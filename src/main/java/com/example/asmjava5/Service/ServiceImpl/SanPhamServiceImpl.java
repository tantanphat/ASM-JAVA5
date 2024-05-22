package com.example.asmjava5.Service.ServiceImpl;

import com.example.asmjava5.Repository.SanPhamDao;
import com.example.asmjava5.Entity.SanPham;
import com.example.asmjava5.Service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SanPhamServiceImpl implements SanPhamService {
    @Autowired
    private SanPhamDao sanPhamDao;

    @Override
    public List<SanPham> getAllSanPham() {
        return sanPhamDao.findAll();
    }

    @Override
    public SanPham getSanPhamById(String maSP) {
        return sanPhamDao.findSanPhambyMaSP(maSP);
    }
}