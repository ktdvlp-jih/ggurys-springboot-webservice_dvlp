package com.ggurys.springbootwebservice.web.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter // 선언된 필드에 get 메소드 생성
@RequiredArgsConstructor // final이 붙은 필드에 생성자 생성
public class HelloResponseDto {
    private final String name;
    private final int amount;

}
