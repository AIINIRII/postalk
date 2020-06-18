package xyz.aiinirii.postalk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import xyz.aiinirii.postalk.bean.User;
import xyz.aiinirii.postalk.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author AIINIRII
 */
@Controller
public class UserController {

    UserService userService = new UserService();

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/user/findUserView")
    public String toFindUserView() {
        return "/user/findUser";
    }

    @PostMapping("/user/login")
    public ModelAndView checkUser(User user, ModelAndView modelAndView){
        int res = userService.checkUser(user);
        modelAndView.addObject("checkResult", res);
        if (res == 0) {
            modelAndView.setViewName("redirect:/posts/");
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

    @GetMapping("/user")
    public String toRegisterPage(){
        return "user/update";
    }

    @PostMapping("/user")
    public ModelAndView registerUser(User user, ModelAndView modelAndView) {

        int res = userService.registerUser(user);
        if (res == 0) {
            // success insert, go to login page
            modelAndView.setViewName("/index");
        } else if (res == 1){
            // the username is used, go back to the register page
            modelAndView.addObject("userR", user);
            modelAndView.setViewName("/user/update");
        }
        return modelAndView;
    }
}