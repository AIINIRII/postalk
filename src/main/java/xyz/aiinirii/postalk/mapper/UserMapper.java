package xyz.aiinirii.postalk.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import xyz.aiinirii.postalk.bean.User;

import java.util.List;

/**
 * @author AIINIRII
 */
@Mapper
@Repository
public interface UserMapper {

    @Select("select * from user")
    List<User> findAllUser();

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Select("select * from user where id=#{id}")
    User findUserById(Integer id);

    @Select("select * from user where username=#{username}")
    User findUserByUsernameExact(String username);

    @Select("delete from user where id=#{id}")
    int deleteUserById(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into user(username, password, sex, age, phone_number, email, registration_date) values (#{username}, #{password}, #{sex}, #{age}, #{phoneNumber}, #{email}, #{registrationDate})")
    void insertUser(User user);

    @Insert("update user set username=#{username}, password=#{password}, sex=#{sex}, age=#{age}, phone_number=#{phoneNumber}, email=#{email}, registration_date=#{registration_date} where id=#{id}")
    int updateUser(User user);

    @Select("select * from user where username like #{inputSearch}")
    List<User> findUserByUsername(String inputSearch);
}