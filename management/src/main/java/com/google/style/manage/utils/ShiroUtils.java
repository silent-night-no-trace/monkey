package com.google.style.manage.utils;

import com.google.style.model.system.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author liangz
 * @date  2018/03/06 13:50
 * shiro 工具类
 */
public class ShiroUtils {

    @Autowired
    private static SessionDAO sessionDAO;

    public static Subject getSubject() {

        return SecurityUtils.getSubject();
    }

    /**
     * shiro 获取登录用户
     * @return User
     */
    public static User getUser() {
        Object object = getSubject().getPrincipal();
        User user = (User) object;
        if(user!=null) {
            System.out.println("登录用户信息为:" + user.toString());
        }
        return user;
    }

    /**
     * shiro 获取登录用户ID
     * @return Long
     */
    public static Long getUserId() {
        return getUser().getId();
    }

    /**
     * shiro 用户登出
     */
    public static void logout() {
        getSubject().logout();
    }

    public static List<Principal> getPrinciples() {
        List<Principal> principals = null;
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        for (Session session : sessions) {
            session.getId();
        }
        return principals;
    }
}
