package z201.github.io.exception;

/**
 * @Description 业务异常的封装
 * @date 2016年11月12日 下午5:05:10
 */
@SuppressWarnings("serial")
public class BussinessException extends RuntimeException{



	private int code;
	
	//业务异常跳转的页面
	private String urlPath;
	
	//异常信息
	private String errer;
	
	//返回数据
	private Object data;
	
	public BussinessException(int state , String errer) {
		this.code = state;
		this.errer = errer;
	}
	
	public BussinessException(BizExceptionEnum bizExceptionEnum){
		this.code = bizExceptionEnum.getCode();
		this.errer = bizExceptionEnum.getMessage();
		this.urlPath = bizExceptionEnum.getUrlPath();
	}
	
	public BussinessException() {
		
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
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

	public String getUrlPath() {
		return urlPath;
	}

	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}
	
}
