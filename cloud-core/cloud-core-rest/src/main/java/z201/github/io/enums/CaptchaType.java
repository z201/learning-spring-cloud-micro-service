package z201.github.io.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 短信验证码类型
 * @author z201
 *
 */
public enum CaptchaType {
	
	注册(1,"注册"),
	找回密码(2,"找回密码"),
	修改支付密码(3,"修改支付密码"),
	绑定银行账户(4,"绑定银行账户"),
	绑定第三方登陆账户(5,"绑定第三方登陆账户"),
	更换已绑定手机号(6,"更换已绑定手机号,发送给旧手机"),
	更换绑定手机号(7,"更换绑定手机号,发送给新手机");
	
	public static List<CaptchaType> all(){
		List<CaptchaType> all = new ArrayList<CaptchaType>();
		all.add(注册);
		all.add(找回密码);
		all.add(修改支付密码);
		all.add(绑定银行账户);
		all.add(绑定第三方登陆账户);
		all.add(更换已绑定手机号);
		all.add(更换绑定手机号);
		return all;
	}
	
	public static String cahptchaTypeDescByCode(Integer code) {
		for(CaptchaType captchaType : all()) {
			if(captchaType.getCode() == code) {
				return captchaType.getDesc();
			}
		}
		return null;
	}
	
	public static boolean verifyCode(Integer code) {
		for(CaptchaType captchaType : all()) {
			if(captchaType.getCode() == code) {
				return true;
			}
		}
		return false;
	}
	private final int code;
	private final String desc;
	
	CaptchaType(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	public int getCode() {
		return code;
	}
	public String getDesc() {
		return desc;
	}
	
	
	
}
