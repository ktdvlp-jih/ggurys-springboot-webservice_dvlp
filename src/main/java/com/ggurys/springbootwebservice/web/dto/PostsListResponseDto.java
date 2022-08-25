package com.ggurys.springbootwebservice.web.dto;

/*
* 게시판 응답 Dto
* id,title,content,author
* DTO는 Data Transfer Object의 약자로, 계층 간 데이터 교환 역할을 한다.
* DB에서 꺼낸 데이터를 저장하는 Entity를 가지고 만드는 일종의 Wrapper라고 볼 수 있는데,
* Entity를 Controller 같은 클라이언트단과 직접 마주하는 계층에 직접 전달하는 대신 DTO를 사용해 데이터를 교환한다.
* DTO는 계층간에 데이터 교환이 이루어지도록만 해야해서 특별한 로직이 없는 순수한 데이터 객체이여야하면
* DB에서 꺼낸 값을 DTO에서 임의로 조작할수 없기때문에 setter를 할당하지않고 생성자에서 값을 할당함
* */

import com.ggurys.springbootwebservice.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostsListResponseDto {
    private Long id;
    private String title;
    private String author;
    private String content;
    private LocalDateTime modifiedDate;

    public PostsListResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.content = entity.getContent();
        this.modifiedDate = entity.getModifiedDate();
    }

}

