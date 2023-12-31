package com.example.lightCrud.Service.Impl;

import com.example.lightCrud.Dto.request.UserLoginRequestDto;
import com.example.lightCrud.Dto.request.UserSignUpDto;
import com.example.lightCrud.Dto.response.UserLoginResponseDto;
import com.example.lightCrud.Entity.User;
import com.example.lightCrud.Jwt.JwtTokenProvider;
import com.example.lightCrud.Repository.UserRepository;
import com.example.lightCrud.Service.Interface.UserService;
import com.example.lightCrud.enums.UserRole;
import com.example.lightCrud.error.ErrorCode;
import com.example.lightCrud.error.exception.UnAuthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;



    @Override
    public void signUP(UserSignUpDto requestDto, HttpServletResponse response) throws Exception {
        if(userRepository.findByEmail(requestDto.getEmail()).isPresent()){
            throw new Exception("이미 존재하는 이메일 입니다.");
        }
        requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        User user = requestDto.toEntity();
        userRepository.save(user);
    }

    @Override
    public UserLoginResponseDto login(UserLoginRequestDto requestDto, HttpServletResponse response) {
        User user = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new UnAuthorizedException("401", ErrorCode.ACCESS_DENIED_EXCEPTION);
        }
        String refreshToken = jwtTokenProvider.createRefreshToken(requestDto.getEmail(), user.getUserRole());
        setJwtTokenInHeader(requestDto.getEmail(), response, refreshToken);

        // RefreshToken을 User 엔티티에 저장하고 데이터베이스에 반영
        user.setRefreshToken(refreshToken);
        userRepository.save(user);
        return UserLoginResponseDto.builder()
                .responseCode("200")
                .build();
    }

    public void setJwtTokenInHeader(String email, HttpServletResponse response, String refreshToken) {
        UserRole userRole = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."))
                .getUserRole();

        String accessToken = jwtTokenProvider.createAccessToken(email, userRole);

        jwtTokenProvider.setHeaderAccessToken(response, accessToken);
        jwtTokenProvider.setHeaderRefreshToken(response, refreshToken);
    }


    @Override
    public User findUserByToken(HttpServletRequest request) {
        String email = jwtTokenProvider.getUserEmail(jwtTokenProvider.resolveAccessToken(request));
        User user = userRepository.findByEmail(email).orElseThrow();
        return user;
    }

}
