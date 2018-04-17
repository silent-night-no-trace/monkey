package com.baidu.demo.security;

import com.baidu.demo.dao.AdminUserMapper;
import com.baidu.demo.model.AdminUser;
import com.baidu.demo.model.dto.AdminUserDto;
import com.baidu.demo.model.vo.AdminMenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liangz
 * @date 2018/4/12 17:09
 * 用户数据
 **/
@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private AdminUserMapper adminUserMapper;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AdminUser adminUser = adminUserMapper.getByLoginName(username);
		if(adminUser!=null){
			if (adminUser.isLocked()) {
				throw new UserLockedException("");
			} else {
				//封装用户权限信息
				AdminUserDto adminUserDto = adminUser.getAdminUserDto();
				System.out.println(adminUserDto.getMenus());
				List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
				grantedAuthorities.addAll(getMenuGrantedAuthority(adminUserDto.getMenus()));
				System.out.println("111111   :"+getMenuGrantedAuthority(adminUserDto.getMenus()));
				adminUser.setGrantedAuthorities(grantedAuthorities);
				return adminUser;
			}
		}

		return null;
	}

	private List<GrantedAuthority> getMenuGrantedAuthority(ArrayList<AdminMenuVO> source) {
		List<GrantedAuthority> result = new ArrayList<>();

		for (AdminMenuVO menu : source) {
			if (menu.getUrl() != null && !"".equals(menu.getUrl())) {
				System.out.println("url ："+menu.getUrl());
				GrantedAuthority grantedAuthority = new CustomGrantedAuthority(menu.getUrl());
				result.add(grantedAuthority);
			}

			if (menu.getSubMenus() != null && menu.getSubMenus().size() > 0) {
				List<GrantedAuthority> subAuths = getMenuGrantedAuthority(menu.getSubMenus());

				if (subAuths != null && subAuths.size() > 0) {
					result.addAll(subAuths);
				}
			}
		}

		return result;
	}
}
