package cn.ymsys.api.common.request;

import cn.ymsys.api.common.model.BuUTender;

public class TenderRequest extends BuUTender {
    private int page;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
