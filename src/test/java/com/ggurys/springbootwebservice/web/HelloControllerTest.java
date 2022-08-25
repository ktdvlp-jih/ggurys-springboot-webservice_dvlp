package com.ggurys.springbootwebservice.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class) // J 테스트 진행시 JUnit에 내장된 실행자 외에 다른 실행자를 실행
@WebMvcTest(controllers = {HelloController.class}, secure = false) // 2. @Controller,ControllerAdvice등 사용 가능 (스프링 테스트 중 MVC에 집중할수있는 어노테이션)
//@WebMvcTest(controllers = HelloController.class,
//        excludeFilters = {
//        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
//        }
//)

public class HelloControllerTest {

    @Autowired // 스프링 Bean 주입
    private MockMvc mvc; // 웹 API 테스트, HTTP GET,Post 등에 대한 API 테스트

    @Test
    public void hello_리턴() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) // MockMVC를 통해 /hello로 get 요청
                .andExpect(status().isOk()) // HTTP 상태값 검증
                .andExpect(content().string(hello)); // 응답 본문 내용 검증 (리턴값이 hello가 맞는지)
    }

    @Test
    public void helloDto_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                                .param("name", name) // API 테스트할때 사용될 요청 파라미터 설정
                                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name))) // JSON 응답값을 필드별로 검증 할 수 있는 메소드
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
