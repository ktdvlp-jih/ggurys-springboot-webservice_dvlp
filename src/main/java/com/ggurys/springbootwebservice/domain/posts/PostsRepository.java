package com.ggurys.springbootwebservice.domain.posts;

/*
 *  Posts의 CRUD를 책임
 * Posts 클래스로 Database 접근하게 도와줄 JpaRepository, DB Layer 접근자, 인터페이스로 생성
 * JpaRepository<Entity클래스,PK타입>를 상속하면 기본적인 CRUD메소드가 자동생성됨
 * */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface PostsRepository extends JpaRepository<Posts,Long> {

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC") // Posts게시판을 id기준으로 내림차순
    List<Posts> findAllDesc();

}
