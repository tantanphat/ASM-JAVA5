package com.example.asmjava5.Service.ServiceImpl;

import com.example.asmjava5.Repository.SanPhamRepository;
import com.example.asmjava5.Entity.SanPham;
import com.example.asmjava5.Service.SanPhamService;
import jakarta.servlet.http.HttpSession;
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

    private List<SanPham> getCart(HttpSession session) {
        List<SanPham> cart = (List<SanPham>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }
        return cart;
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
    public void addToCart(String maSP, String size, int soLuong, HttpSession session) {
        List<SanPham> cart = getCart(session);
        boolean existed = false;
        for (SanPham sp : cart) {
            if (sp.getMaSP().equals(maSP) && sp.getSize().equals(size)) {
                sp.setSoLuong(sp.getSoLuong() + soLuong);
                existed = true;
                break;
            }
        }
        if (!existed) {
            SanPham sp = getSanPhamById(maSP);
            sp.setSize(size);
            sp.setSoLuong(soLuong);
            cart.add(sp);
        }
    }

    @Override
    public void removeFromCart(String maSP, String size, HttpSession session) {
        List<SanPham> cart = getCart(session);
        Iterator<SanPham> iterator = cart.iterator();
        while (iterator.hasNext()) {
            SanPham sp = iterator.next();
            if (sp.getMaSP().equals(maSP) && sp.getSize().equals(size)) {
                iterator.remove();
                break;
            }
        }
    }

    @Override
    public float calculateTotal(HttpSession session) {
        List<SanPham> cart = getCart(session);
        float total = 0;
        for (SanPham sp : cart) {
            total += sp.getGiaBan() * sp.getSoLuong();
        }
        return total;
    }

    @Override
    public List<SanPham> getCartItems(HttpSession session) {
        return getCart(session);
    }
    @Override
    public SanPham findSanPhamByMaSP(String maSP) {
        return sanPhamRepository.findSanPhambyMaSP(maSP);
    }

    @Override
    public List<SanPham> timKiemSanPham(String key) {
        return sanPhamRepository.timKiemSanPham(key);
    }
}