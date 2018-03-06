package com.google.style.model.system;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author liangz
 * @date 2018/03/02 17:29
 * 用户表
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    //
    private Long id;
    // 用户名
    private String username;
    // 用户真实姓名
    private String name;
    // 密码
    private String password;
    // 部门
    private Long deptId;
    private String deptName;
    // 邮箱
    private String email;
    // 手机号
    private String mobile;
    // 状态 0:禁用，1:正常
    private Integer status;
    // 创建用户
    private String createBy;
    // 创建时间
    private Date createTime;
    // 修改时间
    private Date updateTime;
    //角色
    private List<Long> roleIds;
    //性别
    private Long sex;
    //现居住地
    private String liveAddress;
    //省份
    private String province;
    //所在城市
    private String city;
    //所在地区
    private String district;


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", deptId=" + deptId +
                ", deptName='" + deptName + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", status=" + status +
                ", createBy=" + createBy +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", roleIds=" + roleIds +
                ", sex=" + sex +
                ", liveAddress='" + liveAddress + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                '}';
    }
}
