package xyz.aiinirii.postalk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xyz.aiinirii.postalk.bean.Post;
import xyz.aiinirii.postalk.bean.User;
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
     * @param user the user
     * @return true if success
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean updatePost(Post post, User user) {
        // check whether the user is the writer of the post
        post.setUser(postMapper.findPostById(post.getId()).getUser());
        if (post.getUser() == user) {
            return postMapper.updatePost(post) == 1;
        }
        return false;
    }

    /**
     * delete the post by id
     *
     * @param id   the id
     * @param user the user
     * @return true if success
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean deletePostById(Integer id, User user) {
        // check whether the user is the writer of the post
        if (postMapper.findPostById(id).getUser() == user) {
            int postDelete = postMapper.deletePostById(id);
            int textDelete = textMapper.deleteTextById(id);
            return postDelete == 1 && textDelete == 1;
        }
        return false;
    }
}