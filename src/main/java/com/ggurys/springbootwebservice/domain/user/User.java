package com.ggurys.springbootwebservice.domain.user;

/*
* 사용자 정보를 담당할 도메인 User 클래스
* */

import com.ggurys.springbootwebservice.domain.BaseTimeEntity;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id // 2. 해당 테이블의 PK필드를 의미
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 3. PK 생성 규칙
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Role role;

    @Builder
    public User(String name, String email, String picture, Role role){
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture){
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }

}
