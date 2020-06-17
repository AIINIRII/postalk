package xyz.aiinirii.postalk.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author AIINIRII
 */
@Data
public class User implements Serializable {

    private Integer id;
    private String username;
    private String password;
    private String sex;
    private Integer age;
    private String phoneNumber;
    private String email;
    private Date registrationDate;
}