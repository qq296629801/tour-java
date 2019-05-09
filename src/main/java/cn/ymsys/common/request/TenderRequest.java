package cn.ymsys.common.request;

import com.jyd.common.model.BuUTender;

public class TenderRequest extends BuUTender {
    private int page;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
