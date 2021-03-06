package xyz.aiinirii.postalk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xyz.aiinirii.postalk.bean.User;
import xyz.aiinirii.postalk.mapper.UserMapper;

import java.util.Date;
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


    public void deleteUserById(Integer id) {
        userMapper.deleteUserById(id);
    }

    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public User findUserById(Integer id) {
        return userMapper.findUserById(id);
    }

    /**
     * register user
     *
     * @param user the user to be registed
     * @return whether the user is successfully registed
     * 0: success
     * 1: fail due to user name is already be used
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public int registerUser(User user) {
        // check whether the user name is used
        User userByUsername = userMapper.findUserByUsernameExact(user.getUsername());
        if (userByUsername != null) {
            return 1;
        } else {
            // record the time of user register
            user.setRegistrationDate(new Date(System.currentTimeMillis()));
            // add user into the database
            userMapper.insertUser(user);
            return 0;
        }
    }

    /**
     * @param user the user to be checked
     * @return whether the user is legal
     * 0: pass
     * 1: wrong password
     * 2: no such user
     * 3: no login
     */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int checkUser(User user) {
        User checkUser = userMapper.findUserByUsernameExact(user.getUsername());
        if (checkUser == null) {
            return 2;
        } else if (Objects.equals(checkUser.getPassword(), user.getPassword())) {
            user.setRegistrationDate(checkUser.getRegistrationDate());
            user.setSex(checkUser.getSex());
            user.setPhoneNumber(checkUser.getPhoneNumber());
            user.setAge(checkUser.getAge());
            user.setId(checkUser.getId());
            user.setEmail(checkUser.getEmail());
            return 0;
        } else {
            return 1;
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<User> findUserByUsername(String inputSearch) {
        return userMapper.findUserByUsername('%' + inputSearch + '%');
    }
}