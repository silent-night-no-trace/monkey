package com.google.style.model.system;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * @date 20180302 17:09
 * @author liangz
 * 角色
 */
@Slf4j
@Data
public class Role implements Serializable {
	
	private Long id;
    /**
     * 角色名称
     */
	private String roleName;
    /**
     * 角色标识
     */
	private String roleSign;
    /**
     *创建者
     */
	private String createBy;
    /**
     * 创建时间
     */
	private Timestamp createTime;
    /**
     * 更新时间
     */
	private Timestamp updateTime;
	private List<Long> menuIds;
    /**
     * 备注
     */
	private String remark;




	@Override
	public String toString() {
		return "Role{" +
				"roleId=" + id +
				", roleName='" + roleName + '\'' +
				", roleSign='" + roleSign + '\'' +
				", remark='" + remark + '\'' +
				", createBy=" + createBy +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				", menuIds=" + menuIds +
				'}';
	}
}
