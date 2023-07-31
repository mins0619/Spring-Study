package com.example.lightCrud.Service.Interface;

import com.example.lightCrud.Dto.request.UserLoginRequestDto;
import com.example.lightCrud.Dto.request.UserSignUpDto;
import com.example.lightCrud.Dto.response.UserLoginResponseDto;
import com.example.lightCrud.Entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;


public interface UserService {

    void signUP(UserSignUpDto userSignUpDto, HttpServletResponse response) throws Exception;
    UserLoginResponseDto login(UserLoginRequestDto requestDto, HttpServletResponse response);


    User findUserByToken(HttpServletRequest request);
}

