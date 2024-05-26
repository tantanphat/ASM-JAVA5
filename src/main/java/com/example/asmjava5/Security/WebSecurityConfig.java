package com.example.asmjava5.Security;

import com.example.asmjava5.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Sử dụng NoOpPasswordEncoder để bỏ qua mã hóa mật khẩu
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.
                authorizeHttpRequests(authorizeRequests -> authorizeRequests
                                .requestMatchers("/assets/**","/Trang-chu","/Gioi-thieu").permitAll() // Cho phép truy cập vào tài nguyên tĩnh
                                .anyRequest().authenticated() // Yêu cầu xác thực cho tất cả các yêu cầu khác

                )
                .csrf(AbstractHttpConfigurer::disable)//tắt tính năng bảo vệ CSRF
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/Dang-nhap") // Trang login tùy chỉnh
                                .defaultSuccessUrl("/Trang-chu", true) // URL thành công sau khi đăng nhập
                                .permitAll() // Cho phép tất cả truy cập vào trang login
//                                .usernameParameter("username")
//                                .passwordParameter("password")
                                .failureHandler(authenticationFailureHandler())
                )
                .logout(Customizer.withDefaults())
                .rememberMe(rememberMe ->
                        rememberMe
                                .tokenRepository(persistentTokenRepository()) // Sử dụng persistentTokenRepository để lưu trữ token
                                .tokenValiditySeconds(1 * 24 * 60 * 60) // Thời gian tồn tại của token là 24 giờ
                )
//                .sessionManagement(sessionManagement ->
//                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.ALWAYS) // Luôn tạo session mới
//
//                )
                .sessionManagement(session -> session
                        .maximumSessions(1)
//                        .expiredUrl("/Dang-nhap")// Chuyển hướng đến trang đăng nhập khi phiên hết hạn
//                        .sessionRegistry(sessionRegistry())
                                .maxSessionsPreventsLogin(true)//Lần đăng nhập thứ hai sau đó sẽ bị từ chối

                );

        return http.build();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }


    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

    @Bean
    public SecurityContextRepository securityContextRepository() {
        return new HttpSessionSecurityContextRepository();
    }



//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080")); /
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
//        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl(); // Ta lưu tạm remember me trong memory (RAM). Nếu cần mình có thể lưu trong database
        return memory;
    }
}
