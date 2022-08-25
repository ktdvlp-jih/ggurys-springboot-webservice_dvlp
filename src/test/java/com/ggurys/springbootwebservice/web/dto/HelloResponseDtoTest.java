package com.ggurys.springbootwebservice.web.dto;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class HelloResponseDtoTest {

    @Test
    public void lombok_function_Test(){
        String name = "name";
        int amount = 1995;

        HelloResponseDto dto = new HelloResponseDto(name,amount);

        assertThat(dto.getName()).isEqualTo(name); // 값이 같은지 검증
        assertThat(dto.getAmount()).isEqualTo(amount); // 값이 같은지 검증




    }
}
