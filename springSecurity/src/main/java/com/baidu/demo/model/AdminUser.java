package com.baidu.demo.model;


import com.baidu.demo.model.dto.AdminUserDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author liangz
 * @date 2018/4/12 17:34
 * 用户详细信息
 **/
@Data
public class AdminUser implements UserDetails {

	private Long id;
	private String username;
	@JsonIgnore
	private String password;
	private Integer status;
	private Long lastVisitTime;
	@JsonIgnore
	private Long createdAt;
	@JsonIgnore
	private Long updatedAt;

	/**
	 * 用户role list
	 */
	@JsonIgnore
	private ArrayList<AdminRoleDO> roleList;
	@JsonIgnore
	private ArrayList<AdminMenuDO> menuList;


	@JsonIgnore
	private List<? extends GrantedAuthority> authorities;

	@JsonIgnore
	private AdminUserDto adminUserDto;

	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isEnabled() {
		return true;
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	@JsonIgnore
	public String getUsername() {
		return username;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setGrantedAuthorities(List<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@JsonIgnore
	public boolean isLocked() {
		return this.status == 2;
	}

	/**
	 * 定义 转储 对象获取
	 * @return
	 */
	public AdminUserDto getAdminUserDto() {
		adminUserDto = new AdminUserDto();
		adminUserDto.setId(this.id);
		adminUserDto.setUsername(this.username);
		adminUserDto.setRoleList(this.roleList);
		adminUserDto.setMenuList(this.menuList);
		return adminUserDto;
	}



}
