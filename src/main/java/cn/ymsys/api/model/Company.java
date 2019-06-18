package cn.ymsys.api.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Company {
    private String companyName;
    private String introduction;
    private List<String> pictures = new ArrayList<String>();
    private List<String> produectDetails = new ArrayList<String>();
    private List<String> caseAnalysis = new ArrayList<String>();
}
