package xyz.aiinirii.postalk.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author AIINIRII
 */
@Data
public class Friend implements Serializable {

    private User user1;
    private User user2;
    private Data sinceTime;
}