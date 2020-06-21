package xyz.aiinirii.postalk.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author AIINIRII
 */
@Data
public abstract class Text implements Serializable {

    private Integer id;
    private String content;
    private Date time;
    private boolean anonymous = false;

    private User user;
    private List<Like> likes;
    private List<Comment> comments;
}