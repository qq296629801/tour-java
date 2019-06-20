package cn.ymsys.api.model.user;

import cn.ymsys.api.common.util.PagerUtil;
import cn.ymsys.api.model.user.Experience.WorkExperience;
import cn.ymsys.api.model.user.Experience.educationExperience;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Accessors(chain = true)
public class User extends PagerUtil implements Serializable {
    private int id;
    private String userName;
    private String password;
    private String nickName;
    private String phone;
    private int sex;
    private String avator;
    private int status;
    private boolean display;
    private Date birthday;
    private String company;
    private String deptDuty;
    private String email;
    private String address;
    private String wechat;
    private String qq;
    private String city;
    private String url;
    private String openId;
    private Date createTime;
    private String createUser;
    private Date lastUpdateTime;
    private String lastUpdateUser;
    private List<WorkExperience> workExperiences = new ArrayList<WorkExperience>();
    private List<educationExperience> educationExperiences = new ArrayList<educationExperience>();
}
