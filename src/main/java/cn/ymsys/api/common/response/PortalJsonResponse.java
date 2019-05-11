package cn.ymsys.api.common.response;

import cn.ymsys.api.common.exception.LimitAccessException;
import cn.ymsys.api.common.exception.LoginException;
import org.apache.shiro.authc.AuthenticationException;

import java.io.Serializable;
import java.util.LinkedHashMap;

public class PortalJsonResponse extends LinkedHashMap<String, Object> implements Serializable {

    private static final String JSON_KEY_SUCCESS = "success";
    private static final String JSON_KEY_DATA = "data";
    private static final String JSON_KEY_ERROR_CODE = "errorCode";
    private static final String JSON_KEY_ERROR_MESSAGE = "errorMessage";
    private static final String JSON_KEY_ERROR_HINT = "errorHint";

    private PortalJsonResponse() {
        this.put(JSON_KEY_SUCCESS, true);
    }

    private PortalJsonResponse(Object data) {
        this.put(JSON_KEY_SUCCESS, true);
        this.put(JSON_KEY_DATA, data);
    }

    private PortalJsonResponse(PortalErrorCode error) {
        this.put(JSON_KEY_SUCCESS, false);
        this.put(JSON_KEY_ERROR_CODE, error.getCode());
        this.put(JSON_KEY_ERROR_MESSAGE, error.responseMessage());
        this.put(JSON_KEY_ERROR_HINT, error.getHint());
    }

    private PortalJsonResponse(PortalErrorCode error, String message, String hint) {
        this.put(JSON_KEY_SUCCESS, false);
        this.put(JSON_KEY_ERROR_CODE, error.getCode());
        this.put(JSON_KEY_ERROR_MESSAGE, message);
        this.put(JSON_KEY_ERROR_HINT, hint);
    }

    public static PortalJsonResponse success() {
        return new PortalJsonResponse();
    }

    public static PortalJsonResponse success(Object data) {
        return new PortalJsonResponse(data);
    }

    public static PortalJsonResponse error(PortalErrorCode error) {
        return new PortalJsonResponse(error);
    }

    public static PortalJsonResponse error(PortalErrorCode error, String message) {
        return new PortalJsonResponse(error, error.responseMessage(message), message);
    }

    public static PortalJsonResponse error(PortalErrorCode error, Throwable e) {
        return error(error, e.getMessage());
    }

    public static PortalJsonResponse error$authentication(AuthenticationException e) {
        return error(PortalErrorCode.ERROR_AUTHENTICATION_FAILED, e.getMessage());
    }

    public static PortalJsonResponse error$login(LoginException e) {
        return error(PortalErrorCode.ERROR_USER_LOGIN_FAILED, e.getMessage());
    }

    public static PortalJsonResponse error$limit(LimitAccessException e) {
        return error(PortalErrorCode.ERROR_REQUEST_TOO_FREQUENT, e.getMessage());
    }
}
