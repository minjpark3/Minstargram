package com.cos.photogramstart.web.dto.user;

import com.cos.photogramstart.domain.user.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class UserPwDto {
    private String password;


    public User toEntity() {
        return User.builder()
                .password(password)
                .build();
    }
}
