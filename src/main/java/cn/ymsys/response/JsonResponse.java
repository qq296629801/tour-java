package cn.ymsys.response;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import cn.ymsys.util.DataUtil;
import cn.ymsys.util.OwnException;
import cn.ymsys.util.RootModel;
import com.terran4j.commons.api2doc.annotations.ApiComment;

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

    public static JsonResponse build(Object data) {
        if (data instanceof RootModel) {
            return build(((RootModel) data));
        }

        return new JsonResponse(data);
    }

    public static <M extends Map<K, V>, K, V extends RootModel> JsonResponse build(M data) {
        return new JsonResponse(DataUtil.toJSONObject(data));
    }

    public static JsonResponse build(RootModel data) {
        return new JsonResponse(data.toJSON());
    }

    public static JsonResponse build(OwnException e) {
        return new JsonResponse(e);
    }
}