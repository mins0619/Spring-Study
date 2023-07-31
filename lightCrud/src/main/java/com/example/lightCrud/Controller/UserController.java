package com.example.lightCrud.Controller;

import com.example.lightCrud.Dto.request.UserLoginRequestDto;
import com.example.lightCrud.Dto.request.UserSignUpDto;
import com.example.lightCrud.Dto.response.UserLoginResponseDto;
import com.example.lightCrud.Service.Interface.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users")

public class UserController {

   private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> userSignUp(@RequestBody UserSignUpDto requestDto, HttpServletResponse response) throws Exception {
        userService.signUP(requestDto, response);
        return ResponseEntity.ok("회원가입 완료");
    }

    @GetMapping("/login")
    public UserLoginResponseDto login(@RequestBody UserLoginRequestDto requestDto, HttpServletResponse response) {
        return userService.login(requestDto, response);
    }



}
