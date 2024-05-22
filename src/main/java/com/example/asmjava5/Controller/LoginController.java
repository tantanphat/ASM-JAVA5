package com.example.asmjava5.Controller;


import com.example.asmjava5.Constant.SessionAttr;
import com.example.asmjava5.Entity.KhachHang;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    //Hiển thị trang login
    @RequestMapping("/Dang-nhap")
    public String demo() {
        return "/views/Login";
    }


}
