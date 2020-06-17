package xyz.aiinirii.postalk.bean;

import lombok.Data;

import javax.print.DocFlavor;
import java.io.Serializable;
import java.util.Date;

/**
 * @author AIINIRII
 */
@Data
public class Conversation implements Serializable {

    private Integer id;
    private User toUser;
    private User fromUser;
    private Integer conversationId;
    private Date time;
    private String content;
    private boolean read;
}