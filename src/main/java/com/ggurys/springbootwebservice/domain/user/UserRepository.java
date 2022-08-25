package com.ggurys.springbootwebservice.domain.user;

/*
 *  User의 CRUD를 책임
 * User 클래스로 Database 접근하게 도와줄 JpaRepository, DB Layer 접근자, 인터페이스로 생성
 * JpaRepository<Entity클래스,PK타입>를 상속하면 기본적인 CRUD메소드가 자동생성됨
 * */

import com.ggurys.springbootwebservice.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;



@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email); // 소셜 로그인으로 반환되는 값 중 email을 통해 이미 생성도니 사용자인지 처음 가입하는 사용자인지 판단하기 위한 메소드

}
