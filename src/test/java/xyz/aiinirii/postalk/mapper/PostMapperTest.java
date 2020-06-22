package xyz.aiinirii.postalk.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.aiinirii.postalk.bean.Post;
import xyz.aiinirii.postalk.bean.User;

import java.util.Date;

@SpringBootTest
class PostMapperTest {

    private PostMapper postMapper;
    private UserMapper userMapper;
    private TextMapper textMapper;

    @Autowired
    public void setPostMapper(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Test
    void findAllPost() {
        for (Post post : postMapper.findAllPost()) {
            System.out.println("post = " + post);
        }
    }

    @Test
    void insertPost() {
        User user = userMapper.findUserByUsernameExact("AIINIRII");
        Post post = new Post();
        post.setTime(new Date(System.currentTimeMillis()));
        post.setUser(user);
        post.setAnonymous(true);
        post.setContent("Hello World! This is the second test!");
        textMapper.insertText(post);
        postMapper.insertPost(post);
        System.out.println("post = " + post);
    }
}