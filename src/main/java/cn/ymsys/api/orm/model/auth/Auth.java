package cn.ymsys.api.orm.model.auth;

import lombok.Data;

import java.util.Date;

@Data
public class Auth {
    private int id;
    private String phone;
    private String address;
    private String gps;
    private String company;
    private String category;
    private AuthType settledType;
    private int settledTypeId;
    private Date intoSettledTime;
    private String businessPic;
    private String logoPic;
    private String legalPersonPic;
}
