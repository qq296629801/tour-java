package cn.ymsys.api.model;

import lombok.Data;

@Data
public class User {
    private int id;
    private String userName;
    private String password;
    private String nickName;
}
