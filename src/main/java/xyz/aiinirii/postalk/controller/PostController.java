package xyz.aiinirii.postalk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import xyz.aiinirii.postalk.bean.Post;
import xyz.aiinirii.postalk.service.PostService;

import java.util.List;

/**
 * @author AIINIRII
 */
@Controller
public class PostController {

    PostService postService;

    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts/")
    public ModelAndView findAllPost(ModelAndView modelAndView){
        List<Post> postList = postService.findAllPost();
        modelAndView.addObject("postList", postList);
        modelAndView.setViewName("/post/index");
        return modelAndView;
    }
}