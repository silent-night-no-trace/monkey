package com.google.style.manage.shiro;

import com.google.common.collect.Maps;
import com.google.style.dao.mapper.system.UserMapper;
import com.google.style.manage.config.ApplicationContextRegister;
import com.google.style.manage.utils.ShiroUtils;
import com.google.style.model.system.User;
import com.google.style.service.system.MenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashMap;
import java.util.Set;

/**
 * @author liangz
 * @date 2018/3/6 13:42
 *  系统认证实现
 **/
public class SystemRealm extends AuthorizingRealm {

    /**
     * 授权
     * @param principal
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        Object o = principal.getPrimaryPrincipal();
        System.out.println("principal :"+o.toString());
        Long userId = ShiroUtils.getUserId();
        MenuService menuService = ApplicationContextRegister.getBean(MenuService.class);
        Set<String> perms = menuService.listPerms(userId);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //设置对应权限信息
        info.setStringPermissions(perms);

        return info;
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取存储Principal 信息
        String username = (String)token.getPrincipal();
        HashMap<String,Object> map = Maps.newHashMap();
        map.put("username",username);

        String password = new String((char[]) token.getCredentials());

        UserMapper userMapper = ApplicationContextRegister.getBean(UserMapper.class);
        // 查询用户信息
        User user = userMapper.list(map).get(0);
        // 账号不存在
        if (user == null) {
            throw new UnknownAccountException("账号或密码不正确");
        }

        // 密码错误
        if (!password.equals(user.getPassword())) {
            throw new IncorrectCredentialsException("账号或密码不正确");
        }

        // 账号锁定
        if (user.getStatus() == 0) {
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        return info;
    }
}
