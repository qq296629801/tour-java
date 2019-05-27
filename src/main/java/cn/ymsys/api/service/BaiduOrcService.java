package cn.ymsys.api.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ymsys.api.common.baidu.BaiduOCR;

/**
 * @author mjy
 */
@Service
public class BaiduOrcService {
    @Autowired
    private final Map<String, BaiduOCR> map = new ConcurrentHashMap<>();

    public boolean handleEvent(String api) {
        return map.get(api).post();
    }


}
