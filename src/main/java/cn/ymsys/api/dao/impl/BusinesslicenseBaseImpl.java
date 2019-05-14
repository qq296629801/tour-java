package cn.ymsys.api.dao.impl;

import java.util.HashMap;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.baidu.aip.ocr.AipOcr;

import cn.ymsys.api.dao.BaiduOrcBase;
import cn.ymsys.api.common.util.Const;

@Component("BusinesslicenseDaoImpl")
public class BusinesslicenseBaseImpl implements BaiduOrcBase {

	@Override
	public boolean send() {
		AipOcr client = new AipOcr(Const.APP_ID, Const.API_KEY, Const.SECRET_KEY);

		System.err.println("business license");
		// 传入可选参数调用接口
		HashMap<String, String> options = new HashMap<String, String>();

		// 参数为本地路径
		String image = "C:\\Users\\Administrator\\Desktop\\project\\weapp-cheyuanbao-master\\images\\pic.jpg";
		JSONObject res = client.businessLicense(image, options);
		System.out.println(res.toString(2));

		// 参数为二进制数组
//		byte[] file = readFile("test.jpg");
//		res = client.businessLicense(file, options);
//		System.out.println(res.toString(2));
		return false;
	}

	public byte[] readFile(String path) {
		return null;
	}

}
