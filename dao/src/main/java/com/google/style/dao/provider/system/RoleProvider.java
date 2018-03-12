package com.google.style.dao.provider.system;

import java.sql.Timestamp;
import java.util.Map;

/**
 * @author liangz
 * @date 2018/3/5 15:08
 * role provider
 **/
public class RoleProvider {

    /**
     *获取角色列表
     * @param map
     * @return
     */
    public String getRoleList(Map<String,Object> map){
         StringBuffer sb = new StringBuffer();
         Long id = (Long)map.get("id");
         String roleName = (String)map.get("roleName");
         String roleSign = (String)map.get("roleSign");
         String createBy = (String)map.get("createBy");
         Timestamp createTime = (Timestamp)map.get("createTime");
         Timestamp updateTime = (Timestamp)map.get("updateTime");
         String remark = (String)map.get("remark");
        //分页使用参数
        Integer offset = (Integer) map.get("offset");
        Integer limit = (Integer)map.get("limit");
        String sort = (String)map.get("sort");
        //排序方式  降序 or 升序
        String order = (String)map.get("order");

        sb.append("SELECT id,role_name,role_sign,create_by,create_time,update_time,remark FROM sys_role WHERE 1=1");
        if(id!=null){
            sb.append(" AND id = "+id);
        }
        if(roleName!=null&&!"".equals(roleName)){
            sb.append(" AND role_name =" +roleName);
        }
        if(roleSign!=null&&!"".equals(roleSign)){
            sb.append(" AND role_sign = "+roleSign);
        }
        if(createBy!=null&&!"".equals(createBy)){
            sb.append(" AND create_by = "+createBy);
        }
        if(createTime!=null&&!"".equals(createTime)){
            sb.append(" AND create_time = "+createTime);
        }

        if(updateTime!=null&&!"".equals(updateTime)){
            sb.append(" AND update_time = "+updateTime);
        }
        if(remark!=null&&!"".equals(remark)){
            sb.append(" AND remark = "+remark);
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

    /**
     * 按条件统计
     * @param map map
     * @return
     */
    public String count(Map<String,Object>map){

        StringBuffer sb = new StringBuffer();
        Long id = (Long)map.get("id");
        String roleName = (String)map.get("roleName");
        String roleSign = (String)map.get("roleSign");
        String createBy = (String)map.get("createBy");
        Timestamp createTime = (Timestamp)map.get("createTime");
        Timestamp updateTime = (Timestamp)map.get("updateTime");
        String remark = (String)map.get("remark");
        sb.append("SELECT count(*) FROM sys_role WHERE 1=1");
        if(id!=null){
            sb.append(" AND id = "+id);
        }
        if(roleName!=null&&!"".equals(roleName)){
            sb.append(" AND role_name =" +roleName);
        }
        if(roleSign!=null&&!"".equals(roleSign)){
            sb.append(" AND role_sign = "+roleSign);
        }
        if(createBy!=null&&!"".equals(createBy)){
            sb.append(" AND create_by = "+createBy);
        }
        if(createTime!=null&&!"".equals(createTime)){
            sb.append(" AND create_time = "+createTime);
        }

        if(updateTime!=null&&!"".equals(updateTime)){
            sb.append(" AND update_time = "+updateTime);
        }
        if(remark!=null&&!"".equals(remark)){
            sb.append(" AND remark = "+remark);
        }
        return sb.toString();
    }
}
