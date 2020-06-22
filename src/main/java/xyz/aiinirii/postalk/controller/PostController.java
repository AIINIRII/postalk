package xyz.aiinirii.postalk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
    public ModelAndView findAllPost(ModelAndView modelAndView) {
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

    @GetMapping("/post/{id}")
    public ModelAndView toUpdatePostPage(@PathVariable("id") Integer id, ModelAndView modelAndView) {
        // find the post content with id
        Post post = postService.findPostById(id);

        // add the post into the model
        modelAndView.addObject("post", post);

        modelAndView.setViewName("/post/create");
        return modelAndView;
    }

    @PutMapping("/post/")
    public ModelAndView updatePost(Post post, ModelAndView modelAndView, @SessionAttribute("loginUser") User user) throws Exception {

        boolean updatePost = postService.updatePost(post, user);
        if (!updatePost) {
            throw new Exception("the update operation is failed");
        }

        // find the post again with the user id
        List<Post> postList = postService.findAllPostByUId(user.getId());
        modelAndView.addObject("postList", postList);

        modelAndView.setViewName("user/myPage");
        return modelAndView;
    }

    @DeleteMapping("/post/{id}")
    public ModelAndView deletePost(@SessionAttribute("loginUser") User user, @PathVariable("id") Integer id, ModelAndView modelAndView) throws Exception {

        // delete post by id
        boolean deletePostById = postService.deletePostById(id, user);

        // check whether the post is deleted
        if (!deletePostById) {
            throw new Exception("the delete operation failed");
        }

        // find the post again with the user id
        List<Post> postList = postService.findAllPostByUId(user.getId());
        modelAndView.addObject("postList", postList);

        modelAndView.setViewName("user/myPage");
        return modelAndView;
    }
}