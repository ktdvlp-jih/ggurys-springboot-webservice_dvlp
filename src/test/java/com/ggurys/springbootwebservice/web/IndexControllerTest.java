package com.ggurys.springbootwebservice.web;

import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Log4j2
//@RestController
//@WebMvcTest(controllers = HelloController.class)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class IndexControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
//    @Autowired // 3. 스프링이 관리하는 빈을 주입 받음
//    private MockMvc mvc; // 4. 웹 API 테스트용 스프링 mvc패턴의 시작 이 클래스를 통해 GET,POST에 대한 API 테스트 가능


    @Test
    public void mainpage_Load(){
        // when
        String body = this.restTemplate.getForObject("/",String.class);
        log.info("body");
        log.info(body);

        // then
//        assertThat(body).contains("스프링 부트로 시작하는 웹 서비스");
    }
    @Test
    public void posts_Load(){
        // when
        String body = this.restTemplate.getForObject("/posts/save",String.class);
        log.info("body");
        log.info(body);

        // then
//        assertThat(body).contains("게시글 등록");
//        assertThat(status().isOk());
    }
}
