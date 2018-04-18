package com.baidu.demo.dao;

import com.baidu.demo.model.AdminRoleDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author liangz
 * @date 2018/4/12 17:52
 **/
@Mapper
@Repository
public interface AdminRoleMapper {

	/**
	 * 根据 用户id 获取 用户角色
	 * @param userId userId
	 * @return AdminRoleDO
	 */
	@Select("SELECT\n" +
			"\tar.id,\n" +
			"\tar. NAME,\n" +
			"\tar.created_at,\n" +
			"\tar.updated_at\n" +
			"FROM\n" +
			"\tadmin_role ar\n" +
			"LEFT JOIN admin_user_role adr ON ar.id = adr.role_id\n" +
			"WHERE\n" +
			"\tuser_id = #{userId}")
	AdminRoleDO getUserRoleByUserId(long userId);
}
