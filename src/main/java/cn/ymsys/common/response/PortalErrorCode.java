package cn.ymsys.common.response;

public enum PortalErrorCode {

	/**
	 * 系统内部错误
	 */
	ERROR_SYSTEM_INTERNAL("PORTAL-10000", "SYSTEM_INTERNAL_ERROR", "系统内部错误"),

	/**
	 * 请求参数无效
	 */
	ERROR_REQUEST_PARAMETER_INVALID("PORTAL-10001", "REQUEST_PARAMETER_INVALID", "请求参数无效"),

	/**
	 * 未登录认证
	 */
	ERROR_UNAUTHENTICATED("PORTAL-10002", "UNAUTHENTICATED", "请先登录"),

    /**
     * 登录失败
     */
    ERROR_USER_LOGIN_FAILED("PORTAL-10002", "USER_LOGIN_FAILED", ""),

	/**
	 * 无访问权限
	 */
	ERROR_ROLES_UNAUTHORIZED("PORTAL-10003", "ROLES_UNAUTHORIZED", "无访问权限，请联系管理员！"),

	/**
	 * 认证失败
	 */
	ERROR_AUTHENTICATION_FAILED("PORTAL-10004", "AUTHENTICATION_FAILED", ""),

	/**
	 * 接口访问限流
	 */
	ERROR_INTERFACE_LIMIT_ACCESS("PORTAL-10005", "INTERFACE_LIMIT_ACCESS", "接口访问超出频率限制"),

	/**
	 * 业务请求失败
	 */
	ERROR_PORTAL_REQUEST_FAILED("PORTAL-10006", "PORTAL_REQUEST_FAILED", "业务请求失败"),

	/**
	 * 无项目访问权限
	 */
	ERROR_PROJECT_UNAUTHORIZED("PORTAL-10007", "PROJECT_UNAUTHORIZED", "无项目权限，请联系项目负责人！"),

	/**
	 * 请求过于频繁
	 */
	ERROR_REQUEST_TOO_FREQUENT("PORTAL-10008", "REQUEST_TOO_FREQUENT", "请求过于频繁，请稍后再试！");

	private String code;

	private String type;

    private String hint;

	PortalErrorCode(String code, String type, String hint) {
		this.code = code;
		this.type = type;
		this.hint = hint;
	}

	public String getCode() {
		return code;
	}

	public String getType() {
		return type;
	}

	public String getHint() {
		return hint;
	}

    public String responseMessage() {
        return responseMessage(this.hint);
    }

	public String responseMessage(String hint) {
		return String.format("%s - %s: %s", this.code, this.type, hint);
	}
}
