package com.google.style.manage.common.controller;

import com.google.style.manage.utils.ShiroUtils;
import com.google.style.model.system.User;
import org.springframework.stereotype.Controller;

/**
 * @author liangz
 * @date 2018/3/6 15:47
 **/
@Controller
public class BaseController {

    public User getUser() {
        return ShiroUtils.getUser();
    }

    public Long getUserId() {
        return getUser().getId();
    }

    public String getUsername() {
        return getUser().getUsername();
    }
}
