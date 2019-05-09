package cn.ymsys.common.request;

import com.jyd.common.model.BuUCompany;
import com.terran4j.commons.api2doc.annotations.ApiComment;

public class CompanyRequest extends BuUCompany {
    @ApiComment(value = "分页", sample = "1")
    private int page;
    @ApiComment(value = "关键字", sample = "1")
    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

}
