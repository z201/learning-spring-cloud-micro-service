package z201.github.io.base.tips;

/**
 * 返回给前台的提示（最终转化为json形式）
 * @Date 2017年1月11日 下午11:58:00
 */
public abstract class AbstractTip {
	
	protected int status;
	
	protected String error;
	
	protected Object errorInfo;
	
	protected Object data;
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Object getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(Object errorInfo) {
		this.errorInfo = errorInfo;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
