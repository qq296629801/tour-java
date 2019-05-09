package cn.ymsys.common.response;


import com.github.pagehelper.PageInfo;

import java.util.LinkedHashMap;

public class PortalPagerResponse extends LinkedHashMap<String, Object> {

    private static String JSON_KEY_ROWS = "rows";
    private static String JSON_KEY_TOTAL = "total";

    private PortalPagerResponse(Object rows, long total) {
        this.put(JSON_KEY_ROWS, rows);

        if(total > 0) {
            this.put(JSON_KEY_TOTAL, total);
        }
    }

    public static PortalJsonResponse success(PageInfo<?> pageInfo) {
        return PortalJsonResponse.success(new PortalPagerResponse(pageInfo.getList(), pageInfo.getTotal()));
    }

    public static PortalJsonResponse success(Object rows, long total) {
        return PortalJsonResponse.success(new PortalPagerResponse(rows, total));
    }
}
