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

    //Lấy toàn bộ sản phẩm có trong giỏ hàng
    @GetMapping("/getListCart")
    public List<CartItemsDTO> getAllCartItems() {
        return cartService.getAllCartItems();
    }

    //Thêm sản phẩm vào giỏ hàng
    @PostMapping("/cart/add")
    public CartItemsDTO addToCart(@RequestBody CartItemsDTO cartItems) {
        return cartService.addToCart(cartItems);
    }

    //Số lượng sản phẩm có trong giỏ hàng
    @GetMapping("/countListCart")
    public int getCartCount() {
        return cartService.countListCart();
    }
}
