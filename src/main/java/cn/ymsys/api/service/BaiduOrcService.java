package cn.ymsys.api.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ymsys.api.dao.BaiduOCR;

/**
 * 策略模式
 *
 * @author mjy
 */
@Service
public class BaiduOrcService {
    @Autowired
    private final Map<String, BaiduOCR> baiduMap = new ConcurrentHashMap<>();

    public boolean get(String api) {
        return baiduMap.get(api).push();
    }
}
