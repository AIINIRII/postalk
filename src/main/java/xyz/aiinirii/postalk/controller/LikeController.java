package xyz.aiinirii.postalk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import xyz.aiinirii.postalk.bean.Post;
import xyz.aiinirii.postalk.bean.User;
import xyz.aiinirii.postalk.service.LikeService;
import xyz.aiinirii.postalk.service.PostService;

import java.util.List;

/**
 * @author AIINIRII
 */
@Controller
public class LikeController {

    private LikeService likeService;
    private PostService postService;

    @Autowired
    public void setLikeMapper(LikeService likeService) {
        this.likeService = likeService;
    }

    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/like/")
    public ModelAndView insertLike(@SessionAttribute("loginUser") User user, Integer id, ModelAndView modelAndView) throws Exception {

        if (!likeService.like(id, user.getId())) {
            throw new Exception("like operation error!");
        }

        List<Post> postList = postService.findAllPost();
        modelAndView.addObject("postList", postList);
        modelAndView.setViewName("/post/post");
        return modelAndView;
    }
}