package z201.github.io.xss;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 
 * @ClassName: XssHttpServletRequestWrapper 
 * @Description: (这里用一句话描述这个类的作用) 
 * @author z201_java_se@163.com
 * @date 2017年10月16日 下午3:44:44 
 *
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    public XssHttpServletRequestWrapper(HttpServletRequest servletRequest) {

        super(servletRequest);

    }

    @Override
    public String[] getParameterValues(String parameter) {

        String[] values = super.getParameterValues(parameter);

        if (values == null) {

            return null;

        }

        int count = values.length;

        String[] encodedValues = new String[count];

        for (int i = 0; i < count; i++) {

            encodedValues[i] = cleanXSS(values[i]);

        }

        return encodedValues;

    }

    @Override
    public String getParameter(String parameter) {

        String value = super.getParameter(parameter);

        if (value == null) {

            return null;

        }

        return cleanXSS(value);

    }

    @Override
    public String getHeader(String name) {

        String value = super.getHeader(name);

        if (value == null) {
        	  return null;
        }
        return cleanXSS(value);

    }

    private String cleanXSS(String value) {

        //You'll need to remove the spaces from the html entities below

        value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");

        value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");

//        value = value.replaceAll("'", "& #39;");
        value = value.replaceAll("\'", "& #39;");

        value = value.replaceAll("eval\\((.*)\\)", "");

        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");

        value = value.replaceAll("script", "");

        return value;

    }


}