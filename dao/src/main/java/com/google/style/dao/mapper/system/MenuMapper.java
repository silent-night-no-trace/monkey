package com.google.style.dao.mapper.system;

import com.google.style.dao.provider.system.MenuProvider;
import com.google.style.model.system.Menu;
import com.google.style.model.system.MenuVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 菜单管理
 * @author liangz
 * @date 2017-10-03 09:45:09
 */
@Mapper
@Repository
public interface MenuMapper {

	@Select("SELECT * FROM sys_menu WHERE id = #{menuId}")
	Menu get(Long menuId);

	@SelectProvider(type = MenuProvider.class ,method = "getMenuList")
	@Results({@Result(column = "id",property = "id"),@Result(column = "parent_id",property = "parentId"),@Result(column = "parent_ids",property = "parentIds"),
            @Result(column = "name",property = "name"),@Result(column = "url",property = "url"),@Result(column = "permission",property = "permission"),
            @Result(column = "type",property = "type"),@Result(column = "icon",property = "icon"),@Result(column = "order_num",property = "orderNum"),
            @Result(column = "create_time",property = "createTime"),@Result(column = "update_time",property = "updateTime")})
	List<Menu> list(Map<String, Object> map);

	@SelectProvider(type =  MenuProvider.class ,method = "count")
	int count(Map<String, Object> map);

	@Insert("INSERT INTO sys_menu (id, parent_id ,parent_ids ,name,url,permission,type,icon,order_num,create_time,update_time) VALUES (NULL, #{parentId} ,#{parentIds} ,#{name},#{url},#{permission},#{type},#{icon},#{orderNum},#{createTime},#{updateTime}) ")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int save(Menu menu);

	@Update("<script>"+
			"update sys_menu " +
			"<set>" +
			"<if test=\"parentId != null\">`parent_id` = #{parentId}, </if>" +
			"<if test=\"parentIds != null\">`parent_ids` = #{parentIds}, </if>" +
			"<if test=\"name != null\">`name` = #{name}, </if>" +
			"<if test=\"url != null\">`url` = #{url} ,</if>" +
			"<if test=\"permission != null\">`permission` = #{permission} ,</if>" +
			"<if test=\"type != null\">`type` = #{type} ,</if>" +
			"<if test=\"icon != null\">`icon` = #{icon} ,</if>" +
			"<if test=\"orderNum != null\">`order_num` = #{orderNum} ,</if>" +
			"<if test=\"createTime != null\">`create_time` = #{createTime} ,</if>" +
			"<if test=\"updateTime != null\">`update_time` = #{updateTime} </if>" +
			"</set>" +
			"where id = #{id}"+
			"</script>")
	int update(Menu menu);

	@Delete("DELETE sys_menu WHERE id = #{menuId}")
	int delete(Long menuId);

	@Delete("DELETE sys_menu WHERE id in #{menuIds}")
	int batchRemove(Long[] menuIds);

    /**
     * 用户对应的菜单
     * @param id
     * @return
     */
	@Select("SELECT DISTINCT\n" +
            "\tm.id,\n" +
            "\tparent_id,\n" +
            "\tname,\n" +
            "\turl,\n" +
            "\tpermission,\n" +
            "\t`type`,\n" +
            "\ticon,\n" +
            "\torder_num,\n" +
            "\tcreate_time,\n" +
            "\tupdate_time\n" +
            "FROM\n" +
            "\tsys_menu m\n" +
            "LEFT JOIN sys_role_menu rm ON m.id = rm.menu_id\n" +
            "LEFT JOIN sys_user_role ur ON rm.role_id = ur.role_id\n" +
            "WHERE\n" +
            "\tur.user_id = #{id}\n" +
            "AND m.type IN (0, 1)\n" +
            "ORDER BY\n" +
            "\tm.order_num")
	@Results({@Result(column = "id", property = "id"),@Result(column = "parent_id",property = "parentId"),@Result(column = "name",property = "name"),
            @Result(column = "url",property = "url"),@Result(column = "permission",property = "permission"),@Result(column = "type",property = "type"),
            @Result(column = "icon",property = "icon"),@Result(column = "order_num",property = "orderNum"),@Result(column = "create_time",property = "createTime"),
            @Result(column = "update_time",property = "updateTime")})
	List<MenuVo> listMenuByUserId(Long id);

    /**
     * 用户对应的权限
     * @param id
     * @return
     */
	@Select("SELECT DISTINCT\n" +
            "\tm.permission\n" +
            "FROM\n" +
            "\tsys_menu m\n" +
            "LEFT JOIN sys_role_menu rm ON m.id = rm.menu_id\n" +
            "LEFT JOIN sys_user_role ur ON rm.role_id = ur.role_id\n" +
            "WHERE\n" +
            "\tur.user_id = #{id}")
	List<String> listUserPerms(Long id);
}
