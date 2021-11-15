package com.demo.security.user;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.demo.helpers.TypeMap;
import com.demo.pojos.DemoObject;

public class DemoUser implements UserDetails, DemoObject {

	private static final long serialVersionUID = -5419119032616070437L;

	private String username;
	private String password;
	private Collection<GrantedAuthority> authorities;
	private boolean isAccountNonExpired;
	private boolean isAccountNonLocked;
	private boolean isCredentialsNonExpired;
	private boolean isEnabled;

	public DemoUser(String username, String password, String... roles) {
		this(username, password, getRolesAsAuthorities(roles));
	}

	public DemoUser(String username, String password, Collection<GrantedAuthority> authorities) {
		this(username, password, authorities, true, true, true, true);
	}

	public DemoUser(
			String username, 
			String password, 
			Collection<GrantedAuthority> authorities,
			boolean isAccountNonExpired, 
			boolean isAccountNonLocked, 
			boolean isCredentialsNonExpired,
			boolean isEnabled) {

		this.username = username;
		this.password = password;
		this.authorities = authorities;
		this.isAccountNonExpired = isAccountNonExpired;
		this.isAccountNonLocked = isAccountNonLocked;
		this.isCredentialsNonExpired = isCredentialsNonExpired;
		this.isEnabled = isEnabled;
	}

	@SuppressWarnings("unchecked")
	public DemoUser(TypeMap typeMap) {
		String username = typeMap.getString("username");
		String password = typeMap.getString("password");
		Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) typeMap.get("roles");
		boolean isAccountNonExpired = !typeMap.getBoolean("account_expired");
		boolean isAccountNonLocked = !typeMap.getBoolean("account_locked");
		boolean isCredentialsNonExpired = !typeMap.getBoolean("password_expired");
		boolean isEnabled = typeMap.getBoolean("account_enabled");

		// when there is no exception
		this.username = username;
		this.password = password;
		this.authorities = authorities;
		this.isAccountNonExpired = isAccountNonExpired;
		this.isAccountNonLocked = isAccountNonLocked;
		this.isCredentialsNonExpired = isCredentialsNonExpired;
		this.isEnabled = isEnabled;
	}

	private static Collection<GrantedAuthority> getRolesAsAuthorities(String... roles) {
		if (roles.length == 0) {
			return null;
		}
		return Arrays.stream(roles)
			.map(DemoUser::addSpringRolePrefix)
			.map(DemoUser::getRoleAsAuthority)
			.collect(toList());
	}

	private static String addSpringRolePrefix(String role) {
		return "ROLE_".concat(role);
	}

	private static GrantedAuthority getRoleAsAuthority(String role) {
		return new SimpleGrantedAuthority(role);
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return isEnabled;
	}

	@Override
	public String toString() {
		return "User: " + username + ", active: " + isEnabled;
	}

	@Override
	public int hashCode() {
		return 31 * username.length();
	}

	@Override
	public boolean equals(Object object) {
		if (object == this) {
			return true;
		}
		if (object instanceof DemoUser) {
			DemoUser otherObject = (DemoUser) object;
			if (otherObject.getUsername().equals(username)
					&& otherObject.getAuthorities().equals(authorities)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public TypeMap toTypeMap() {
		TypeMap userMap = new TypeMap();
		userMap.put("username", username);
		userMap.put("password", "Secret");
		userMap.put("roles", getRoles(authorities));
		userMap.put("account_expired", !isAccountNonExpired);
		userMap.put("account_locked", !isAccountNonLocked);
		userMap.put("password_expired", !isCredentialsNonExpired);
		userMap.put("account_enabled", isEnabled);
		return userMap;
	}

	public List<String> getRoles(Collection<GrantedAuthority> authorities) {
		return authorities.stream()
			.map(GrantedAuthority::getAuthority)
			.map(this::stripSpringRolePrefix)
			.collect(toList());
	}

	private String stripSpringRolePrefix(String role) {
		return role.substring(role.lastIndexOf("ROLE_"));
	}

}
