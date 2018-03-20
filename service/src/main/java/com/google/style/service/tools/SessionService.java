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
    List<UserOnline> list();

    List<User> listOnlineUser();

    Collection<Session> sessionList();

    boolean forceLogout(String sessionId);
}