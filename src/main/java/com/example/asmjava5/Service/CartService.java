package com.example.asmjava5.Service;

import com.example.asmjava5.Entity.SanPham;
import com.example.asmjava5.Model.DTO.CartItemsDTO;
import jakarta.servlet.http.HttpSession;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public interface CartService {
    CartItemsDTO addToCart(CartItemsDTO items);

    List<CartItemsDTO> getAllCartItems();

    CartItemsDTO removeFromCart(String maSP, String size );

    CartItemsDTO plus(String maSP,String size );

    CartItemsDTO minus(String maSP,String size );

    int countListCart();
}
