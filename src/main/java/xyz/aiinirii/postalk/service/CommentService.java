package xyz.aiinirii.postalk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xyz.aiinirii.postalk.bean.Comment;
import xyz.aiinirii.postalk.bean.User;
import xyz.aiinirii.postalk.mapper.CommentMapper;
import xyz.aiinirii.postalk.mapper.TextMapper;

import java.util.Date;

/**
 * @author AIINIRII
 */
@Service
public class CommentService {

    CommentMapper commentMapper;
    TextMapper textMapper;

    @Autowired
    public void setCommentMapper(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Autowired
    public void setTextMapper(TextMapper textMapper) {
        this.textMapper = textMapper;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void createComment(Integer pid, Comment comment, User user) {
        comment.setUser(user);
        comment.setTime(new Date(System.currentTimeMillis()));
        textMapper.insertText(comment);
        commentMapper.insertComment(pid);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean deleteCommentById(Integer id, User user) throws Exception {
        if (commentMapper.findCommentById(id).getUser().getId().equals(user.getId()) || commentMapper.findCommentById(id).getText().getUser().getId().equals(user.getId())) {
            return commentMapper.deleteCommentById(id) == 1 &&
                    textMapper.deleteTextById(id) == 1;
        } else {
            throw new Exception("the user do not have right to delete the comment");
        }
    }
}