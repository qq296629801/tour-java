package cn.ymsys.api.model;

import cn.ymsys.api.common.util.PagerUtil;
import cn.ymsys.api.model.user.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Trends extends PagerUtil {
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
    private int userId;
    private int status;
    private List<String> imgs = new ArrayList<String>();
}
