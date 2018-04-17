package com.baidu.demo.dao;

import com.baidu.demo.model.AdminMenuDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liangz
 * @date 2018/4/12 17:50
 * 菜单 操作
 **/
@Mapper
@Repository
public interface AdminMenuMapper {

	/**
	 * 获取用户 对象的菜单
	 * @param userId userId
	 * @return List<AdminMenuDO>
	 */
	@Select("SELECT\n" +
			"\tam.*\n" +
			"FROM\n" +
			"\tadmin_menu am\n" +
			"LEFT JOIN admin_role_menu arm ON am.id = arm.menu_id\n" +
			"LEFT JOIN admin_user_role aur ON arm.role_id = aur.role_id\n" +
			"WHERE\n" +
			"\taur.user_id = #{userId}")
	List<AdminMenuDO> getUserMenuByUserId(long userId);
}
