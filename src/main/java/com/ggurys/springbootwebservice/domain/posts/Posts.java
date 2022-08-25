package com.ggurys.springbootwebservice.domain.posts;

import com.ggurys.springbootwebservice.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;

/*
 * Posts 클래스
 * 실제 DB의 테이블과 매칭될 클래스이며 Entity클래스라고도 합니다
 * 해당 클래스는 Setter메소드가 없는데 자바빈 규약을 생각하면 무작성 getter,setter를 생겅하는 경우가 있지만 Entity클래스에서는 절때 생성하지 않습니다
 * 왜냐하면 무작정 생상시 해당 클래스의 인스턴스 값들이 추후에 어디서 변해야하는지 코드상으로 명확하게 구분이 안돼, 추후 변경시 복잡하기 떄문입니다.
 * 대신 해당 필드의 값 변경이 필요하면 명확학 목적과 의도를 나타낼수 있는 메소드를 추가해야합니다. (메소드명 중요)
 *
 * */

@Getter // 6. lombok 어노테이션, 클래스 내 모든 필드의 getter메소드 자동생성
@NoArgsConstructor // 5. lombok 어노테이션, 기본생성자 자동 추가
@Entity // 1. JPA어노테이션 테이블과 링클될 클래스
@EnableJpaAuditing // 날짜 자동 들록

public class Posts extends BaseTimeEntity {

    @Id // 2. 해당 테이블의 PK필드를 의미
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 3. PK 생성 규칙
    private Long id;


    @Column(length = 500, nullable = false) // 4. 테이블 칼럼생성 (사용안해도 무방하지만 추가 변경이 필요할 경우 사용)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    private String author;

    @Builder // 7. 빌더 패턴 클래스 생성 ()
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;

    }

    // 업데이트
    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }


}