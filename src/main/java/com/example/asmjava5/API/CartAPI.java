package com.example.asmjava5.API;

import com.example.asmjava5.Model.DTO.CartItemsDTO;
import com.example.asmjava5.Service.CartService;
import com.example.asmjava5.Service.ServiceImpl.CartServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CartAPI {
    @Autowired
    private HttpSession session;
    @Autowired
    private CartService cartService = new CartServiceImpl();

    @GetMapping("/getListCart")
    public List<CartItemsDTO> getAllCartItems() {
        return cartService.getAllCartItems();
    }

    @PostMapping("/cart/add")
    public CartItemsDTO addToCart(@RequestBody CartItemsDTO cartItems) {
        return cartService.addToCart(cartItems);
    }

    @GetMapping("/countListCart")
    public int getCartCount() {
        return cartService.countListCart();
    }
}
