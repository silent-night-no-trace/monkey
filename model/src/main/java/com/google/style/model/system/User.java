package com.google.style.model.system;

import lombok.Data;
import lombok.ToString;

import java.io.*;
import java.util.Date;
import java.util.List;

/**
 *
 * @author liangz
 * @date 2018/03/02 17:29
 * 用户表
 */
@Data
@ToString
public class User implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long id;
    // 用户名
    private String username;
    // 用户真实姓名
    private String name;
    // 密码
    private String password;
    // 部门
    private Long deptId;
    //部门名称
    private String deptName;
    // 邮箱
    private String email;
    // 手机号
    private String mobile;
    //图片ID
    private Long picId;
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


}
