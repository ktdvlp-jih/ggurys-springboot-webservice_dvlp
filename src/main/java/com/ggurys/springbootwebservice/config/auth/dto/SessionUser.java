package com.ggurys.springbootwebservice.config.auth.dto;

/*
* 인증된 사용자 정보만 필요
* 필요한 정보만 선언
* */

import com.ggurys.springbootwebservice.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
