package xyz.aiinirii.postalk.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author AIINIRII
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Comment extends Text implements Serializable {

    private Text text;
}