package com.example.asmjava5.Security;

import com.example.asmjava5.Security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true,proxyTargetClass = true)
public class WebSecurityConfig {
    @Autowired
    UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
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
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

//    @Bean
//    public static ServletListenerRegistrationBean httpSessionEventPublisher() {
//        return new ServletListenerRegistrationBean(new HttpSessionEventPublisher());
//    }


    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

    @Bean
    public SecurityContextRepository securityContextRepository() {
        return new HttpSessionSecurityContextRepository();
    }


    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl();
        return memory;
    }

    @Bean
    @Order(1)
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/admin","/admin/**")//Bảo mật cho tài nguyên và chỉ cho cho Admin,Staff ms có quyền truy cập tài nguyên này
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/admin","/admin/**").authenticated()
                        .requestMatchers("/admin/**","/admin"
                        ).hasAnyRole("ADMIN","STAFF")
                )
                .formLogin(login -> login
                        .loginPage("/admin/Login")//Khi truy cập tài nguyên admin thì nó sẽ trả về trang /admin/Login
                        .loginProcessingUrl("/Dang-nhap")
                        .defaultSuccessUrl("/amin")
                        .permitAll()
                        .failureHandler(authenticationFailureHandler())//Trả về thông báo nếu có sai sót trong quá trình xác thực
                    )
                .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/admin/Login")//Khi admin hoặc nhân viên logout nó sẽ trả về trang đăng nhập của admin
                .invalidateHttpSession(true)//Xóa session khi logout
                .deleteCookies("JSESSIONID")//Xóa cookies thì logout
                .permitAll()
                )
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedPage("/error")//Nếu lỗi xảy ra thì nó sẽ trả về trang lỗi

                )
                .csrf(AbstractHttpConfigurer::disable)//tắt tính năng bảo vệ CSRF
                .rememberMe(rememberMe ->
                        rememberMe
                                .tokenRepository(persistentTokenRepository()) // Sử dụng persistentTokenRepository để lưu trữ token
                                .tokenValiditySeconds( 60 * 60*60) // Thời gian tồn tại của token là 1 giờ
                );
//                .sessionManagement(sessionManagement ->
//                        sessionManagement
//                                .maximumSessions(1)
//                                .expiredUrl("/admin/Login")
//                                .maxSessionsPreventsLogin(true)
//                                .sessionRegistry(sessionRegistry())
//                );

        return http.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth  -> auth
                        .requestMatchers("/user/**","/cart","/cart/**").authenticated() // Cho phép truy cập vào tài nguyên tĩnh
                        .requestMatchers("/user/**","/cart","/cart/**").hasRole("USER")
                                .anyRequest().permitAll() // Yêu cầu xác thực cho tất cả các yêu cầu khác

                )
                .csrf(AbstractHttpConfigurer::disable)//tắt tính năng bảo vệ CSRF
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/Dang-nhap") // Trang login tùy chỉnh
                                .loginProcessingUrl("/Dang-nhap")
                                .defaultSuccessUrl("/Trang-chu", true) // URL thành công sau khi đăng nhập
                                .permitAll() // Cho phép tất cả truy cập vào trang login
                                .failureHandler(authenticationFailureHandler())
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/Dang-nhap")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll())
                .rememberMe(rememberMe ->
                        rememberMe
                                .tokenRepository(persistentTokenRepository()) // Sử dụng persistentTokenRepository để lưu trữ token
                                .tokenValiditySeconds(60 * 60) // Thời gian tồn tại của token là 24 giờ
                )
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)

                );
//                .sessionManagement(sessionManagement ->
//                        sessionManagement
//                                .maximumSessions(1)
//                                .expiredUrl("/Dang-nhap")
//                                .maxSessionsPreventsLogin(true)
//                                .sessionRegistry(sessionRegistry())
//                );

        return http.build();
    }

//    //Tải thông tin người dùng khi đăng nhập bằng google
//    @Bean
//    public OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService() {
//        return new DefaultOAuth2UserService();
//    }
//
//
//    //Xử lý thông tin khi đăng nhập bằng google
//    @Bean
//    public AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler() {
//        return new CustomOAuth2AuthenticationSuccessHandler();
//    }


}
