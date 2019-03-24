package z201.github.io.jwt;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import z201.github.io.base.tips.ErrorTip;
import z201.github.io.exception.EonerExceptionEnum;
import z201.github.io.util.JsonUtils;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;

/**
 * {@link AuthenticationEntryPoint} 拒绝所有请求与未经授权的错误消息。
 */
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

	private static final long serialVersionUID = -8970718410437077606L;

	@Override
    public void commence ( HttpServletRequest request,
                           HttpServletResponse response,
                           AuthenticationException authException ) throws IOException {
        response.setHeader( "Content-type", MediaType.APPLICATION_JSON_UTF8_VALUE );
        response.setCharacterEncoding( StandardCharsets.UTF_8.displayName() );
        try ( PrintWriter out = response.getWriter() ) {
            out.print( JsonUtils.toCustomizationJson( new ErrorTip(EonerExceptionEnum.TOKEN_EXPIRED.getState(), EonerExceptionEnum.TOKEN_ERROR.getError())));
        }
    }
}
