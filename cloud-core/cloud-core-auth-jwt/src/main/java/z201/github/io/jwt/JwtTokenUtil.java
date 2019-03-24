package z201.github.io.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.security.core.userdetails.UserDetails;

import z201.github.io.jwt.properties.JwtProperties;
import z201.github.io.util.DateUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class JwtTokenUtil {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
    JwtProperties jwtProperties;

	/** 用户模型的用户代理(终端)key **/
	private static final String CLAIM_KEY_AUDIENCE = "audience";
	/** 未知请求 */
	private static final String AUDIENCE_UNKNOWN = "unknown";
	/** 用户模型的用户代理(终端)浏览器端 value **/
	private static final String AUDIENCE_WEB = "web";
	/** 用户模型的用户代理(终端)手机端 value **/
	private static final String AUDIENCE_MOBILE = "mobile";
	/** 用户模型的用户代理(终端)平板 value **/
	private static final String AUDIENCE_TABLET = "tablet";
	/** jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击。 **/
	private static final String CLAIM_KEY_JTI = "jti";
	/** token 签发时间 **/
	private static final String CLAIM_KEY_IAT = "iat";
	/** 该JWT的签发者 **/
	private static final String CLAIM_KEY_ISS = "iss";
	/** 用户名key 该JWT所面向的用户 **/
	private static final String CLAIM_KEY_SUB = "sub";
	/** 接收jwt的一方 */
	private static final String CLAIM_KEY_AUD = "aud";
	/** 定义在什么时间之前，该jwt都是不可用的. */
	private static final String CLAIM_KEY_NBF = "nbf";

	/**
	 * 根据token得到用户名
	 * @param token 
	 * @return
	 */
	public String getUsernameFromToken(String token) {
		String username;
		try {
			final Claims claims = getClaimsFromToken(token);
			username = claims.getSubject();
		} catch (Exception e) {
			log.error("Exception {} \n username return null" , e.getMessage());
			username = null;
		}
		return username;
	}

	/**
	 * 根据token得到 jti
	 *
	 * @param token
	 * @return
	 */
	public String getJtiFromToken(String token) {
		String id;
		try {
			final Claims claims = getClaimsFromToken(token);
			id = claims.getId();
		} catch (Exception e) {
			log.error("Exception {} \n id return null" , e.getMessage());
			id = null;
		}
		return id;
	}

	/**
	 * 得到token的开始时间(或者说创建时间)
	 *
	 * @param token
	 * @return
	 */
	public Date getCreatedDateFromToken(String token) {
		Date created;
		try {
			final Claims claims = getClaimsFromToken(token);
			created = new Date((Long) claims.get(CLAIM_KEY_IAT));
		} catch (Exception e) {
			log.error("Exception {} \n created return null" , e.getMessage());
			created = null;
		}
		return created;
	}

	/**
	 * 得到token过期时间
	 *
	 * @param token
	 * @return
	 */
	public Date getExpirationDateFromToken(String token) {
		Date expiration;
		try {
			final Claims claims = getClaimsFromToken(token);
			expiration = claims.getExpiration();
		} catch (Exception e) {
			log.error("Exception {} \n expiration return null" , e.getMessage());
			expiration = null;
		}
		return expiration;
	}

	/**
	 * @param token
	 * @return
	 */
	public String getAudienceFromToken(String token) {
		String audience;
		try {
			final Claims claims = getClaimsFromToken(token);
			audience = (String) claims.get(CLAIM_KEY_AUDIENCE);
		} catch (Exception e) {
			log.error("Exception {} \n audience return null" , e.getMessage());
			audience = null;
		}
		return audience;
	}

	/**
	 * @param token
	 * @return
	 */
	private Claims getClaimsFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtProperties.getSecret()) // 签名密钥
				.parseClaimsJws(token) // token
				.getBody();
		return claims;
	}

	/**
	 * 生成过期时间
	 * 
	 * @return
	 */
	private Date generateExpirationDate() {
		return DateUtil.addSecond(new Date(), jwtProperties.getExpiration());
	}

	/**
	 * token是否过期
	 * 
	 * @param token
	 * @return expiration > 当前时间 true:过期 false:没过期
	 */
	public Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	/**
	 * 当前时间 > 密码最后重置时间
	 * 
	 * @param created
	 * @param lastPasswordReset
	 * @return created > lastPasswordReset
	 */
	private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
		return (lastPasswordReset != null && created.before(lastPasswordReset));
	}

	/**
	 * <p>
	 * 终端
	 * </p>
	 * 
	 * @param device
	 *            : 模型的用户代理或设备提交当前请求
	 * @return
	 */
	private String generateAudience(Device device) {
		String audience = AUDIENCE_UNKNOWN;
		if (device.isNormal()) {
			audience = AUDIENCE_WEB;
		} else if (device.isTablet()) {
			audience = AUDIENCE_TABLET;
		} else if (device.isMobile()) {
			audience = AUDIENCE_MOBILE;
		}
		return audience;
	}

	/**
	 * 是否忽略token过期(移动设备则会忽略)
	 * 
	 * @param token
	 * @return
	 */
	private Boolean ignoreTokenExpiration(String token) {
		String audience = getAudienceFromToken(token);
		// 如果是移动设备则忽略
		return (AUDIENCE_TABLET.equals(audience) || AUDIENCE_MOBILE.equals(audience));
	}

	/**
	 * 构建token
	 * 
	 * @param userDetails
	 * @param device
	 * @return
	 */
	public String generateToken(UserDetails userDetails, Device device, Long id) {
		Long time = getTimeMillis();
		Map<String, Object> claims = new HashMap<>();
		claims.put(CLAIM_KEY_JTI, id);
		claims.put(CLAIM_KEY_ISS, jwtProperties.getIss());
		claims.put(CLAIM_KEY_SUB, userDetails.getUsername());
		claims.put(CLAIM_KEY_AUD, jwtProperties.getSub());
		claims.put(CLAIM_KEY_NBF, time);
		claims.put(CLAIM_KEY_AUDIENCE, generateAudience(device));
		claims.put(CLAIM_KEY_IAT, time);
		return generateToken(claims);
	}

	/**
	 * 构建token
	 * 
	 * @param claims
	 * @return
	 */
	String generateToken(Map<String, Object> claims) {
		return Jwts.builder().setClaims(claims).setExpiration(generateExpirationDate())
				.signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret()).setHeaderParam("typ", "JWT").compact();
	}

	/**
	 * token是否可以刷新
	 * 
	 * @param token
	 * @return
	 */
	public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
		final Date created = getCreatedDateFromToken(token);
		return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
				&& (!isTokenExpired(token) || ignoreTokenExpiration(token));
	}

	/**
	 * 刷新token
	 * 
	 * @param token
	 * @return
	 */
	public String refreshToken(String token) {
		String refreshedToken;
		try {
			final Claims claims = getClaimsFromToken(token);
			claims.put(CLAIM_KEY_IAT, getTimeMillis());
			refreshedToken = generateToken(claims);
		} catch (Exception e) {
			refreshedToken = null;
		}
		return refreshedToken;
	}

	/**
	 * 验证token
	 * 
	 * @param token
	 * @param userDetails
	 * @return
	 */
	public Boolean validateToken(String token, UserDetails userDetails) {
		BasicJwtUser user = (BasicJwtUser) userDetails;
		final String username = this.getUsernameFromToken(token);
		return (username.equals(user.getUsername()) // 用户名校验
				&& !isTokenExpired(token) // token有效期校验
		);
	}

	/**
	 * 获取到秒的时间戳
	 * 
	 * @return
	 */
	public static Long getTimeMillis() {
		TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
		TimeZone.setDefault(tz);
		Long time = System.currentTimeMillis() / 1000;
		return time;
	}

}
