package com.example.lightCrud.Config;


import com.example.lightCrud.Jwt.JwtAuthenticationTokenFilter;
import com.example.lightCrud.Repository.UserRepository;
import com.example.lightCrud.enums.UserRole;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    private final JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
                .csrf().disable()
                .cors()
                .and()
                .headers().frameOptions().disable()
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .authorizeRequests()
                // 로그인, 회원가입은 토큰 없이도 호출 가능하도록 permitAll() 설정
                .antMatchers(HttpMethod.POST,"/users/signup").permitAll()
                .antMatchers(HttpMethod.GET,"/users/login/**").permitAll()
                .antMatchers(HttpMethod.GET,"/boards").permitAll()
                .antMatchers(HttpMethod.GET,"/comment/boards/{boardId}").permitAll()
                // 정보수정은 USER, MANAGER, ADMIN 권한이 필요하도록 설정
               // .antMatchers(HttpMethod.POST,"").hasAnyAuthority(UserRole.USER.toString())
                // 나머지 요청에 대해서는 권한 제한 없이 호출 가능하도록 설정
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .and()
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

}