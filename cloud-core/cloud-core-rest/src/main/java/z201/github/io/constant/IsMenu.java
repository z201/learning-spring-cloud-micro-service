package z201.github.io.constant;

/**
 * 是否是菜单的枚举
 *
 * @author z201
 * @date 2017年6月1日22:50:11
 */
enum IsMenu {

    YES(1 , "是"), NO(0 , "不是");//不是菜单的是按钮


    public int code;

    public String message;

    IsMenu(int code , String message) {
        this.code = code;
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }


    public static String valueOf(Integer status) {
        if (status == null) {
            return "";
        } else {
            for (IsMenu s : IsMenu.values()) {
                if (s.getCode() == status) {
                    return s.getMessage();
                }
            }
            return "";
        }
    }
}
