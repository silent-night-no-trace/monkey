package com.google.style.dao.mapper.system;

import com.google.style.dao.provider.system.UserRoleProvider;
import com.google.style.model.system.UserRole;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 用户与角色对应关系
 * 
 * @author liangz
 * @date 2018/03/13 11:52
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

    @Insert("INSERT INTO sys_user_role (id ,user_id ,role_id) VALUES (NULL, #{userId},#{roleId}) ")
    @Options(useGeneratedKeys = true, keyProperty = "id")
	int save(UserRole userRole);

    @Update("<script>"+
            "update sys_user_role " +
            "<set>" +
            "<if test=\"userId != null\">`user_id` = #{userId}, </if>" +
            "<if test=\"roleId != null\">`role_id` = #{roleId},</if>" +
            "</set>" +
            "where id = #{id}"+
            "</script>")
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

//    @SelectProvider(type = UserRoleProvider.class,method = "batchSave")
//    @Insert("\"<script>\"+\n" +
//            "\t\"Insert into sys_user_role(user_id, role_id) values \"+\n" +
//            "\t\"<foreach item ='userRole' collection ='list' open=\\\"(\\\" separator=\\\",\\\" close=\\\")\\\">\"\n" +
//            "\t\t\"#{userRole.userId},#{userRole.roleId} \"+\n" +
//            "\t\t\"</foreach>\"+\n" +
//            "\t\"</script\"")
//	int batchSave(UserRole userRole);

    @Delete("\t\tdelete from sys_user_role where user_id in #{ids}\n")
    int batchRemoveByUserId(Long[] ids);
}
