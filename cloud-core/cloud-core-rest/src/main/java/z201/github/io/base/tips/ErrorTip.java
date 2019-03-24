package z201.github.io.base.tips;


/**
 * 返回给前台的错误提示
 * 普通错误是1
 * 登录成功是0
 * 认证失败是-1
 * @date 2016年11月12日 下午5:05:22
 */
public class ErrorTip extends AbstractTip {

	public ErrorTip(){
		super();
		this.status = 1;
	}
	
	public ErrorTip(String error){
		super();
		this.status = 1;
		this.error = error;
	}
	
	public ErrorTip(Object data){
		super();
		this.status = 1;
		this.data = data;
	}
	
	public ErrorTip(int status, Object data){
		super();
		this.status = status;
		this.data = data;
	}
	
    public ErrorTip(int status, String error , Object data) {
        super();
        this.status = status;
        this.error = error;
        this.data = data;
    }
    
    public ErrorTip(int status, String error) {
        this.status = status;
        this.error = error;
    }
    
}
