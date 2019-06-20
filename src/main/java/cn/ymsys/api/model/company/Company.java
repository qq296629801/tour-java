package cn.ymsys.api.model.company;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Company {
    private int id;
    private String name;
    private String info;
    private String logo;
    private String address;
    private String gps;
    private int score;
    private int viewNum;
    private int userId;
    private List<String> pictures = new ArrayList<String>();
    private List<String> produectDetails = new ArrayList<String>();
    private List<String> caseAnalysis = new ArrayList<String>();
}
