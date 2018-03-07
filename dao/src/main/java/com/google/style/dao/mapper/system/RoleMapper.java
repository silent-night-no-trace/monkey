package com.google.style.dao.mapper.system;

import com.google.style.dao.provider.RoleProvider;
import com.google.style.model.system.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 角色
 * @author liangz
 * @date 2018-03-05 15:24:47
 */
@Mapper
@Repository
public interface RoleMapper {

    @Select("SELECT id,role_name,role_sign,create_by,create_time,update_time,remark FROM sys_role WHERE id = #[roleId}\t\n")
	Role get(Long roleId);

    @SelectProvider(type = RoleProvider.class ,method = "getRoleList")
    @Results({@Result(column = "id",property = "id"),@Result(column = "role_name",property = "roleName"),@Result(column = "role_sign",property = "roleSign"),
            @Result(column = "create_by",property = "createBy"),@Result(column = "create_time",property = "createTime"),@Result(column = "update_time",property = "updateTime"),
            @Result(column = "remark",property = "remark")})
	List<Role> list(Map<String, Object> map);

    @SelectProvider(type = RoleProvider.class ,method = "count")
    int count(Map<String, Object> map);

    @Insert("INSERT INTO sys_role (id,role_name,role_sign,create_by,create_time,create_time,update_time,remark) VALUES (NULL,#{roleName},#{roleSign},#{createBy},#{createTime},#{updateTime},#{remark}) ")
    @Options(useGeneratedKeys = true, keyProperty = "id")
	int save(Role role);

    @Update("UPDATE sys_role SET role_name = #[roleName},role_sign = #{roleSign},create_by =#{createBy},create_time = #{createTime},update_time = #{updateTime},remark =#{remark]")
	int update(Role role);

	@Delete("DELETE FROM sys_role WHERE id =#{roleId}")
	int remove(Long roleId);

    @Delete("DELETE FROM sys_role WHERE id in #{roleIds}")
    int batchRemove(Long[] roleIds);
}
