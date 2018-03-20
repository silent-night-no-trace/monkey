package com.google.style.model.system;

import lombok.Data;
import lombok.ToString;

/**
 * @date 2018/03/02 17:40
 * @author liangz
 * 用户角色表
 */
@Data
@ToString
public class UserRole {
    private Long id;
    private Long userId;
    private Long roleId;

}
