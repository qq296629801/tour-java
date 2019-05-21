package cn.ymsys.api.dao.impl;

import java.net.URLEncoder;

import org.springframework.stereotype.Component;

import com.baidu.aip.util.Base64Util;

import cn.ymsys.api.dao.BaiduOCR;
import cn.ymsys.api.common.util.AuthService;
import cn.ymsys.api.common.util.FileUtil;
import cn.ymsys.api.common.util.HttpUtil;

@Component("IdcardOCR")
public class IdcardOCR implements BaiduOCR {

    @Override
    public boolean post() {
        System.err.println("id card");
        // 身份证识别url
        String idcardIdentificate = "https://aip.baidubce.com/rest/2.0/ocr/v1/idcard";
        // 本地图片路径
        String filePath = "C:\\Users\\Administrator\\Desktop\\project\\weapp-cheyuanbao-master\\images\\aic.jpg";
        try {
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            // 识别身份证正面id_card_side=front;识别身份证背面id_card_side=back;
            String params = "id_card_side=front&" + URLEncoder.encode("image", "UTF-8") + "="
                    + URLEncoder.encode(imgStr, "UTF-8");
            /**
             * 线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
             */
            String accessToken = AuthService.getAuth();
            String result = HttpUtil.post(idcardIdentificate, accessToken, params);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
