package xyz.aiinirii.postalk.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author AIINIRII
 */
@Data
public class Like implements Serializable {

    private User user;
    private Text text;
}