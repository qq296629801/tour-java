package cn.ymsys.api.model;

import lombok.Data;

import java.util.Date;

@Data
public class Photo {
    private String id;
    private String url;
    private String describe;
    private Date createTime;
    private String createUser;
    private Date lastUpdateTime;
    private String lastUpdateUser;
}
