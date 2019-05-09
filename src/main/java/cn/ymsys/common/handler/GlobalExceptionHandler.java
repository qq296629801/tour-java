package cn.ymsys.common.handler;

import cn.ymsys.common.exception.LimitAccessException;
import cn.ymsys.common.exception.LoginException;
import cn.ymsys.common.exception.PortalException;
import cn.ymsys.common.response.PortalErrorCode;
import cn.ymsys.common.response.PortalJsonResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.List;
import java.util.Set;

@Slf4j
//@RestControllerAdvice
//@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public PortalJsonResponse handleException(Exception e) {
        log.error("系统内部错误：", e);
        return PortalJsonResponse.error(PortalErrorCode.ERROR_SYSTEM_INTERNAL, e);
    }

    @ExceptionHandler(PortalException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public PortalJsonResponse handleRequestFailedPortalException(PortalException e) {
        log.info("Portal业务请求失败：{}", e);
        return PortalJsonResponse.error(PortalErrorCode.ERROR_PORTAL_REQUEST_FAILED, e);
    }

    @ExceptionHandler(LoginException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public PortalJsonResponse handleLoginException(LoginException e) {
        log.debug("Portal业务请求失败：{}", e);
        return PortalJsonResponse.error$login(e);
    }

    /**
     * 统一处理请求参数校验(实体对象传参)
     *
     * @param e BindException
     * @return PortalJsonResponse
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public PortalJsonResponse validExceptionHandler(BindException e) {
        StringBuilder message = new StringBuilder();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        for (FieldError error : fieldErrors) {
            message.append(error.getField()).append(error.getDefaultMessage()).append(";");
        }
        return PortalJsonResponse.error(PortalErrorCode.ERROR_REQUEST_PARAMETER_INVALID, message.toString());
    }

    /**
     * 统一处理请求参数校验(普通传参)
     *
     * @param e ConstraintViolationException
     * @return PortalJsonResponse
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public PortalJsonResponse handleConstraintViolationException(ConstraintViolationException e) {
        StringBuilder message = new StringBuilder();
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            Path path = violation.getPropertyPath();
            String[] pathArr = StringUtils.splitByWholeSeparatorPreserveAllTokens(path.toString(), ".");
            message.append(pathArr[1]).append(violation.getMessage()).append(";");
        }
        return PortalJsonResponse.error(PortalErrorCode.ERROR_REQUEST_PARAMETER_INVALID, message.toString());
    }

    @ExceptionHandler(LimitAccessException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public PortalJsonResponse handleLimitAccessException(LimitAccessException e) {
        log.warn(e.getMessage());
        return PortalJsonResponse.error(PortalErrorCode.ERROR_INTERFACE_LIMIT_ACCESS);
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public PortalJsonResponse handleUnauthorizedException(UnauthorizedException e) {
        log.debug("无访问权限，{}", e.getMessage());
        return PortalJsonResponse.error(PortalErrorCode.ERROR_ROLES_UNAUTHORIZED);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ExceptionHandler(UnauthenticatedException.class)
    public PortalJsonResponse handleUnauthenticatedException(UnauthenticatedException e) {
        log.debug("用户未认证{}", e.getMessage());
        return PortalJsonResponse.error(PortalErrorCode.ERROR_UNAUTHENTICATED);
    }
}
