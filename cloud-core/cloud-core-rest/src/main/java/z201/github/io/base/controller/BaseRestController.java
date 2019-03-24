package z201.github.io.base.controller;


import com.baomidou.mybatisplus.plugins.Page;
import z201.github.io.page.PageInfoBT;
import z201.github.io.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.*;

public class BaseRestController {
	
	protected Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 把service层的分页信息，封装为bootstrap table通用的分页封装
     */
    protected <T> PageInfoBT<T> packForBT(Page<T> page) {
        return new PageInfoBT<T>(page);
    }

    /**
     * 返回前台文件流
     * @date 2017年2月28日 下午2:53:19
     */
    protected ResponseEntity<byte[]> renderFile(String fileName, String filePath) {
        byte[] bytes = FileUtil.toByteArray(filePath);
        return renderFile(fileName, bytes);
    }

    /**
     * 返回前台文件流
     * @date 2017年2月28日 下午2:53:19
     */
    protected ResponseEntity<byte[]> renderFile(String fileName, byte[] fileBytes) {
        String dfileName = null;
        try {
            dfileName = new String(fileName.getBytes("gb2312"), "iso8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", dfileName);
        return new ResponseEntity<byte[]>(fileBytes, headers, HttpStatus.CREATED);
    }
    
    /**
     * 显示验证异常信息
     * @param result
     */
    protected String showBindingResultError(BindingResult result) { 
    	List<FieldError> list = result.getFieldErrors();
    	FieldError error = null;
    	List<String> errorLs = new ArrayList<String>();
    	errorLs.add(MessageFormat.format("错误提示：{0} 次",list.size()));
		for (int i = 0; i < list.size(); i++) {
			error = list.get(i);
			errorLs.add(MessageFormat.format(", {0}",error.getDefaultMessage()));
			errorLs.add(MessageFormat.format("字段：{0} 错误提示：{1} ",error.getField() ,error.getDefaultMessage()));
		}
        log.error(errorLs.toString());
		return errorLs.toString();
    }
    
    
}
