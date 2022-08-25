package com.ggurys.springbootwebservice.service.posts;

/*\
 *
 * */


import com.ggurys.springbootwebservice.domain.posts.Posts;
import com.ggurys.springbootwebservice.domain.posts.PostsRepository;
import com.ggurys.springbootwebservice.web.dto.PostsListResponseDto;
import com.ggurys.springbootwebservice.web.dto.PostsResponseDto;
import com.ggurys.springbootwebservice.web.dto.PostsSaveRequestDto;
import com.ggurys.springbootwebservice.web.dto.PostsUpdateRequestDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor // 생성자 (선언된 모든 final필드가 포함된 생성자 생성 (final이 없는 필드는 생성자에 포함되지 않음))
@Service // 알맞은 정보를 가공 -> 비즈니스 로직을 수행 -> DB에접근하는 DAO(sql문을 실행할 수 잇는 객체 )이용해서 결과값을 받아옴
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    /*@Transactional (db 상태변경, begin,commit 자동 수행, 예외발생시 rollback처리 자동) 즉 해당 어노테이션이 추가되면 프록시 객체가 생성
    이 프록시 객체는 메소드 메소드 호출시 정상 여부에 따라 Commit 또는 Rollback을 함 */
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        // orElseThrow() 메소드: 저장된 값이 존재하면 그값을 반환하고 값이 존재하지 않으면 인수로 전달된 예를를 발생시킵니다.
        // IllegalArgumentException() 메소드: 적합하지 않거나 저절하지 못한 인자를 메소드에 넘겨주었을때 반환
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        // 여기서 쿼리를 날리지 않고 Repository를 통해 수정하고자하는 게시판Post를 가져옵니다
        // 중요한 부분인데 JPA의 영속성 컨텍스트(Entity 영구저장) 때문에 데이터 값을 변경하면 트랜잭션이 끝나는 시점에 해당 테이블에 변경부을 반영 (즉 Entity 객체값만 변경하면 update쿼리를 날릴필요 없음)
        // JPA는 트랜잭션이 끝나는 시점 변화가 있는 모든 Entity를 데이터베이스에 자동 반영합니다.
        // https://jojoldu.tistory.com/415 (dirty checking) 확인
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        return new PostsResponseDto(entity);
    }


    // postsRepository.delete(posts)  // 데이터삭제
    /* JpaRepository에서 이미 delete 메소드를 지원하니 이를 이용
     *  엔티티를 파라미터로 삭제 할 수도 있고 , deleteById 메소드를 이용하여 id로 바로 삭제할수도 있음
     *  존재하는 Posts인지 확인을 위해 엔티티 조회 후 그래도 삭제
     * */
    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        postsRepository.delete(posts);
    }


    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

}
