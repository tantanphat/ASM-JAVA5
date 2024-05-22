package com.example.asmjava5.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @RequestMapping("/myaccount")
    public String demo2() {
        return "/views/myaccount";
    }
}
