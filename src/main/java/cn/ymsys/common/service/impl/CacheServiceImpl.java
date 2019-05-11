package cn.ymsys.common.service.impl;

import cn.ymsys.api.service.UserService;
import cn.ymsys.common.domain.PortalConstant;
import cn.ymsys.common.model.SysUser;
import cn.ymsys.common.service.CacheService;
import cn.ymsys.common.service.RedisService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.startup.UserConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("cacheService")
public class CacheServiceImpl implements CacheService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public void testConnect() throws Exception {
        this.redisService.exists("test");
    }

    @Override
    public SysUser getUser(String username) throws Exception {
        String userString = this.redisService.get(PortalConstant.USER_CACHE_PREFIX + username.toLowerCase());
        if (StringUtils.isBlank(userString))
            throw new Exception();
        else
            return this.mapper.readValue(userString, SysUser.class);
    }


    @Override
    public UserConfig getUserConfig(String userId) throws Exception {
        String userConfigString = this.redisService.get(PortalConstant.USER_CONFIG_CACHE_PREFIX + userId);
        if (StringUtils.isBlank(userConfigString))
            throw new Exception();
        else
            return this.mapper.readValue(userConfigString, UserConfig.class);
    }

    @Override
    public void saveUser(SysUser user) throws Exception {
        String username = user.getUserName().toLowerCase();
        this.deleteUser(username);
        redisService.set(PortalConstant.USER_CACHE_PREFIX + username, mapper.writeValueAsString(user));
    }

    @Override
    public void saveUser(String username) throws Exception {
        SysUser user = userService.findByName(username);
        this.deleteUser(username);
        redisService.set(PortalConstant.USER_CACHE_PREFIX + username.toLowerCase(), mapper.writeValueAsString(user));
    }

    @Override
    public void saveUserConfigs(String userId) throws Exception {

    }


    @Override
    public void deleteUser(String username) throws Exception {
        username = username.toLowerCase();
        redisService.del(PortalConstant.USER_CACHE_PREFIX + username);
    }

}
