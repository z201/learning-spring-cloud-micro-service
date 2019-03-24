package z201.github.io.exception;

import z201.github.io.base.tips.ErrorTip;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 全局的的异常拦截器（拦截所有的控制器）（带有@RequestMapping注解的方法上都会拦截）
 * @author z201
 * @date 2016年11月12日 下午3:19:56
 */
@ControllerAdvice
@ConditionalOnProperty(prefix = "commom-properties", name = "debug-open", havingValue = "false")
public class GlobalControllerExceptionHandler {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    
	/**
     * 404异常
     * @author z201
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorTip noHandlerFoundException(EonerException e) {
        return new ErrorTip(e.getState(), e.getErrer());
    }
    
    /**
     * 501异常
     * @author z201
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ErrorTip illegalArgumentException(EonerException e) {
        log.error("501异常: {}", e.getErrer());
        return new ErrorTip(e.getState(), e.getErrer());
    }
    
    /**
     * 拦截业务异常
     * @author z201
     */
    @ExceptionHandler(EonerException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ErrorTip notFount(EonerException e) {
        log.error("拦截业务异常: {}", e.getErrer());
        return new ErrorTip(e.getState(), e.getErrer());
    }
    
    /**
     * jwt 认证异常
     * @author z201
     */
    @ExceptionHandler(EonerJwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorTip notFount(EonerJwtException e) {
        log.error("jwt 认证异常: {} ", e.getError());
        return new ErrorTip(e.getState(), e.getError());
    }
    
    /**
     * 拦截未知的运行时异常
     * @author z201
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ErrorTip notFount(RuntimeException e) {
        log.error("运行时异常: {}", e);
        return new ErrorTip(EonerExceptionEnum.SERVER_ERROR.getState(), EonerExceptionEnum.SERVER_ERROR.getError());
    }
	
    /**
     * 请求参数非空异常
     * @author z201
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ErrorTip notFount(MissingServletRequestParameterException e) {
        log.error("运行时异常: {}", e);
        return new ErrorTip(EonerExceptionEnum.SERVER_ERROR.getState(), EonerExceptionEnum.REQUEST_NULL.getError());
    }
    
    /**
     * 请求参数类型异常
     * @author z201
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ErrorTip methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error("运行时异常: {}", e);
        return new ErrorTip(EonerExceptionEnum.SERVER_ERROR.getState(), EonerExceptionEnum.REQUEST_NULL.getError());
    }
    
    /**
     * 请求头异常
     * @author z201
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ErrorTip toolBoxException(HttpRequestMethodNotSupportedException e) {
        log.error("请求头异常: {}", e);
        return new ErrorTip(EonerExceptionEnum.HANDLER_ERROR.getState(), EonerExceptionEnum.HANDLER_ERROR.getError());
    }
    
    /**
     * 工具类运行时异常
     * @author z201
     */
    @ExceptionHandler(ToolBoxException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ErrorTip toolBoxException(ToolBoxException e) {
        log.error("工具类运行时异常: {}", e);
        return new ErrorTip(EonerExceptionEnum.SERVER_ERROR.getState(), EonerExceptionEnum.REQUEST_NULL.getError());
    }
    
    

    

}
