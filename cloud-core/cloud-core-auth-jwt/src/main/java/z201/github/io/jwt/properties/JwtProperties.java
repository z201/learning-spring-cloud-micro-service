package z201.github.io.jwt.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = JwtProperties.JWT_PREFIX)
public class JwtProperties {

	public static final String JWT_PREFIX = "jwt";
	/** 是否打开开发模式 */
	private Boolean jwtOpen;
	/** 签名密钥 **/
	private String secret;
	/** token过期时间 **/
	private Integer expiration;
	/** header key **/
	private String header;
	/** jwt签发者 */
	private String iss;
	/** jwt所面向的用户 */
	private String sub;

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public Integer getExpiration() {
		return expiration;
	}

	public void setExpiration(Integer expiration) {
		this.expiration = expiration;
	}

	public static String getJwtPrefix() {
		return JWT_PREFIX;
	}

	public Boolean getJwtOpen() {
		return jwtOpen;
	}

	public void setJwtOpen(Boolean jwtOpen) {
		this.jwtOpen = jwtOpen;
	}

	public String getIss() {
		return iss;
	}

	public void setIss(String iss) {
		this.iss = iss;
	}

	public String getSub() {
		return sub;
	}

	public void setSub(String sub) {
		this.sub = sub;
	}

}
