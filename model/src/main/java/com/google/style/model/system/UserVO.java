package com.google.style.model.system;

import lombok.Data;
import lombok.ToString;

/**
 * @author liangz
 * @date 2018/3/15 14:45
 *  用户临时更新信息
 **/
@Data
@ToString
public class UserVO {
    /**
     * 更新用户信息
     */
    private User user = new User();
    /**
     * 用户密码
     */
    private String pwd;
    /**
     * 新密码
     */
    private String newPwd;
}
