package cn.ymsys.api.model;

import cn.ymsys.api.common.util.PagerUtil;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class User extends PagerUtil implements Serializable {
    private String id;
    private String userName;
    private String password;
    private String nickName;
    private String phone;
    private int sex;
    private String avator;
    private int roleId;
    private int status;
    private String address;
    private String url;
    private String openId;
    private Date createTime;
    private String createUser;
    private Date lastUpdateTime;
    private String lastUpdateUser;
}
