package com.baidu.demo.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author liangz
 * @date 2018/4/12 18:01
 *
 **/
public class CustomGrantedAuthority implements GrantedAuthority {
	private String permissionUrl;

	public CustomGrantedAuthority(String permissionUrl) {
		this.permissionUrl = permissionUrl;
	}

	@Override
	public String getAuthority() {
		return permissionUrl;
	}

	public String getPermissionUrl() {
		return permissionUrl;
	}

}
