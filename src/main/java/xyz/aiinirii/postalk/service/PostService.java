package xyz.aiinirii.postalk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xyz.aiinirii.postalk.bean.Post;
import xyz.aiinirii.postalk.mapper.PostMapper;
import xyz.aiinirii.postalk.mapper.TextMapper;

import java.util.Date;
import java.util.List;

/**
 * @author AIINIRII
 */
@Service
public class PostService {

    private PostMapper postMapper;
    private TextMapper textMapper;

    @Autowired
    public void setTextMapper(TextMapper textMapper) {
        this.textMapper = textMapper;
    }

    @Autowired
    public void setPostMapper(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Post> findAllPost() {
        return postMapper.findAllPost();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void createPost(Post post) {
        post.setTime(new Date(System.currentTimeMillis()));
        textMapper.insertText(post);
        postMapper.insertPost(post);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Post> findAllPostByUId(Integer id) {
        return postMapper.findAllPostByUId(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Post findPostById(Integer id) {
        return postMapper.findPostById(id);
    }

    /**
     * update the post
     *
     * @param post the post
     * @return true if success
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean updatePost(Post post) {
        return postMapper.updatePost(post) == 1;
    }

    /**
     * delete the post by id
     *
     * @param id the id
     * @return true if success
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean deletePostById(Integer id) {
        int postDelete = postMapper.deletePostById(id);
        int textDelete = textMapper.deleteTextById(id);
        return postDelete == 1 && textDelete == 1;
    }
}