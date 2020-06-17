package xyz.aiinirii.postalk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import xyz.aiinirii.postalk.bean.User;
import xyz.aiinirii.postalk.service.UserService;

import java.util.LinkedList;
import java.util.List;

/**
 * @author AIINIRII
 */
@Controller
@RequestMapping("/user")
public class UserController {

    UserService userService = new UserService();

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/findUserView")
    public String toFindUserView() {
        return "/user/findUser";
    }

    @PostMapping("/login")
    public ModelAndView checkUser(User user, ModelAndView modelAndView){
        int res = userService.checkUser(user);
        modelAndView.addObject("checkResult", res);
        if (res == 0) {
            modelAndView.setViewName("/post/index");
        } else {
            modelAndView.setViewName("/index");
        }
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView findUserById(@PathVariable("id") Integer id, ModelAndView modelAndView) {
        User user = userService.findUserById(id);
        modelAndView.setViewName("user/list");
        List<User> users = new LinkedList<>();
        users.add(user);
        modelAndView.addObject("users", users);
        return modelAndView;
    }
}