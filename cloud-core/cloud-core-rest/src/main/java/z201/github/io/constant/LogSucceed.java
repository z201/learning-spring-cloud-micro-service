package z201.github.io.constant;

/**
 * 业务是否成功的日志记录
 *
 * @Date 2017年1月22日 下午12:14:59
 */
public enum LogSucceed {

    SUCCESS("成功"),
    FAIL("失败");

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String message;

    LogSucceed(String message) {
        this.message = message;
    }

}
