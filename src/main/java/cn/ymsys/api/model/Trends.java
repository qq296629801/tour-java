package cn.ymsys.api.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Trends {
    private String id;
    private User user;
    private String address;
    private String name;
    private String con;
    private Date createTime;
    private String time;
    private String createUser;
    private Date lastUpdateTime;
    private String lastUpdateUser;
    private boolean like;
    private List<Photo> imgs = new ArrayList<>();
}
