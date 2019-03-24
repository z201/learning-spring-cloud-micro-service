package z201.github.io.exception;

/**
 * @author z201
 * @Description 业务异常的封装
 * @date 2016年11月12日 下午5:05:10
 */
public class EonerException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	//状态码
	private int state;

	// 异常信息
	private String errer;

	// 返回数据
	private Object data;


	public EonerException(EonerExceptionEnum bizExceptionEnum) {
		this.state = bizExceptionEnum.getState();
		this.errer =  bizExceptionEnum.getError();
	}
	
	public EonerException(int state , String errer) {
		this.state = state;
		this.errer =  errer;
	}
	
	public EonerException(EonerExceptionEnum bizExceptionEnum , Object data) {
		this.state = bizExceptionEnum.getState();
		this.errer =  bizExceptionEnum.getError();
		this.data = data;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getErrer() {
		return errer;
	}

	public void setErrer(String errer) {
		this.errer = errer;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
