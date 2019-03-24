package z201.github.io.exception;

public class EonerJwtException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	// 状态码
	private int state;

	// 异常信息
	private String error;

	// 返回数据
	private Object data;

	public EonerJwtException(int state, String error) {
		this.state = state;
		this.error = error;
	}

	public EonerJwtException(EonerExceptionEnum bizExceptionEnum) {
		this.state = bizExceptionEnum.getState();
		this.error = bizExceptionEnum.getError();
	}

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

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
