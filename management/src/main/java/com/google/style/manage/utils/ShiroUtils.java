package com.google.style.manage.utils;

import com.google.style.model.system.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

/**
 * @author liangz
 * @date  2018/03/06 13:50
 * shiro 工具类
 */
public class ShiroUtils {

    @Autowired
    private static SessionDAO sessionDAO;

    public static Subject getSubjct() {

        return SecurityUtils.getSubject();
    }

    public static User getUser() {
        Object object = getSubjct().getPrincipal();
        User user = (User) object;
        if(user!=null) {
            System.out.println("登录用户信息为:" + user.toString());
        }
        return user;
    }
    public static Long getUserId() {
        return getUser().getId();
    }
    public static void logout() {
        getSubjct().logout();
    }

    public static List<Principal> getPrinciples() {
        List<Principal> principals = null;
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        return principals;
    }
}
