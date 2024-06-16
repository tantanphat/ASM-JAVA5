package com.example.asmjava5.Service.ServiceImpl;

import com.example.asmjava5.Repository.SanPhamRepository;
import com.example.asmjava5.Entity.SanPham;
import com.example.asmjava5.Service.SanPhamService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
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
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private List<SanPham> getCart(HttpSession session) {
        List<SanPham> cart = (List<SanPham>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    public String generateNewMaSP() {
        return jdbcTemplate.queryForObject("SELECT dbo.AUTO_MaSP()", String.class);
    }

    @Override
    public List<SanPham> getAllSanPham() {
        return sanPhamRepository.findAll();
    }

    @Override
    public SanPham getSanPhamById(String maSP) {
        return sanPhamRepository.findSanPhambyMaSP(maSP);
    }


    @Override
    public List<SanPham> timKiemSanPham(String key) {
        return sanPhamRepository.timKiemSanPham(key);
    }

    @Override
    public void updateSP(SanPham sp) {
        SanPham spp = sanPhamRepository.findSanPhambyMaSP(sp.getMaSP());
        spp.setSoLuong(sp.getSoLuong());
        spp.setGhiChu(sp.getGhiChu());
        spp.setMaDM(sp.getMaDM());
        spp.setAnh(sp.getAnh());
        spp.setGiaBan(sp.getGiaBan());
        spp.setSize(sp.getSize());
        spp.setTenSP(sp.getTenSP());
        sanPhamRepository.save(spp);
    }

    @Override
    public void addSP(SanPham sp) {
        String newMaNV = generateNewMaSP();
        sp.setMaSP(newMaNV);
         sanPhamRepository.save(sp);
    }

    @Override
    public void deleteSP(String sp) {
        sanPhamRepository.deleteById(sp);
    }

    @Override
    public String AUTO_MASP() {
        return sanPhamRepository.AUTO_MA_SP();
    }

    @Override
    public List<SanPham> getAllSP(Integer pageNo, Integer pageSize, String sortBy, String sortOrder) {
        Pageable paging;
        if (sortOrder.equalsIgnoreCase("asc")) {
            paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());
        } else if (sortOrder.equalsIgnoreCase("desc")) {
            paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        } else {
            // Giá trị mặc định nếu sortOrder không hợp lệ
            paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());
        }

        Page<SanPham> pagedResult = sanPhamRepository.findAll(paging);
        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<SanPham>();
        }
    }


    @Override
    public List<SanPham> findByMaDM(int maDM) {
        return sanPhamRepository.findByMaDM(maDM);
    }

    public List<SanPham> findAllByDanhMuc(int maDM) {
        return sanPhamRepository.findByMaDM(maDM);
    }

    public List<SanPham> findAll() {
        return sanPhamRepository.findAll();
    }
}