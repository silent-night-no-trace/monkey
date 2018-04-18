package com.google.style.service.tools;

import com.google.style.model.system.User;
import com.google.style.model.system.UserOnline;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * @author liangz
 * @date 2018/3/16 15:47
 **/

public interface SessionService {
    /**
     * list
     * @return list
     */
    List<UserOnline> list();

    /**
     * 获取在线用户
     * @return
     */
    List<User> listOnlineUser();

    /**
     * session list
     * @return collection
     */
    Collection<Session> sessionList();

    /**
     * 强制登出
     * @param sessionId sessionId
     * @return boolean
     */
    boolean forceLogout(String sessionId);
}