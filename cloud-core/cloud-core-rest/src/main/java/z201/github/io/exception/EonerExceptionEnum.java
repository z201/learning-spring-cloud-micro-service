package z201.github.io.exception;

/**
 * @Description 所有业务异常的枚举
 * @author z201
 * @date 2016年11月12日 下午5:04:51
 */
public enum EonerExceptionEnum {

	/**
	 * token异常
	 */
	TOKEN_EXPIRED(-1, "token过期"), 
	TOKEN_ERROR(-1, "token验证失败"),

	/**
	 * 签名异常
	 */
	SIGN_ERROR(-1, "签名验证失败"),

	/**
	 * 其他
	 */
	AUTH_REQUEST_ERROR(0, "账号密码错误"),
	/**
	 * 错误的请求
	 */
	REQUEST_NULL(0, "请求有错误"),
	DATA_NULL(0,"该数据不存在"),
	HANDLER_ERROR(0,"请求头异常"),
	FILE_NOT_FOUND(0,"文件不存在"),
	FILE_READING_ERROR(0,"文件读取失败"),
	REPEATING_DATA(0, "数据已经存在"),
	SERVER_ERROR(0, "服务器异常");

	EonerExceptionEnum(int code, String message) {
		this.state = code;
		this.error = message;
	}

	//状态码
	private int state;

	// 异常信息
	private String error;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}



}
