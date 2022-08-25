package com.ggurys.springbootwebservice.domain.posts;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*; // 이 방법으로 쓰지말고
import static org.assertj.core.api.Assertions.assertThat; // 이 방법을 쓰자

import java.time.LocalDateTime;
import java.util.List;

/*
 * JpaRepositoryTest
 *  sava,findAll 기능을 테스트 해보기
 */

@Log4j2
@RunWith(SpringRunner.class) // 1. 테스트 진행지 JUnit에 내장된 실행자 외 다른 실행자 실행 즉 스트링부트 테스트와 JUnit의 연결자 (SpringRunner 실행)
@SpringBootTest
//@EnableJpaAuditing
public class PostsRepositoryTest {

    @Autowired // 2. 스프링이 관리하는 빈을 주입 받음
    PostsRepository postsRepository;

    @After
    // 3. Junit 단위 테스트 끝날때 마다 수행, // 배포 전 전체 테스트 수행할때 테스트간 데이터 침범을 막기위해 사용하기도 하며 여러 테스트가 동시 실행시 h2에 데이터가 남아 다음 실행시 테스트 실패 가능성이 있음
    public void cleanUp() {
        postsRepository.deleteAll();
    }

    @Test
    public void post_load() {
        // 초기값 설정
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder() // 4. posts에 insert,update 쿼리 실행 id가 있으면 update없으면 insert
                .title(title)
                .content(content)
                .author("ggury@gmail.com")
                .build());

        // 객체 생성 ()
        List<Posts> postsList = postsRepository.findAll(); // 5. 테이블 posts에 있는 모든 데이터를 조회하는 메소드

        // 값 검증 및 확인
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }



    @Test
    public void BaseTimeEntity_등록(){
        // 초기값 설정
        String title = "테스트 게시글";
        String content = "테스트 본문";
        String author = "ggury";

        // 초기값
        LocalDateTime now = LocalDateTime.of(2022,8,14,19,27,30);
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build());

        // 언제?
        List<Posts> postsList = postsRepository.findAll();

        // 그 이후
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>> createDate = " + posts.getCreatedDate());
        System.out.println(">>>>>>> createDate = " + posts.getCreatedDate());
        System.out.println(">>>>>>> modifieDate = " + posts.getModifiedDate());
        System.out.println(">>>>>>> modifieDate = " + now);

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
//        log.info(posts.getCreatedDate());
//        log.info(posts.getModifiedDate());

    }
}
