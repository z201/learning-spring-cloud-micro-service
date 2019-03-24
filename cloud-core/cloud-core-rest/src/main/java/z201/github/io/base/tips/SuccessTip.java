package z201.github.io.base.tips;

/**
 * 返回给前台的成功提示
 *
 * @date 2016年11月12日 下午5:05:22
 */
public class SuccessTip extends AbstractTip {
	
	public SuccessTip(){
		
	}
	
	public SuccessTip(Object data){
		super();
		this.status = 1;
		this.data = data;
	}
	
}
