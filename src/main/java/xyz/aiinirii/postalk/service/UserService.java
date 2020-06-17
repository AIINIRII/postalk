package xyz.aiinirii.postalk.service;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.aiinirii.postalk.bean.User;
import xyz.aiinirii.postalk.mapper.UserMapper;

import java.util.List;
import java.util.Objects;

/**
 * @author AIINIRII
 */
@Service
public class UserService {
    
    UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User registerUser(User user) {
        userMapper.insertUser(user);
        return user;
    }

    public void deleteUserById(Integer id) {
        userMapper.deleteUserById(id);
    }

    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    public User findUserById(Integer id) {
        return userMapper.findUserById(id);
    }

    /**
     *
     * @param   user the user to be checked
     * @return  whether the user is legal
     *          0: pass
     *          1: wrong password
     *          2: no such user
     */
    public int checkUser(User user) {
        User checkUser = userMapper.findUserByUsername(user.getUsername());
        if (checkUser == null) {
            return 2;
        } else if (Objects.equals(checkUser.getPassword(), user.getPassword())) {
            return 0;
        } else {
            return 1;
        }
    }
}