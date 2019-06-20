package cn.ymsys.api.model.into;

import lombok.Data;

import java.util.Date;

@Data
public class Settled {
    private int id;
    private String phone;
    private String address;
    private String gps;
    private String company;
    private String category;
    private SettledType settledType;
    private int settledTypeId;
    private Date intoSettledTime;
    private String businessPic;
    private String logoPic;
    private String legalPersonPic;
}
