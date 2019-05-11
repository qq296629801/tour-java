package cn.ymsys.api.common.exception;

/**
 * 登录异常
 */
public class LoginException extends RuntimeException {

    public LoginException(String message) {
        super(message);
    }
}
