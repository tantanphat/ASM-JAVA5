package com.example.asmjava5.Controller;

import com.example.asmjava5.Entity.SanPham;
import com.example.asmjava5.Service.SanPhamService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {

    @Autowired
    private SanPhamService sanPhamServiceImpl;

    @GetMapping("/cart")
    public String showCart(Model model, HttpSession session) {
        model.addAttribute("cartItems", sanPhamServiceImpl.getCartItems(session));
        model.addAttribute("total", sanPhamServiceImpl.calculateTotal(session));
        return "/views/cart";
    }


    @PostMapping("/cart/add")
    public String addToCart(@RequestParam("maSP") String maSP,
                            @RequestParam("size") String size,
                            @RequestParam("soLuong") int soLuong,
                            HttpSession session) {
        sanPhamServiceImpl.addToCart(maSP, size, soLuong, session);
        return "redirect:/cart";
    }

    @PostMapping("/cart/remove")
    public String removeFromCart(@RequestParam("maSP") String maSP,
                                 @RequestParam("size") String size,
                                 HttpSession session) {
        sanPhamServiceImpl.removeFromCart(maSP, size, session);
        return "redirect:/cart";
    }
}

