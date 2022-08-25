package com.ggurys.springbootwebservice.web.dto;

/*
 * DTO클래스 아무런 메소드를 가지지 않으면 Get,Set만을 가지고 있지만
 * DB에서 꺼낸 값을 가져오는 용도이기에 Set은 사용하지 않으면 Getter 롬북만을 사용해 Set은 사용하지 않습니다.
 * title과 content값 업데이트하는 DTO 클래스
 * */

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {
    private String title;
    private String content;

    @Builder
    public PostsUpdateRequestDto(String title, String content){
        this.title=title;
        this.content=content;
    }

}
