package com.google.style.dao.mapper.system;

import com.google.style.dao.provider.system.RoleMenuProvider;
import com.google.style.model.system.RoleMenu;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 角色与菜单对应关系
 * @author liangz
 * @date 2018/03/13 11:553
 */
@Mapper
@Repository
public interface RoleMenuMapper {

    @Select("SELECT id ,role_id ,menu_id FROM sys_role_menu WHERE id = #{id}")
	RoleMenu get(Long id);

    @SelectProvider(type = RoleMenuProvider.class,method = "getRoleMenuList")
    @Results({@Result(column = "id",property = "id"),@Result(column = "role_id",property = "roleId"),@Result(column = "menu_id",property = "menuId")})
    List<RoleMenu> list(Map<String, Object> map);

    @SelectProvider(type = RoleMenuProvider.class,method = "count")
    int count(Map<String, Object> map);

    @Insert("INSERT INTO sys_role_menu (id ,role_id ,menu_id) VALUES (NULL, #{roleId},#{menuId}) ")
    @Options(useGeneratedKeys = true, keyProperty = "id")
	int save(RoleMenu roleMenu);

    @Update("<script>"+
            "update sys_role_menu " +
            "<set>" +
            "<if test=\"roleId != null\">`role_id` = #{roleId}, </if>" +
            "<if test=\"menuId != null\">`menu_id` = #{menuId} </if>" +
            "</set>" +
            "where id = #{id}"+
            "</script>")
	int update(RoleMenu roleMenu);

    @Delete("DELETE FROM sys_role_menu WHERE id = #{id}")
	int remove(Long id);

    @Delete("DELETE FROM sys_role_menu WHERE id in #{ids}")
    int batchRemove(Long[] ids);

    @Select("select menu_id from sys_role_menu where role_id = #{roleId}")
	List<Long> listMenuIdByRoleId(Long roleId);


    @Delete("DELETE FROM sys_role_menu WHERE role_id = #{role_id}")
    int removeByRoleId(Long roleId);
	
	int batchSave(List<RoleMenu> list);
}
