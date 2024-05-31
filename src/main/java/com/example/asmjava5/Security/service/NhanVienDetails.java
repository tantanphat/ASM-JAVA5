package com.example.asmjava5.Security.service;


import com.example.asmjava5.Entity.NhanVien;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
@AllArgsConstructor
public class NhanVienDetails implements UserDetails {
    private NhanVien nv;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (nv.getVaiTro() == true ) {
            return Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_STAFF"));
    }


    @Override
    public String getPassword() {
        return nv.getMatkhau();
    }

    @Override
    public String getUsername() {
        return nv.getMaNV();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
