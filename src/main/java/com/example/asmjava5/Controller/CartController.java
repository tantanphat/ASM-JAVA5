package com.example.asmjava5.Controller;

import com.example.asmjava5.Constant.SessionAttr;
import com.example.asmjava5.Entity.SanPham;
import com.example.asmjava5.Model.DTO.CartItemsDTO;
import com.example.asmjava5.Service.CartService;
import com.example.asmjava5.Service.HoaDonService;
import com.example.asmjava5.Service.SanPhamService;
import com.example.asmjava5.Service.ServiceImpl.CartServiceImpl;
import com.example.asmjava5.Service.ServiceImpl.SanPhamServiceImpl;
import com.example.asmjava5.Utils.SessionUtils;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
//@RequestMapping("/user")
public class CartController {
    @Autowired
    private SessionUtils sessionUtils ;
    @Autowired
    private CartService cartService = new CartServiceImpl();
    @Autowired
    private HoaDonService hoaDonService;

    @GetMapping("/cart")
    @SuppressWarnings("unchecked")
    public String showCart(Model model) {
        List<CartItemsDTO> cartItems = (List<CartItemsDTO>) sessionUtils.laySession(SessionAttr.SESSION_KEY_CART);
        if (cartItems.size()==0) {
            System.out.println("Giỏ hàng đang trống");
            return "redirect:/Trang-chu";
        }
        for (CartItemsDTO cartItem : cartItems) {
            System.out.println(cartItem);
        }
        return "views/cart";
    }


    @GetMapping("/cart/remove")
    public String removeFromCart(@RequestParam("maSP") String maSP,
                                       @RequestParam("size") String size) {
        cartService.removeFromCart(maSP, size);
        return  "redirect:/cart";
    }

    @GetMapping("/cart/plus")
    public String addQuanlity(@RequestParam("maSP") String maSP, @RequestParam("size") String size) {
        cartService.plus(maSP, size);
        return  "redirect:/cart";
    }

    @GetMapping("/cart/minus")
    public String minusQuanlity(@RequestParam("maSP") String maSP, @RequestParam("size") String size) {
        cartService.minus(maSP, size);
        return  "redirect:/cart";
    }

    @GetMapping("/cart/Thanh-toan")
    public String getThanhToan(Model model) {
        String maHD = hoaDonService.AU_MaHD();
        model.addAttribute("maHD", maHD);
        return  "user/ThanhToan";
    }
}

