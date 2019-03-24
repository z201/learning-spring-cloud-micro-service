package z201.github.io.constant;

/**
 * 数据库排序
 *
 * @author z201
 * @Date 2017年5月31日20:48:41
 */
public enum Order {

    ASC("ASC"), DESC("DESC");

    private String desc;

    Order(String des) {
        this.desc = des;
    }

    public String getDes() {
        return desc;
    }

    public void setDes(String desc) {
        this.desc = desc;
    }
    
    public static boolean check(String desc) {
    	for (Order item : Order.values()) {
			if(item.getDes().equals(desc)) {
				return true;
			}
		}
    	return false;
    }
}
