package cn.ymsys.api.common.util;

import cn.ymsys.api.common.model.Basics;
import cn.ymsys.api.common.model._MappingKit;
import cn.ymsys.api.service.BasicsService;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;

@Component
@Configuration
public class BasicsUtil {
    private static HashMap<String, Basics> ALL_BASICS_PARAMETERS = new HashMap<String, Basics>();
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Autowired
    private BasicsService basicsService;

    private DataSource getDataSource() {
        DruidPlugin druidPlugin = new DruidPlugin(url,
                username, password);
        druidPlugin.start();
        return druidPlugin.getDataSource();
    }

    @PostConstruct
    private void loadBasics() {
        ActiveRecordPlugin arp = new ActiveRecordPlugin(getDataSource());
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
