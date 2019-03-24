package z201.github.io.page;

import org.apache.commons.lang3.StringUtils;

import z201.github.io.util.ToolUtil;

/**
 * PageLimitFactory
 *   
 * @ClassName: PageLimitFactory
 * @Description: limit SQL工厂
 * @author z201_java_se@163.com
 * @date 2017年11月7日 上午11:35:34
 *
 */
public class PageLimitFactory {

	public static final int NO_ROW_OFFSET = 0;

	public static final int NO_ROW_LIMIT = Integer.MAX_VALUE;

	private static final String DEFAULT_PARAM_NAMES = "limit ";

	private int from; // 当前起始数量

	private int limit; // 每页显示个数

	@SuppressWarnings("unchecked")
	public String initLimit() {
		return StringUtils.join(DEFAULT_PARAM_NAMES, from, "," , limit);
	}

	public PageLimitFactory(int from, int limit) {
		super();
		if(ToolUtil.isEmpty(from)) {
			this.from = NO_ROW_OFFSET;
		}
		if(ToolUtil.isEmpty(limit)) {
			this.limit = NO_ROW_LIMIT;
		}
		this.from = from;
		this.limit = limit;
	}

	public PageLimitFactory() {
		this.from = NO_ROW_OFFSET;
		this.limit = NO_ROW_LIMIT;
	}

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

}
