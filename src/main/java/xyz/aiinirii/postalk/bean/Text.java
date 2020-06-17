package xyz.aiinirii.postalk.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author AIINIRII
 */
@Data
public abstract class Text implements Serializable {

    private Integer id;
    private String content;
    private Date time;

    private User user;
}