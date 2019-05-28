package cn.ymsys.api.common.response;

import cn.ymsys.api.common.util.DataUtil;
import cn.ymsys.api.common.util.PagerUtil;
import cn.ymsys.api.common.util.RootModel;

import java.util.Collection;
import java.util.LinkedHashMap;

public class PagerResponse extends LinkedHashMap<String, Object> {

    private static String JSON_KEY_ROWS = "rows";
    private static String JSON_KEY_TOTAL = "total";

    private PagerResponse(Object rows, Long total) {
        this.put(JSON_KEY_ROWS, rows);

        if (DataUtil.isNotNull(total) && total > 0) {
            this.put(JSON_KEY_TOTAL, total);
        }
    }

    public static JsonResponse success(Collection<? extends RootModel> rows) {
        return JsonResponse.success(new PagerResponse(DataUtil.toJSONArray(rows), -1L));
    }

    public static JsonResponse success(Collection<? extends RootModel> rows, PagerUtil pager) {
        return JsonResponse.success(new PagerResponse(DataUtil.toJSONArray(rows), pager.getTotalCount()));
    }

    public static JsonResponse success(Object rows) {
        return JsonResponse.success(new PagerResponse(rows, -1L));
    }

    public static JsonResponse success(Object rows, PagerUtil pager) {
        return JsonResponse.success(new PagerResponse(rows, pager.getTotalCount()));
    }
}
