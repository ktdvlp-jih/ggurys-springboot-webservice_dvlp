package com.ggurys.springbootwebservice.web;


/*
 * Controller
 * 게시판 API 컨트롤러
 * Autowired를 통해 스프링은 원래 Bean을 주입 받아야하지만 권장하지 않아
 * 생성자로 Bean을 객체로 받도록 해도 동일한 효과를 볼 수 있음
 * @RequiredArgsConstructor(lombok) 사용하는 이유 : 해당 클래스의 의존선 관계가 변경될 때마다 생성자 코드를 계속해서 수정하는 번거로움 해결
 * HTTP 메소드 CRUD
 * 생성-POST, 읽기-GET, 수정-PUT, 삭제-DELETE
 * */

import com.ggurys.springbootwebservice.service.posts.PostsService;
import com.ggurys.springbootwebservice.web.dto.PostsListResponseDto;
import com.ggurys.springbootwebservice.web.dto.PostsResponseDto;
import com.ggurys.springbootwebservice.web.dto.PostsSaveRequestDto;
import com.ggurys.springbootwebservice.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor // 생성자 (선언된 모든 final필드가 포함된 생성자 생성 (final이 없는 필드는 생성자에 포함되지 않음))
@RestController // 컨트롤러를 JSON을 반환 하는 컨트롤러로 만들어줌
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts") // 데이터 추가 및 등록
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        // @RequestBody : json 기반의 HTTP Body를 자바 객체로 변환
        return postsService.save(requestDto);
    }

    @GetMapping("/api/v1/posts/{id}") // 데이터 조회 (HTTP GET 요청)
    public PostsResponseDto findById (@PathVariable Long id){ // @PathVariable: URL에 변수 사용을 가능케함
        return postsService.findById(id);
    }

    @PutMapping("/api/v1/posts/{id}") // 데이터 업데이트 (HTTP PUT 요청)
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id,requestDto);
    }

    @DeleteMapping("/api/v1/posts/{id}") // 데이터 삭제 (HTTP Deletd 요청)
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }
    @GetMapping("/api/v1/posts/list")
    public List<PostsListResponseDto> findAll() {
        return postsService.findAllDesc();
    }
}
