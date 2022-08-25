package com.ggurys.springbootwebservice.domain.user;

/*
 * 사용자 권한을 관리할 Enum 클래스 Role
 * */

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    GUEST("ROLE_GUEST", "손님"),
    USER("ROLE_USER", "일반 사용자");

    private final String key;
    private final String title;

}
