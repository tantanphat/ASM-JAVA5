package com.example.asmjava5.Service;

import com.example.asmjava5.Entity.KhachHang;
import com.example.asmjava5.Repository.KhachHangRepository;
import com.example.asmjava5.Security.KhachHangDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private KhachHangRepository KhachHangRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        KhachHang kh = KhachHangRepository.findByEmail(username);
        if (kh==null){
            throw new UsernameNotFoundException(username);
        }

        return new KhachHangDetails(kh);
    }
}
