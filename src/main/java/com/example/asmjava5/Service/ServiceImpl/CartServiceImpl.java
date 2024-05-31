package com.example.asmjava5.Service.ServiceImpl;

import com.example.asmjava5.Model.DTO.CartItemsDTO;
import com.example.asmjava5.Service.CartService;
import com.example.asmjava5.Service.SanPhamService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;
@SessionScope
@Service
public class CartServiceImpl implements CartService {

    List<CartItemsDTO> cartItemsDTOS = new ArrayList<>();

    @Autowired
    private HttpSession session;


    @Autowired
    private SanPhamService sanPhamService = new SanPhamServiceImpl();


    @Override
    public CartItemsDTO addToCart(CartItemsDTO items) {
        boolean existed = false;

        for (CartItemsDTO cartItem:getAllCartItems()) {
            if (cartItem.getMaSP().equals(items.getMaSP()) && cartItem.getSize().equals(items.getSize())) {
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                existed = true;
                break;
            }
        }

        if (!existed) {
            CartItemsDTO cartItem = new CartItemsDTO(items.getMaSP(), items.getTenSP(), items.getGiaBan(), items.getQuantity(), items.getAnh(), items.getSize());
            cartItemsDTOS.add(cartItem);
        }

        return items;
    }


    @Override
    public List<CartItemsDTO> getAllCartItems() {
        return cartItemsDTOS;
    }

    @Override
    public CartItemsDTO removeFromCart(String maSP, String size) {
        List<CartItemsDTO> cart = getAllCartItems();
        Iterator<CartItemsDTO> iterator = cart.iterator();
        while (iterator.hasNext()) {
            CartItemsDTO sp = iterator.next();
            if (sp.getMaSP().equals(maSP) && sp.getSize().equals(size)) {
                iterator.remove();
                return sp;
            }
        }
        return null;
    }

    @Override
    public CartItemsDTO plus(String maSP, String size) {
        List<CartItemsDTO> cart = getAllCartItems();
        for (CartItemsDTO sp : cart) {
            if (sp.getMaSP().equals(maSP) && sp.getSize().equals(size)) {
                sp.setQuantity(sp.getQuantity() + 1);
                return sp;
            }
        }
        return null;
    }

    @Override
    public CartItemsDTO minus(String maSP, String size) {
        List<CartItemsDTO> cart = getAllCartItems();
        for (CartItemsDTO sp : cart) {
            if (sp.getMaSP().equals(maSP) && sp.getSize().equals(size)) {
                sp.setQuantity(sp.getQuantity() - 1);
                return sp;
            }
        }
        return null;
    }

    @Override
    public int countListCart() {
        int totalQuantity = 0;
        List<CartItemsDTO> cartItems = getAllCartItems();
        for (CartItemsDTO cartItem : cartItems) {
            totalQuantity += cartItem.getQuantity();
        }
        return totalQuantity;
    }



}
