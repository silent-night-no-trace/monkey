package com.google.style.dao.provider.system;

import com.google.style.model.system.UserRole;

import java.util.List;
import java.util.Map;

/**
 * @author liangz
 * @date 2018/3/6 10:17
 * 用户角色
 **/
public class UserRoleProvider {

    /**
     * 获取用户角色 关系 列表
     * @param map
     * @return
     */
    public String getUserRoleList(Map<String, Object> map){
        StringBuffer sb = new StringBuffer();
        Integer userId = (Integer)map.get("userId");
        Integer roleId = (Integer)map.get("roleId");
        //分页使用参数
        Integer offset = (Integer) map.get("offset");
        Integer limit = (Integer)map.get("limit");
        String sort = (String)map.get("sort");
        //排序方式  降序 or 升序
        String order = (String)map.get("order");
        sb.append("SELECT id ,user_id,role_id FROM sys_user_role WHERE 1=1");
        if(roleId!=null){
            sb.append(" AND role_id = "+roleId);
        }
        if(userId!=null){
            sb.append(" AND user_id = "+userId);
        }

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

    public String getCount(Map<String,Object> map){
        StringBuffer sb = new StringBuffer();
        Integer userId = (Integer)map.get("userId");
        Integer roleId = (Integer)map.get("roleId");
        sb.append("SELECT id ,user_id,role_id FROM sys_user_role WHERE 1=1");
        if(roleId!=null){
            sb.append(" AND role_id = "+roleId);
        }
        if(userId!=null){
            sb.append(" AND user_id = "+userId);
        }
        return sb.toString();
    }

    /**
     * 废弃
     * @param list
     * @return
     */
    public String batchSave(List<UserRole> list){
        StringBuffer sb = new StringBuffer();
        sb.append("\t\tINSERT INTO sys_user_role(user_id, role_id) values\n");
        for (UserRole ur:list) {
            sb.append("( "+ur.getUserId()+","+ur.getRoleId()+")");
        }
        return sb.toString();
    }
}
