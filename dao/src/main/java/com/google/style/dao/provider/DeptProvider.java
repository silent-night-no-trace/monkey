package com.google.style.dao.provider;

import com.google.style.model.system.Dept;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * @author liangz
 * @date 2018/3/2 18:09
 *  部门相关 provider
 **/
@Slf4j
public class DeptProvider {
    /**
     * 获取 list 部门
     * @param map
     * @return
     */
    public String getDeptList(Map<String, Object> map){
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT `id`,`parent_id`,`name`,`order_num`,`del_flag` FROM\n" +
                "\t\tsys_dept WHERE 1=1");
        Long id = (Long) map.get("id");
        Long parentId = (Long)map.get("parentId");
        String name = (String)map.get("name");
        Integer orderNum = (Integer)map.get("orderNum");
        Integer delFlag = (Integer)map.get("delFlag");
        //分页使用参数
        Integer offset = (Integer) map.get("offset");
        Integer limit = (Integer)map.get("limit");
        String sort = (String)map.get("sort");
        //排序方式  降序 or 升序
        String order = (String)map.get("order");
        if(id!=null&&!"".equals(id)){
            sb.append(" AND id = "+id );
        }
        if(parentId!=null&&!"".equals(parentId)){
            sb.append(" AND parent_id = "+parentId );
        }
        if(name!=null&&!"".equals(name)){
            sb.append(" AND name = "+name );
        }
        if(orderNum!=null&&!"".equals(orderNum)){
            sb.append(" AND order_num = "+id );
        }if(delFlag!=null&&!"".equals(delFlag)){
            sb.append(" AND del_flag = "+delFlag );
        }
        //分页参数
        if(offset!=null&&limit!=null){
            sb.append(" limit "+offset+" , "+limit);
        }
        if(sort!=null&&!"".equals(sort)){
            sb.append(" ORDER BY "+sort +" "+order );
        }else {
            //未传排序字段 默认使用id 排序
            sb.append(" ORDER BY id DESC");
        }
        return sb.toString();
    }

    /**
     * count 计算按照条件的统计数量
     * @param map
     * @return
     */
    public String count(Map<String, Object> map){
        StringBuffer sb = new StringBuffer();

        sb.append("SELECT count(*) FROM\n" +
                "\t\tsys_dept WHERE 1=1");
        //获取map存放的参数
        Long id = (Long) map.get("id");
        Long parentId = (Long)map.get("parentId");
        String name = (String)map.get("name");
        Integer orderNum = (Integer)map.get("orderNum");
        Integer delFlag = (Integer)map.get("delFlag");

        if(id!=null&&!"".equals(id)){
            sb.append(" AND id = "+id );
        }
        if(parentId!=null&&!"".equals(parentId)){
            sb.append(" AND parent_id = "+parentId );
        }
        if(name!=null&&!"".equals(name)){
            sb.append(" AND name = "+name );
        }
        if(orderNum!=null&&!"".equals(orderNum)){
            sb.append(" AND order_num = "+id );
        }if(delFlag!=null&&!"".equals(delFlag)){
            sb.append(" AND del_flag = "+delFlag );
        }

        return sb.toString();
    }
}
