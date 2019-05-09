package cn.ymsys.common.authentication;

import cn.ymsys.api.service.RedisService;
import cn.ymsys.common.exception.LimitAccessException;
import cn.ymsys.common.properties.LambdaPortalProperties;
import cn.ymsys.common.response.PortalErrorCode;
import cn.ymsys.common.response.PortalJsonResponse;
import cn.ymsys.common.util.IPUtil;
import cn.ymsys.common.util.PortalUtil;
import cn.ymsys.common.util.SpringContextUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class JWTFilter extends BasicHttpAuthenticationFilter {
    private static final long ACTION_NUMBER = 1;
    private static final long EXPIRATION_DATE = 200;
    private static final String FILTER_CONDITION = "limit_";
    private static final String TOKEN = "Authentication";
    private static final long EXPIRE_TIME = 3600 * 1000;
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        LambdaPortalProperties lambdaPortalProperties = SpringContextUtil.getBean(LambdaPortalProperties.class);
        String[] anonUrl = StringUtils.splitByWholeSeparatorPreserveAllTokens(lambdaPortalProperties.getShiro().getAnonUrl(), ",");
        for (String u : anonUrl) {
            if (pathMatcher.match(u, httpServletRequest.getRequestURI()))
                return true;
        }
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginAttempt(request, response)) {
            try {
                return this.executeLogin(request, response);
            } catch (AuthenticationException e) {
                this.responseDirectly(request, response, PortalJsonResponse.error$authentication(e));
                return false;
            }
        }
        this.responseDirectly(request, response, PortalJsonResponse.error(PortalErrorCode.ERROR_UNAUTHENTICATED));
        return false;
    }


    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader(TOKEN);
        return token != null;
    }


    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws AuthenticationException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader(TOKEN);
        JWTToken jwtToken = new JWTToken(PortalUtil.decryptToken(token));
        try {
            getSubject(request, response).login(jwtToken);
        } catch (AuthenticationException e) {
            log.error("用户登录认证失败", e);
            throw e;
        }
        try {
            limitAction(request, JWTUtil.getUsername((String) jwtToken.getCredentials()));
        } catch (LimitAccessException e) {
            log.error("接口限制访问", e);
            this.responseDirectly(request, response, PortalJsonResponse.error$limit(e));
            return false;
        }
        return true;
    }

    private void limitAction(ServletRequest request, String USERNAME) throws LimitAccessException {
        HttpServletRequest req = (HttpServletRequest) request;
        String URL = req.getRequestURI();
        String METHOD = req.getMethod();
        if (contain(URL)) {
            RedisService redisService = SpringContextUtil.getBean(RedisService.class);
            String IP = IPUtil.getIpAddr(req);
            ImmutableList<String> keys = ImmutableList.of(StringUtils.join(FILTER_CONDITION, USERNAME, URL, IP, METHOD));
            Number count = redisService.execute(keys, ACTION_NUMBER, EXPIRATION_DATE);
            if (count == null || count.intValue() > ACTION_NUMBER) {
                throw new LimitAccessException("操作太过频繁");
            }
        }
    }

    private boolean contain(String url) {
        String[] words = {"find", "view", "look", "get", "see", "query", "watch"};
        for (String word : words) {
            if (url.indexOf(word) != -1 ||
                    url.indexOf(StringUtils.upperCase(word)) != -1 ||
                    url.indexOf(capitalized(word)) != -1) {
                return false;
            }
        }
        return true;
    }

    private String capitalized(String str) {
        return str.substring(1) + (str.charAt(0) - 32);
    }

    @Override
    protected String getAuthzHeader(ServletRequest request) {
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        return httpRequest.getHeader(JWTFilter.TOKEN);
    }

    /**
     * 无需转发，直接返回Response信息
     */
    private void responseDirectly(ServletRequest req, ServletResponse resp, PortalJsonResponse message) {
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
        httpServletResponse.setStatus(HttpStatus.OK.value());
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = httpServletResponse.getWriter();
            out.append(JSONObject.toJSONString(message));
        } catch (IOException e) {
            log.error("发生IO异常错误：{}", e.getMessage());
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个 option请求，这里我们给 option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }
}
