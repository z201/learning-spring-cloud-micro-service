package z201.github.io.util;

import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * json工具类
 * 
 * @date : 2016/10/2
 */
public class JsonUtils {

	public static String toCustomizationJson(Object input) {
		return com.alibaba.fastjson.JSON.toJSONString(input, SerializerFeature.DisableCircularReferenceDetect,
				SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
				SerializerFeature.WriteDateUseDateFormat);
	}

}
