package cn.ymsys.api.common.util;

import cn.ymsys.api.model.Basic;
import cn.ymsys.api.repository.BasicRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;

@Component
@Configuration
public class BasicsUtil {
    private static HashMap<String, Basic> ALL_BASICS_PARAMETERS = new HashMap<>();
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Autowired
    private BasicRepository basicRepository;


    @PostConstruct
    private void load() {
        ALL_BASICS_PARAMETERS.clear();
        List<Basic> sysParameters = basicRepository.findAll();
        if (DataUtil.isNotEmpty(sysParameters)) {
            for (Basic sysParameter : sysParameters) {
                if (StringUtils.isBlank(sysParameter.getKey()))
                    continue;
                ALL_BASICS_PARAMETERS.put(sysParameter.getKey(), sysParameter);
            }
        }
    }

    public Basic findByKey(String key) {
        if (StringUtils.isNotBlank(key)) {
            Basic param = ALL_BASICS_PARAMETERS.get(key);
            if (DataUtil.isNotNull(param))
                return param;
        }
        return null;
    }

}
