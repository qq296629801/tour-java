package cn.ymsys.api.common.response;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import cn.ymsys.api.common.util.DataUtil;
import cn.ymsys.api.common.util.OwnException;
import cn.ymsys.api.common.util.RootModel;

public class JsonResponse extends LinkedHashMap<String, Object> implements Serializable {
    private static final String JSON_KEY_SUCCESS = "success";
    private static final String JSON_KEY_DATA = "data";
    private static final String JSON_KEY_ERROR_CODE = "errorCode";
    private static final String JSON_KEY_ERROR_MESSAGE = "errorMessage";
    private static final String JSON_KEY_ERROR_HINT = "errorHint";

    private JsonResponse(Object data) {
        this.put(JSON_KEY_SUCCESS, true);
        this.put(JSON_KEY_DATA, data);
    }

    private JsonResponse(OwnException e) {
        this.put(JSON_KEY_SUCCESS, false);
        this.put(JSON_KEY_ERROR_CODE, e.getCode());
        this.put(JSON_KEY_ERROR_MESSAGE, e.getMessage());
        this.put(JSON_KEY_ERROR_HINT, e.getHint());
    }

    public static JsonResponse success(Object data) {
        if (data instanceof RootModel) {
            return success(((RootModel) data));
        }

        return new JsonResponse(data);
    }

    public static <M extends Map<K, V>, K, V extends RootModel> JsonResponse build(M data) {
        return new JsonResponse(DataUtil.toJSONObject(data));
    }

    public static JsonResponse success(RootModel data) {
        return new JsonResponse(data.toJSON());
    }

    public static JsonResponse success(OwnException e) {
        return new JsonResponse(e);
    }
}