package com.example.lightCrud.Dto.request;

import com.example.lightCrud.Entity.User;
import com.example.lightCrud.enums.UserRole;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserSignUpDto {

    private String nickname;
    private String email;
    private String password;

    public User toEntity(){
        User user = User.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .userRole(UserRole.USER)
                .build();
        return user;
    }


}
