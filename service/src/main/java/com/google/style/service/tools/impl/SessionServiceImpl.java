package com.google.style.service.tools.impl;

import com.google.common.collect.Lists;
import com.google.style.model.system.User;
import com.google.style.model.system.UserOnline;
import com.google.style.service.tools.SessionService;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author liangz
 * @date 2018/3/16 15:48
 *  在线用户管理 service
 **/
@Service
public class SessionServiceImpl implements SessionService {


    private final SessionDAO sessionDAO;

    /**
     * 进行构造初始化
     * @param sessionDAO
     */
    @Autowired
    public SessionServiceImpl(SessionDAO sessionDAO) {
        this.sessionDAO = sessionDAO;
    }

    @Override
    public List<UserOnline> list() {
        //在线用户封装  参考来自 session 属性信息
        List<UserOnline> onlineUsers = Lists.newArrayList();
        Collection<Session> activeSessions = sessionDAO.getActiveSessions();
        for (Session session : activeSessions) {
            UserOnline userOnline = new UserOnline();
            if (session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY) == null) {
                //不存在 session 用户
                continue;
            } else {
                SimplePrincipalCollection principalCollection = (SimplePrincipalCollection) session
                        .getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
                User userDO = (User) principalCollection.getPrimaryPrincipal();
                userOnline.setUsername(userDO.getUsername());
            }
            userOnline.setId((String) session.getId());
            userOnline.setHost(session.getHost());
            userOnline.setStartTimestamp(session.getStartTimestamp());
            userOnline.setLastAccessTime(session.getLastAccessTime());
            userOnline.setTimeout(session.getTimeout());
            onlineUsers.add(userOnline);
        }
        return onlineUsers;
    }

    @Override
    public List<User> listOnlineUser() {
        ArrayList<User> list = Lists.newArrayList();
        //获取存活session
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        for (Session session:sessions){
            if(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY)==null){
                continue;
            }
            SimplePrincipalCollection principalCollection = (SimplePrincipalCollection)session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            User user = (User)principalCollection.getPrimaryPrincipal();
            list.add(user);
        }

        return list;
    }

    @Override
    public Collection<Session> sessionList() {
        return sessionDAO.getActiveSessions();
    }

    /**
     * 强制登出
     * @param sessionId
     * @return
     */
    @Override
    public boolean forceLogout(String sessionId) {
        Session session = sessionDAO.readSession(sessionId);
        session.setTimeout(0);
        return true;
    }
}
