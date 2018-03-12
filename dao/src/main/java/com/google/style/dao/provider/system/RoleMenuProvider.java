package com.google.style.dao.provider.system;

import com.google.style.model.system.RoleMenu;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

/**
 * @author liangz
 * @date 2018/3/5 16:48
 *  角色菜单  provider
 **/
public class RoleMenuProvider {

    public String getRoleMenuList(Map<String,Object>map){
        StringBuffer sb = new StringBuffer();
        Integer id = (Integer)map.get("id");
        Integer menuId = (Integer)map.get("menuId");
        Integer roleId = (Integer)map.get("roleId");
        //分页使用参数
        Integer offset = (Integer) map.get("offset");
        Integer limit = (Integer)map.get("limit");
        String sort = (String)map.get("sort");
        //排序方式  降序 or 升序
        String order = (String)map.get("order");
        sb.append("SELECT id ,menu_id,role_id FROM sys_role_menu WHERE 1=1");
        if(id!=null){
            sb.append(" AND id = "+id);
        }
        if(menuId!=null){
            sb.append(" AND menu_id ="+menuId);
        }
        if(roleId!=null){
            sb.append(" AND role_id ="+roleId);
        }
        //分页参数
        //分页参数
        if(sort!=null&&!"".equals(sort)){
            sb.append(" ORDER BY "+sort +" "+order );
        }else {
            //未传排序字段 默认使用id 排序
            sb.append(" ORDER BY id DESC");
        }
        if(offset!=null&&limit!=null){
            sb.append(" limit "+offset+" , "+limit);
        }
        return  sb.toString();
    }

    public String count(Map<String,Object>map){
        StringBuffer sb = new StringBuffer();
        Integer id = (Integer)map.get("id");
        Integer menuId = (Integer)map.get("menuId");
        Integer roleId = (Integer)map.get("roleId");
        sb.append("SELECT count(*) FROM sys_role_menu WHERE 1=1");
        if(id!=null){
            sb.append(" AND id = "+id);
        }
        if(menuId!=null){
            sb.append(" AND menu_id ="+menuId);
        }
        if(roleId!=null){
            sb.append(" AND role_id ="+roleId);
        }
        return sb.toString();
    }

    /**
     * TODO
     * @param list
     * @return
     */
    public String batchSave(List<RoleMenu> list){
        if(list.size()>0){
            for (RoleMenu roleMenu:list) {
                save(roleMenu);
            }
        }
        return "";
    }

        public String save(final RoleMenu roleMenu) {
            try {
                return new SQL() {
                    {
                        INSERT_INTO("sys_role_menu");

                        VALUES("role_id", "#{roleId}");

                        VALUES("menu_id", "#{menuId}");

                    }
                }.toString();
            } catch (Exception e) {
                return "";
            }

    }
}
