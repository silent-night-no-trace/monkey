package com.google.style.model.system;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @date  2018/03/02 17:42
 * @author liangz
 * @version V1.0
 * 用户 token
 */
@Data
@ToString
public class UserToken implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long userId;
    private String username;
    private String name;
    private String password;
    private Long deptId;


}
