package io.rl.tm_api.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import io.rl.tm_api.entity.User;

public class MyUserDetails implements UserDetails {
	
	String username;
	String password;
	private boolean active;
	private List<GrantedAuthority> authorities;
	//	String firstName;
	//	String lastName;
	
	public MyUserDetails(User user) {
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.active = user.getActive()!=0;
		this.authorities = Arrays.stream(user.getRoles().split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
//		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
//		return null;
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return active;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
