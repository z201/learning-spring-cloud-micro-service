package z201.github.io.util;

/**
 *
 * @author dzc
 * @create_time 2017/6/23
 *
 */
public class RespondJsonInfo {

    private String msg ;
    private int code ;

    public RespondJsonInfo(String msg) {
        this.msg = msg;
    }

    public RespondJsonInfo() {
        this.code = 0;
        this.msg = "ok";
    }


    public static RespondJsonInfo newInstance(int code,String msg){
        return new RespondJsonInfo(code,msg);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public RespondJsonInfo(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

}
