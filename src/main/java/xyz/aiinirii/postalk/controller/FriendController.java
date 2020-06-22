package xyz.aiinirii.postalk.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import xyz.aiinirii.postalk.bean.User;
import xyz.aiinirii.postalk.service.FriendService;
import xyz.aiinirii.postalk.service.UserService;

import java.util.LinkedList;
import java.util.List;

/**
 * @author AIINIRII
 */
@Controller
public class FriendController {

    private UserService userService;
    private FriendService friendService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setFriendService(FriendService friendService) {
        this.friendService = friendService;
    }

    @GetMapping("/friends/")
    public ModelAndView toMyFriendPage(ModelAndView modelAndView,
                                       @SessionAttribute("loginUser") User user) {
        List<User> friends = friendService.findAllFriendsByUId(user.getId());
        modelAndView.addObject("friends", friends);
        modelAndView.setViewName("friend/myFriend");
        return modelAndView;
    }

    @GetMapping("/friend/")
    public ModelAndView toSearchFriendPage(ModelAndView modelAndView) {
        modelAndView.setViewName("friend/search");
        return modelAndView;
    }

    @GetMapping("/friend/search/")
    public ModelAndView findFriend(ModelAndView modelAndView,
                                   @Param("findWay") String findWay,
                                   @Param("inputSearch") String inputSearch,
                                   @SessionAttribute("loginUser") User user) throws Exception {
        List<User> friends = new LinkedList<>();
        if (findWay.equals("id") && !user.getId().equals(Integer.valueOf(inputSearch))) {
            friends.add(userService.findUserById(Integer.valueOf(inputSearch)));
        } else if (findWay.equals("username") && !user.getUsername().equals(inputSearch)) {
            friends = userService.findUserByUsername(inputSearch);
        } else {
            throw new Exception("Wrong with the findWay");
        }
        modelAndView.setViewName("/friend/result");
        modelAndView.addObject("friends", friends);
        return modelAndView;
    }

    @PostMapping("/friend/")
    public ModelAndView addFriend(ModelAndView modelAndView,
                                  Integer id,
                                  @SessionAttribute("loginUser") User user) {
        friendService.addFriend(user.getId(), id);
        List<User> friends = friendService.findAllFriendsByUId(user.getId());
        modelAndView.addObject("friends", friends);
        modelAndView.setViewName("friend/myFriend");
        return modelAndView;
    }

    @DeleteMapping("/friend/{id}")
    public ModelAndView deleteFriend(ModelAndView modelAndView,
                                     @PathVariable("id") Integer id,
                                     @SessionAttribute("loginUser") User user) throws Exception {

        if (!friendService.deleteFriendById(user.getId(), id)) {
            throw new Exception("the delete operation failed");
        } else {
            List<User> friends = friendService.findAllFriendsByUId(user.getId());
            modelAndView.addObject("friends", friends);
            modelAndView.setViewName("friend/myFriend");
        }
        return modelAndView;
    }
}