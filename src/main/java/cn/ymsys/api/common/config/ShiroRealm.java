package cn.ymsys.api.common.config;

import cn.ymsys.api.common.authentication.JWTToken;
import cn.ymsys.api.common.authentication.JWTUtil;
import cn.ymsys.api.common.domain.ActiveUser;
import cn.ymsys.api.common.domain.PortalConstant;
import cn.ymsys.api.service.RedisService;
import cn.ymsys.api.common.util.HttpContextUtil;
import cn.ymsys.api.common.util.IPUtil;
import cn.ymsys.api.common.util.PortalUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义实现 ShiroRealm，包含认证和授权两大模块
 *
 * @author MrBird
 */

@Service
public class ShiroRealm extends AuthorizingRealm {
    private static final long EXPIRE_TIME = 3600 * 1000;
    @Autowired
    private RedisService redisService;

//    @Autowired
//    private UserManager userManager;


    @Autowired
    private ObjectMapper mapper;


    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * `
     * 授权模块，获取用户角色和权限
     *
     * @param token token
     * @return AuthorizationInfo 权限信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection token) {
        String username = JWTUtil.getUsername(token.toString());

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//        // 获取用户角色集
//        Set<String> roleSet = userManager.getUserRoles(username);
//        simpleAuthorizationInfo.setRoles(roleSet);
//        // 获取用户权限集
//        Set<String> permissionSet = userManager.getUserPermissions(username);
//        simpleAuthorizationInfo.setStringPermissions(permissionSet);
        return simpleAuthorizationInfo;
    }

    /**
     * 用户认证
     *
     * @param authenticationToken 身份认证 token
     * @return AuthenticationInfo 身份认证信息
     * @throws AuthenticationException 认证相关异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 这里的 token是从 JWTFilter 的 executeLogin 方法传递过来的，已经经过了解密
        String token = (String) authenticationToken.getCredentials();

        // 从 redis里获取这个 token
        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
        String ip = IPUtil.getIpAddr(request);

        String encryptToken = PortalUtil.encryptToken(token);
        String encryptTokenInRedis = null;
        try {
            encryptTokenInRedis = redisService.get(PortalConstant.TOKEN_CACHE_PREFIX + encryptToken + "." + ip);
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
        String username = "";
        // 如果找不到，说明已经失效
        if (StringUtils.isBlank(encryptTokenInRedis)) {
            throw new AuthenticationException("Token已经过期，请重新登陆！");
        } else {
            //延长token生效时间
            username = JWTUtil.getUsername(token);
            ActiveUser activeUser = new ActiveUser();
            activeUser.setUsername(username);
            activeUser.setIp(ip);
            activeUser.setToken(encryptToken);
            activeUser.setLoginAddress(ip);

            try {
                this.redisService.pexpireAt(PortalConstant.ACTIVE_USERNAMES_SET_PREFIX + StringUtils.lowerCase(username), System.currentTimeMillis() + EXPIRE_TIME);
                this.redisService.pexpireAt(PortalConstant.TOKEN_CACHE_PREFIX + encryptToken + "." + ip,
                        System.currentTimeMillis() + EXPIRE_TIME);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (StringUtils.isBlank(username))
            throw new AuthenticationException("Token校验不通过，请重新登陆");

        // 通过用户名查询用户信息
//        User user = userManager.getUser(username);

//        if (user == null)
//            throw new AuthenticationException("用户信息错误，请先登录！");

//        if (User.STATUS_LOCK.equals(user.getStatus()))
//            throw new AuthenticationException("账号已被锁定，请联系管理员！");

//        if (!JWTUtil.verify(token, username, user.getPassword()))
//            throw new AuthenticationException("token校验不通过");
        return new SimpleAuthenticationInfo(token, token, "lambda_portal_shiro_realm");
    }
}
