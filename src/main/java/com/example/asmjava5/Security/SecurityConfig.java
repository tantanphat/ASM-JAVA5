package com.example.asmjava5.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    private final DataSource dataSource;
//
//    public SecurityConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
//        return manager;
//    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery("SELECT email AS username, MatKhau, 'true' AS enabled FROM KhachHang WHERE email = ?")
//                .authoritiesByUsernameQuery("SELECT email AS username, 'ROLE_USER' AS authority FROM KhachHang WHERE email = ?");
////                .passwordEncoder(passwordEncoder());
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        // Sử dụng NoOpPasswordEncoder để bỏ qua mã hóa mật khẩu
//        return NoOpPasswordEncoder.getInstance();
//    }



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorize ->
        {
            try {
                authorize
                        .requestMatchers("/assets/**","/**").permitAll()
                        .anyRequest().authenticated()
                        .and()
                        .csrf(AbstractHttpConfigurer::disable);//tắt tính năng bảo vệ CSRF
//                        .formLogin(login -> login
//                                .loginPage("/Dang-nhap") // Trang đăng nhập tùy chỉnh
//                                .permitAll() // Cho phép truy cập tự do
//                        );
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }// Yêu cầu xác thực cho các yêu cầu khác
        );
        return http.build();
    }




}
