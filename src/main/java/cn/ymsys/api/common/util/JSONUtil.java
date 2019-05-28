package cn.ymsys.api.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.Collection;
import java.util.Map;

public class JSONUtil {

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean isNotNull(Object obj) {
        return obj != null;
    }

    public static String toJSONString(Object object) {
        return JSON.toJSONString(object, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat);
    }

    public static String toPrettyJSONString(JSON json) {
        return JSON.toJSONString(json, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat);
    }

    public static String toPrettyJSONString(RootModel model) {
        return JSON.toJSONString(JSONUtil.toJSONObject(model), SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat);
    }

    public static String toPrettyJSONString(Collection<? extends RootModel> models) {
        return JSON.toJSONString(JSONUtil.toJSONArray(models), SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat);
    }

    public static JSONObject toJSONObject(RootModel model) {
        return JSONUtil.isNotNull(model) ? model.toJSON() : null;
    }

    public static <M extends Map<K, V>, K, V extends RootModel> JSONObject toJSONObject(M models) {
        if (JSONUtil.isEmpty(models))
            return null;

        JSONObject jsonModels = new JSONObject(models.size(), true);
        for (Map.Entry<K, V> entry : models.entrySet()) {
            jsonModels.put(String.valueOf(entry.getKey()), entry.getValue().toJSON());
        }

        return jsonModels;
    }

    public static JSONArray toJSONArray(Collection<? extends RootModel> models) {
        if (JSONUtil.isNull(models))
            return null;

        JSONArray jsonArray = new JSONArray();
        for (RootModel model : models) {
            jsonArray.add(model.toJSON());
        }
        return jsonArray;
    }

    public static boolean isEmpty(Collection<? extends Object> list) {
        return (JSONUtil.isNotNull(list) ? list.isEmpty() : true);
    }

    public static boolean isNotEmpty(Collection<? extends Object> list) {
        return !JSONUtil.isEmpty(list);
    }

    public static boolean isEmpty(Map map) {
        return (JSONUtil.isNotNull(map) ? map.isEmpty() : true);
    }

    public static boolean isNotEmpty(Map map) {
        return !JSONUtil.isEmpty(map);
    }

    public static String format(String message, RootModel m1) {
        return String.format("%s\n%s", message, JSONUtil.toPrettyJSONString(m1));
    }

    public static String format(String message, RootModel m1, RootModel m2) {
        return String.format("%s\n%s\n%s", message, JSONUtil.toPrettyJSONString(m1), JSONUtil.toPrettyJSONString(m2));
    }
}
