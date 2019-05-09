package cn.ymsys.common.exception;

/**
 * Portal 业务异常
 */
public class PortalException extends RuntimeException {

    public PortalException(String message) {
        super(message);
    }

    public PortalException(String message, Throwable e) {
        super(message, e);
    }
}
