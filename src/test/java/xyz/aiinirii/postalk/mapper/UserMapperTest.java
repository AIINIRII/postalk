package xyz.aiinirii.postalk.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.aiinirii.postalk.bean.User;

import java.sql.Date;

@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void findAllUser() {

        System.out.println("userMapper.findAllUser() = " + userMapper.findAllUser());
    }

    @Test
    void findUserById() {

        System.out.println("userMapper.findUserById(1) = " + userMapper.findUserById(1));
    }

    @Test
    void deleteUserById() {
    }

    @Test
    void insertUser() {

        User user = new User();
        user.setUsername("AIINIRII");
        user.setEmail("aiinirii@163.com");
        user.setPassword("aiinirii");
        user.setPhoneNumber("13088208820");
        user.setRegistrationDate(new Date(2020, 6, 17));
        user.setAge(21);
        user.setSex("M");

        userMapper.insertUser(user);
    }

    @Test
    void updateUser() {
    }
}