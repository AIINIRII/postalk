package xyz.aiinirii.postalk.controller;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import xyz.aiinirii.postalk.bean.Comment;
import xyz.aiinirii.postalk.bean.Post;
import xyz.aiinirii.postalk.bean.User;
import xyz.aiinirii.postalk.service.CommentService;
import xyz.aiinirii.postalk.service.PostService;

import java.util.List;

/**
 * @author AIINIRII
 */
@Controller
public class CommentController {
    private CommentService commentService;
    private PostService postService;

    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/post/{id}/comment")
    public ModelAndView toCreateCommentPage(@PathVariable("id") Integer id, ModelAndView modelAndView) {

        modelAndView.addObject("pid", id);
        modelAndView.setViewName("/comment/create");
        return modelAndView;
    }

    @PostMapping("/comment/")
    public ModelAndView createComment(ModelAndView modelAndView,
                                      Comment comment,
                                      Integer pid,
                                      @SessionAttribute("loginUser") User user) {
        commentService.createComment(pid, comment, user);
        List<Post> postList = postService.findAllPost();
        modelAndView.addObject("postList", postList);
        modelAndView.setViewName("/post/post");
        return modelAndView;
    }

    @DeleteMapping("/comment/{id}")
    public ModelAndView deleteComment(ModelAndView modelAndView,
                                      @SessionAttribute("loginUser") User user,
                                      @PathVariable("id") Integer id) throws Exception {
        if (!commentService.deleteCommentById(id, user)){
            throw new Exception("delete comment failed");
        } else {
            List<Post> postList = postService.findAllPost();
            modelAndView.addObject("postList", postList);
            modelAndView.setViewName("/post/post");
        }
        return modelAndView;
    }
}