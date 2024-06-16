package com.example.asmjava5.Controller;




import com.example.asmjava5.Constant.SessionAttr;
import com.example.asmjava5.Model.request.LoginRequest;
import com.example.asmjava5.Service.ServiceImpl.KhachHangServiceImpl;
import com.example.asmjava5.Utils.SessionUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class LoginController {
    private SessionUtils session;
    @Autowired
    HttpServletRequest request;
    @Autowired
    HttpServletResponse response;
    @Autowired
    KhachHangServiceImpl khachHangServiceImpl;

    @Autowired
    private AuthenticationManager authenticationManager;

    private SecurityContextHolderStrategy securityContextHolderStrategy;

    //Hiển thị trang login
    @GetMapping("/Dang-nhap")
    public String getDangNhap(Principal principal) {
        // Kiểm tra nếu người dùng đã đăng nhập chưa, nếu rồi thì chuyển ra trang chủ và chưa thì trang đăng nhập
        if (principal != null) {
            return "redirect:/Trang-chu";
        } else {
            return "views/login";
        }
    }

    private final SecurityContextRepository securityContextRepository =
            new HttpSessionSecurityContextRepository();//	Thêm SecurityContextRepositoryvào bộ điều khiển
    //Xử lý thông  tin đăng nhập
//    @RequestMapping(
//            path = "/Dang-nhap",
//            method = RequestMethod.POST,
//            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
//            produces = {
//                    MediaType.APPLICATION_ATOM_XML_VALUE,
//                    MediaType.APPLICATION_JSON_VALUE
//            })
    @PostMapping("/Dang-nhap")
    public void postDangNhap( LoginRequest loginRequest) {//Tiêm HttpServletRequestvà HttpServletResponseđể có thể lưuSecurityContext
        UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken.unauthenticated(
                loginRequest.getUsername(), loginRequest.getPassword());//Tạo thông tin không được xác thực UsernamePasswordAuthenticationTokenbằng thông tin đăng nhập được cung cấp
        Authentication authentication = authenticationManager.authenticate(token);//Gọi AuthenticationManager#authenticateđể xác thực người dùng
        SecurityContextHolderStrategy securityContextHolderStrategy = this.securityContextHolderStrategy;

        try {
            SecurityContext context = securityContextHolderStrategy.createEmptyContext();
            context.setAuthentication(authentication);//Tạo một SecurityContextvà đặt Authenticationtrong đó
            securityContextHolderStrategy.setContext(context);
            securityContextRepository.saveContext(context, request, response);//Lưu SecurityContexttrongSecurityContextRepository
            session.addSession(SessionAttr.CURRENT_USER,loginRequest.getUsername());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }



    @GetMapping("/admin/Login")
    public String doGetAdminLoginController() {
        return "/views/Admin/adminLogin";
    }

    @GetMapping("/Quen-mat-khau")
    public String getQuenMatKhau() {
        return "/views/Quen-Mat-Khau";
    }




}
