package cn.ymsys.api.common.util;

import cn.ymsys.api.common.model.Basics;
import cn.ymsys.api.common.model._MappingKit;
import cn.ymsys.api.service.BasicsService;
import cn.ymsys.app.JFinalDemoGenerator;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;

@Component
public class BasicsUtil {
    private static HashMap<String, Basics> ALL_BASICS_PARAMETERS = new HashMap<String, Basics>();

    @Autowired
    private BasicsService basicsService;

    @PostConstruct
    private void loadBasics() {
        ActiveRecordPlugin arp = new ActiveRecordPlugin(JFinalDemoGenerator.getDataSource());
        _MappingKit.mapping(arp);
        arp.start();

        ALL_BASICS_PARAMETERS.clear();
        List<Basics> sysParameters = basicsService.findAll();
        if (DataUtil.isNotEmpty(sysParameters)) {
            for (Basics sysParameter : sysParameters) {
                if (StringUtils.isBlank(sysParameter.getKeyy()))
                    continue;
                ALL_BASICS_PARAMETERS.put(sysParameter.getKeyy(), sysParameter);
            }
        }
    }

    public static Basics find(String key) {
        if (StringUtils.isNotBlank(key)) {
            Basics param = ALL_BASICS_PARAMETERS.get(key);
            if (DataUtil.isNotNull(param))
                return param;
        }
        return null;
    }

}
