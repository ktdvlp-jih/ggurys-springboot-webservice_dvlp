package com.ggurys.springbootwebservice.web.dto;

/*
 * DTO(계층 간 데이터 교환을 하기 위해 사용하는 객체로, DTO는 로직을 가지지 않는 순수한 데이터 객체(getter & setter 만 가진 클래스))
 * 즉. 계층간에 데이터 교환만 하기에 DTO에서는 임의로 값을 조작할 필요가 없어 setter를 만들 필요가 없고 생성자에 값을 할당
 *
 * Entity 클래스란 실제 DB의 테이블과 매칭될 클래스를 의미
 * Entity 클래스와 유사한 Dto 클래스
 * Entity는 Request(요청)/Response(응답) 클래스로 사용해선 안됨
 *
 * */
import com.ggurys.springbootwebservice.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;

@Getter
//@NoArgsConstructor // 기본생성자 자동 추가 lombok 어노테이션,
@RequiredArgsConstructor // 생성자 (선언된 모든 final필드가 포함된 생성자 생성 (final이 없는 필드는 생성자에 포함되지 않음))
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author){
        Assert.hasText(title, "title must not be empty");
        Assert.hasText(content, "content must not be empty");
        Assert.hasText(author, "author must not be empty");

        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();

    }
}
