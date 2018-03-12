package com.google.style.dao.provider.system;

import java.util.Map;

/**
 * @author liangz
 * @date 2018/3/5 13:43
 * 系统菜单 provider
 **/
public class MenuProvider {

    /**
     * 菜单列表
     * @param map
     * @return
     */
    public  String getMenuList(Map<String, Object> map){
        StringBuffer sb = new StringBuffer();
        Integer id = (Integer)map.get("id");
        Integer parentId = (Integer)map.get("parentId");
        String  parentIds = (String)map.get("parentIds");
        String name = (String)map.get("name");
        String url = (String)map.get("url");
        String permission = (String)map.get("permission");
        Integer type = (Integer)map.get("type");
        String icon = (String)map.get("icon");
        Integer orderNum = (Integer)map.get("orderNum");
        String createTime = (String)map.get("createTime");
        String updateTime = (String)map.get("updateTime");
        //分页使用参数
        Integer offset = (Integer) map.get("offset");
        Integer limit = (Integer)map.get("limit");
        String sort = (String)map.get("sort");
        //排序方式  降序 or 升序
        String order = (String)map.get("order");
        sb.append("SELECT id, parent_id ,parent_ids ,name,url,permission,type,icon,order_num,create_time,update_time FROM sys_menu");

        if(id!=null&&!"".equals(id)){
            sb.append(" AND id = "+id );
        }
        if(parentId!=null&&!"".equals(parentId)){
            sb.append(" AND parent_id = "+parentId );
        }
        if(parentIds!=null&&!"".equals(parentIds)){
            sb.append(" AND parent_ids = "+parentIds );
        }
        if(name!=null&&!"".equals(name)){
            sb.append(" AND name = "+name );
        }
        if(url!=null&&!"".equals(url)){
            sb.append(" AND url = "+url );
        }
        if(permission!=null&&!"".equals(permission)){
            sb.append(" AND permission = "+permission );
        }
        if(type!=null&&!"".equals(type)){
            sb.append(" AND type = "+type );
        }
        if(icon!=null&&!"".equals(icon)){
            sb.append(" AND icon = "+icon );
        }
        if(orderNum!=null&&!"".equals(orderNum)){
            sb.append(" AND orderNum = "+orderNum );
        }

        if(createTime!=null&&!"".equals(createTime)){
            sb.append(" AND createTime = "+createTime );
        }
        if(updateTime!=null&&!"".equals(updateTime)){
            sb.append(" AND updateTime = "+updateTime );
        }
        //分页参数
        //分页参数
        if(sort!=null&&!"".equals(sort)){
            sb.append(" ORDER BY "+sort +" DESC ");
        }else {
            //未传排序字段 默认使用id 排序
            sb.append(" ORDER BY id DESC");
        }
        if(offset!=null&&limit!=null){
            sb.append(" limit "+offset+" , "+limit);
        }
        return sb.toString() ;
    }

    /**
     * 统计按条件查询数目
     * @param map
     * @return
     */
    public String count(Map<String, Object> map){
        StringBuffer sb = new StringBuffer();
        Integer id = (Integer)map.get("id");
        Integer parentId = (Integer)map.get("parentId");
        String  parentIds = (String)map.get("parentIds");
        String name = (String)map.get("name");
        String url = (String)map.get("url");
        String permission = (String)map.get("permission");
        Integer type = (Integer)map.get("type");
        String icon = (String)map.get("icon");
        Integer orderNum = (Integer)map.get("orderNum");
        String createTime = (String)map.get("createTime");
        String updateTime = (String)map.get("updateTime");

        sb.append("SELECT count(*) FROM sys_menu");

        if(id!=null&&!"".equals(id)){
            sb.append(" AND id = "+id );
        }
        if(parentId!=null&&!"".equals(parentId)){
            sb.append(" AND parent_id = "+parentId );
        }
        if(parentIds!=null&&!"".equals(parentIds)){
            sb.append(" AND parent_ids = "+parentIds );
        }
        if(name!=null&&!"".equals(name)){
            sb.append(" AND name = "+name );
        }
        if(url!=null&&!"".equals(url)){
            sb.append(" AND url = "+url );
        }
        if(permission!=null&&!"".equals(permission)){
            sb.append(" AND permission = "+permission );
        }
        if(type!=null&&!"".equals(type)){
            sb.append(" AND type = "+type );
        }
        if(icon!=null&&!"".equals(icon)){
            sb.append(" AND icon = "+icon );
        }
        if(orderNum!=null&&!"".equals(orderNum)){
            sb.append(" AND order_num = "+orderNum );
        }

        if(createTime!=null&&!"".equals(createTime)){
            sb.append(" AND create_time = "+createTime );
        }
        if(updateTime!=null&&!"".equals(updateTime)){
            sb.append(" AND update_time = "+updateTime );
        }
        return  sb.toString();
    }

}
