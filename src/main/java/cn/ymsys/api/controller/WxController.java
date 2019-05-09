package cn.ymsys.api.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.ymsys.common.util.Const;
import cn.ymsys.common.util.HttpClientUtils;

@RestController
@RequestMapping("/wx")
public class WxController {
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;

	private final String url = "https://api.weixin.qq.com/sns/jscode2session";

	@RequestMapping("/toLogin")
	public Map<String, Object> doLogin(@RequestParam(required = false) String code) {

		HttpClientUtils httpClientUtils = new HttpClientUtils();
		Map<String, String> parm = new HashMap<>();
		parm.put("appid", Const.WX_APP_ID);
		parm.put("secret", Const.WX_SECRET_KEY);
		parm.put("js_code", code);
		parm.put("grant_type", "authorization_code");

		System.out.println("code:" + code);
		String param = "appid=" + Const.WX_APP_ID + "&secret=" + Const.WX_SECRET_KEY + "&js_code=" + code
				+ "&grant_type=authorization_code";

		Map<String, Object> result = new HashMap<String, Object>();
		int error = 0;
		try {
			String ret = httpClientUtils.sendGet(url, parm);
			System.out.println("拼接: " + ret);
			/*
			 * JSONObject obj = JSONObject.parseObject(ret); String openid =
			 * obj.getString("openid"); String session_key = obj.getString("session_key");
			 * result.put("openid", openid);
			 */
			// System.out.println("session_key:" + session_key);
		} catch (Exception e) {
			e.printStackTrace();
			error = -1;
		}
		result.put("code", error);
		return result;
	}
}
