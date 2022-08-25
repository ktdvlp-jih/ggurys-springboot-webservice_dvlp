package com.ggurys.springbootwebservice.web;


import com.ggurys.springbootwebservice.service.posts.PostsService;
import com.ggurys.springbootwebservice.web.dto.HelloResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController  // 1. 컨트롤러를 JSON을 반환하는 컨트롤러로 만들어줌
@RequiredArgsConstructor
public class HelloController {

    private final PostsService postsService;
    @GetMapping("/hello") // 2. http 메소드인 get의 요청을 받을 수 있는 API 생성
    public String hello() {
        return "hello";

    }

    @GetMapping("/hello/sitemap") // 2. http 메소드인 get의 요청을 받을 수 있는 API 생성
    public String hello_안녕() {

        return "sitemap";

    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(
            @RequestParam("name") String name,
            @RequestParam("amount") int amount) { // 3. 외부에서 API로 넘긴 파라미터를 가져오는 어노테이션 즉 name,amount이란 이름으로 넘긴 파라미터를 메소드 파라미터 name,amount에 저장하게 되는것

        return new HelloResponseDto(name,amount);
    }


}
