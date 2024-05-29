package com.example.asmjava5.Controller;




import com.example.asmjava5.Model.request.LoginRequest;
import com.example.asmjava5.Service.ServiceImpl.KhachHangServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {
    @Autowired
    KhachHangServiceImpl khachHangServiceImpl;

    @Autowired
    private AuthenticationManager authenticationManager;


    private SecurityContextHolderStrategy securityContextHolderStrategy;


    //Hiển thị trang login
    @GetMapping("/Dang-nhap")
    public String getDangNhap() {
        return "views/Login";
    }

    private final SecurityContextRepository securityContextRepository =
            new HttpSessionSecurityContextRepository();//	Thêm SecurityContextRepositoryvào bộ điều khiển
    @PostMapping("/Dang-nhap")
    public void postDangNhap(@RequestBody LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response) {//Tiêm HttpServletRequestvà HttpServletResponseđể có thể lưuSecurityContext
        UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken.unauthenticated(
                loginRequest.getUsername(), loginRequest.getPassword());//Tạo thông tin không được xác thực UsernamePasswordAuthenticationTokenbằng thông tin đăng nhập được cung cấp
        Authentication authentication = authenticationManager.authenticate(token);//Gọi AuthenticationManager#authenticateđể xác thực người dùng
        SecurityContext context = securityContextHolderStrategy.createEmptyContext();
        context.setAuthentication(authentication);//Tạo một SecurityContextvà đặt Authenticationtrong đó
        securityContextHolderStrategy.setContext(context);
        securityContextRepository.saveContext(context, request, response);//Lưu SecurityContexttrongSecurityContextRepository
    }

    //Khi logout sẽ về trang Dang-nhap
    @RequestMapping("/logout")
    public String logout() {
        return "redirect:/Dang-nhap";
    }


}
