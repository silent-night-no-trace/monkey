package com.baidu.demo.dao;

import com.baidu.demo.model.AdminUser;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.time.Period;

/**
 * @author liangz
 * @date 2018/4/12 17:33
 **/
@Mapper
@Repository
public interface AdminUserMapper {

	@SelectProvider(type = SelectSQL.class, method = "getAdminUserByLoginName")
	@Results({
			@Result(property = "id", column = "id"),
			@Result(property = "username",column = "username"),
			@Result(property = "password",column = "password"),
			@Result(property = "status",column = "status"),
			@Result(property = "lastVisitTime",column = "last_visit_time"),
			@Result(property = "createdAt",column = "created_at"),
			@Result(property = "updatedAt",column = "updated_at"),
			@Result(property = "roleList", column = "id",many = @Many(select = "com.baidu.demo.dao.AdminRoleMapper.getUserRoleByUserId",fetchType = FetchType.EAGER)),
			@Result(property = "menuList", column = "id", many = @Many(select = "com.baidu.demo.dao.AdminMenuMapper.getUserMenuByUserId", fetchType = FetchType.EAGER))
	})
	AdminUser getByLoginName(String username);


	@Update("update admin_user set last_visit_time = UNIX_TIMESTAMP() * 1000 where id = #{userId}")
	int updateLastVisitTime(long userId);

	class SelectSQL {
		public String getAdminUserByLoginName() {
			try {
				return new SQL() {
					{
						SELECT("*");
						FROM("admin_user");
						WHERE("username = #{username}");
					}
				}.toString();
			} catch (Exception e) {
				return "";
			}
		}
	}
}
