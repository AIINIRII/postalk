package xyz.aiinirii.postalk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xyz.aiinirii.postalk.bean.Post;
import xyz.aiinirii.postalk.mapper.PostMapper;

import java.util.Date;
import java.util.List;

/**
 * @author AIINIRII
 */
@Service
public class PostService {

    private PostMapper postMapper;

    @Autowired
    public void setPostMapper(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Post> findAllPost(){
        return postMapper.findAllPost();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void createPost(Post post){
        post.setTime(new Date(System.currentTimeMillis()));
        postMapper.insertText(post);
        postMapper.insertPost(post);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Post> findAllPostByUId(Integer id) {
        return postMapper.findAllPostByUId(id);
    }
}