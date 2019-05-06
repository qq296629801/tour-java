package cn.ymsys.util;

import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class DataUtil {

	private static Pattern PATTERN_REPLACE_BLANK = Pattern.compile("\\s*|\t|\r");

	public static boolean isNull(Object obj) {
		return obj == null;
	}

	public static boolean isNotNull(Object obj) {
		return obj != null;
	}

	public static String replaceBlank(String str) {

		String dest = null;
		if (str != null) {
			Matcher m = PATTERN_REPLACE_BLANK.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

	public static String toJSONString(Object object) {
		return JSON.toJSONString(object, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat);
	}

	public static String toPrettyJSONString(JSON json) {
		return JSON.toJSONString(json, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
				SerializerFeature.WriteDateUseDateFormat);
	}

	public static String toPrettyJSONString(RootModel model) {
		return JSON.toJSONString(DataUtil.toJSONObject(model), SerializerFeature.PrettyFormat,
				SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat);
	}

	public static String toPrettyJSONString(Collection<? extends RootModel> models) {
		return JSON.toJSONString(DataUtil.toJSONArray(models), SerializerFeature.PrettyFormat,
				SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat);
	}

	public static JSONObject toJSONObject(RootModel model) {
		return DataUtil.isNotNull(model) ? model.toJSON() : null;
	}

	public static <M extends Map<K, V>, K, V extends RootModel> JSONObject toJSONObject(M models) {
		if (DataUtil.isEmpty(models))
			return null;

		JSONObject jsonModels = new JSONObject(models.size(), true);
		for (Map.Entry<K, V> entry : models.entrySet()) {
			jsonModels.put(String.valueOf(entry.getKey()), entry.getValue().toJSON());
		}

		return jsonModels;
	}

	public static JSONArray toJSONArray(Collection<? extends RootModel> models) {
		if (DataUtil.isNull(models))
			return null;

		JSONArray jsonArray = new JSONArray();
		for (RootModel model : models) {
			jsonArray.add(model.toJSON());
		}
		return jsonArray;
	}

	public static boolean isEmpty(Collection<? extends Object> list) {
		return (DataUtil.isNotNull(list) ? list.isEmpty() : true);
	}

	public static boolean isNotEmpty(Collection<? extends Object> list) {
		return !DataUtil.isEmpty(list);
	}

	public static boolean isEmpty(Map map) {
		return (DataUtil.isNotNull(map) ? map.isEmpty() : true);
	}

	public static boolean isNotEmpty(Map map) {
		return !DataUtil.isEmpty(map);
	}

	public static String format(String message, RootModel m1) {
		return String.format("%s\n%s", message, DataUtil.toPrettyJSONString(m1));
	}

	public static String format(String message, RootModel m1, RootModel m2) {
		return String.format("%s\n%s\n%s", message, DataUtil.toPrettyJSONString(m1), DataUtil.toPrettyJSONString(m2));
	}

	public static String likeKeyword(String keyword) {
		return String.format("%%%s%%", keyword);
	}

	public static String toString(Object object) {
		if (DataUtil.isNull(object))
			return null;

		return object.toString();
	}

}
