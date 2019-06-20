package cn.ymsys.api.model.live;

import lombok.Data;

import java.util.Date;

@Data
public class Live {
    private int id;
    private String liveName;
    private Date meetTime;
    private String meetAddress;
    private String meetScale;
    private String MasterCompany;
    private String pic;
}
