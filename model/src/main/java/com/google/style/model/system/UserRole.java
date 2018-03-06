package com.google.style.model.system;

import lombok.Data;

/**
 * @date 2018/03/02 17:40
 * @author liangz
 * 用户角色表
 */
@Data
public class UserRole {
    private Long id;
    private Long userId;
    private Long roleId;

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", userId=" + userId +
                ", roleId=" + roleId +
                '}';
    }
}
