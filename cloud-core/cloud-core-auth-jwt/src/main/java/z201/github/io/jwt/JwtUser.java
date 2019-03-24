package z201.github.io.jwt;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;

public class JwtUser implements BasicJwtUser {

	private static final long serialVersionUID = 1L;

	private final Long id;
	private final String username;
	private final String password;
	private final String nickName;
	private final String userImageUrl;
	private boolean enabled;

	public JwtUser(Long id, String username, String password, String nickName, String userImageUrl, boolean enabled) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.nickName = nickName;
		this.userImageUrl = userImageUrl;
		this.enabled = enabled;
	}
	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public String getNickName() {
		return nickName;
	}

	public String getUserImageUrl() {
		return userImageUrl;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return null;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public Date getLastPasswordResetDate() {
		return null;
	}

}
