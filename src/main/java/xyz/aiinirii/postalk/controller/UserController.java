package xyz.aiinirii.postalk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import xyz.aiinirii.postalk.bean.Post;
import xyz.aiinirii.postalk.bean.User;
import xyz.aiinirii.postalk.service.PostService;
import xyz.aiinirii.postalk.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

/**
 * @author AIINIRII
 */
@Controller
public class UserController {

    UserService userService;
    PostService postService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping("/user/findUserView")
    public String toFindUserView() {
        return "/user/findUser";
    }

    @PostMapping("/user/login")
    public ModelAndView checkUserToPostPage(User user, ModelAndView modelAndView, HttpServletRequest request) {
        int res = userService.checkUser(user);
        modelAndView.addObject("checkResult", res);
        if (res == 0) {
            request.getSession().setAttribute("loginUser", user);
            List<Post> postList = postService.findAllPostByUId(user.getId());
            modelAndView.addObject("postList", postList);
            modelAndView.setViewName("user/myPage");
        } else {
            modelAndView.setViewName("/index");
        }
        return modelAndView;
    }

    @GetMapping("/user/{id}")
    public ModelAndView findUserById(@PathVariable("id") Integer id, ModelAndView modelAndView) {
        User user = userService.findUserById(id);
        modelAndView.setViewName("user/list");
        List<User> users = new LinkedList<>();
        users.add(user);
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @GetMapping("/user/")
    public String toRegisterPage() {
        return "user/update";
    }

    @GetMapping("/user/myPage")
    public ModelAndView toMyPage(@SessionAttribute("loginUser") User user, ModelAndView modelAndView) {
        modelAndView.setViewName("user/myPage");
        List<Post> postList = postService.findAllPostByUId(user.getId());
        modelAndView.addObject("postList", postList);
        modelAndView.setViewName("user/myPage");
        return modelAndView;
    }

    @PostMapping("/user/")
    public ModelAndView registerUser(User user, ModelAndView modelAndView) {

        int res = userService.registerUser(user);
        if (res == 0) {
            // success insert, go to login page
            modelAndView.setViewName("/index");
        } else if (res == 1) {
            // the username is used, go back to the register page
            modelAndView.addObject("userR", user);
            modelAndView.setViewName("/user/update");
        }
        return modelAndView;
    }

}