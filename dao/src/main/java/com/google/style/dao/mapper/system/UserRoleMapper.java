package com.google.style.dao.mapper.system;

import com.google.style.dao.provider.UserRoleProvider;
import com.google.style.model.system.UserRole;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 用户与角色对应关系
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 11:08:59
 */
@Mapper
@Repository
public interface UserRoleMapper {

	@Select("SELECT * FROM sys_user_role WHERE id = #{id}")
	UserRole get(Long id);

    @SelectProvider(type = UserRoleProvider.class, method = "getUserRoleList")
    @Results({@Result(column = "id",property = "id"),@Result(column = "user_id",property = "userId"),@Result(column = "role_id",property = "roleId")})
	List<UserRole> list(Map<String, Object> map);

    @SelectProvider(type = UserRoleProvider.class, method = "getCount")
    int count(Map<String, Object> map);

    @Insert("INSERT INTO sys_user_role (id ,user_id ,role_id) VALUES (NULL, #{user_id},#{role_id}) ")
    @Options(useGeneratedKeys = true, keyProperty = "id")
	int save(UserRole userRole);

    @Update("UPDATE sys_user_role SET user_id = #{userId},role_id =#{roleId} WHERE id =#{id}")
	int update(UserRole userRole);

    @Delete("DELETE  FROM  sys_user_role WHERE id = #{id}")
	int remove(Long id);

    @Delete("DELETE  FROM  sys_user_role WHERE id in {ids}")
	int batchRemove(Long[] ids);


    @Select("select role_id from\n" +
            "\t\tsys_user_role where\n" +
            "\t\tuser_id=#{userId}")
	List<Long> listRoleId(Long userId);

    @Delete("\t\tdelete from sys_user_role where user_id=#{userId}\n")
	int removeByUserId(Long userId);

    @SelectProvider(type = UserRoleProvider.class,method = "batchSave")
	int batchSave(List<UserRole> list);

    @Delete("\t\tdelete from sys_user_role where user_id in #{ids}\n")
    int batchRemoveByUserId(Long[] ids);
}
