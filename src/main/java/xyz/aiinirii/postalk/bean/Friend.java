package xyz.aiinirii.postalk.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author AIINIRII
 */
@Data
public class Friend implements Serializable {

    private User user1;
    private User user2;
    private Date sinceTime;
}