package com.ggurys.springbootwebservice.web;


/*
 * 페이지와 관련된 컨트롤러인 IndexController
 * 페이지 경로
 * */

//import com.ggurys.springbootwebservice.config.auth.dto.SessionUser;
import com.ggurys.springbootwebservice.service.posts.PostsService;
import com.ggurys.springbootwebservice.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) { // @PathVariable: URL에 변수 사용을 가능케함
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }


//    public String index(Model model) { // model 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장 (postsService.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달)
//        model.addAttribute("posts", postsService.findAllDesc());
//
//        SessionUser user = (SessionUser)httpSession.getAttribute("user");
//
//        if(user != null) {
//            model.addAttribute("userName", user.getName());
//        }
//
//        return "index";
//    }

}
