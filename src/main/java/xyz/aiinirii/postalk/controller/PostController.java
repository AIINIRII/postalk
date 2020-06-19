package xyz.aiinirii.postalk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import xyz.aiinirii.postalk.bean.Post;
import xyz.aiinirii.postalk.bean.User;
import xyz.aiinirii.postalk.service.PostService;

import javax.servlet.http.HttpServletRequest;
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
        modelAndView.setViewName("/post/post");
        return modelAndView;
    }

    @GetMapping("/post/")
    public ModelAndView toCreatePostPage(ModelAndView modelAndView) {
        modelAndView.setViewName("post/create");
        return modelAndView;
    }

    @PostMapping("/post/")
    public ModelAndView createPost(ModelAndView modelAndView, Post post, HttpServletRequest request) {

        // get login user from the session
        User user = (User) request.getSession().getAttribute("loginUser");

        // add user dependence to the post
        post.setUser(user);

        // create the post
        postService.createPost(post);

        // find the post again with the user id
        List<Post> postList = postService.findAllPostByUId(user.getId());
        modelAndView.addObject("postList", postList);

        modelAndView.setViewName("user/myPage");
        return modelAndView;
    }
}