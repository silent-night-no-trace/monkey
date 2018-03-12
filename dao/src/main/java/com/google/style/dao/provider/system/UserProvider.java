package com.google.style.dao.provider.system;

import java.util.Map;

/**
 * @author liangz
 * @date 2018/3/5 17:47
 **/
public class UserProvider {
    /**
     * 获取用户列表
     * @return
     */
    public String getUserList(Map<String,Object> map){
        StringBuffer sb = new StringBuffer();
        Integer id = (Integer) map.get("id");
        String username = (String)map.get("username");
        String name = (String)map.get("name");
        String deptId = (String)map.get("deptId");
        Integer status = (Integer)map.get("status");
        String createBy = (String)map.get("createBy");
        Integer sex = (Integer)map.get("sex");
        String province = (String)map.get("province");
        String city = (String)map.get("city");
        String district = (String)map.get("district");
        //分页使用参数
        Integer offset = (Integer) map.get("offset");
        Integer limit = (Integer)map.get("limit");
        String sort = (String)map.get("sort");
        //排序方式  降序 or 升序
        String order = (String)map.get("order");
        sb.append("SELECT id,\tusername,name,password,dept_id,email,mobile,status,\tcreate_by,\tcreate_time,update_time\t,sex,live_address,province,city,district FROM sys_user WHERE 1= 1");

        if(id!=null){
            sb.append(" AND id  ="+ id);
        }
        if(username!=null&&!"".equals(username)){
            sb.append(" AND username = '"+username+"'");
        }
        if(name!=null&&!"".equals(name)){
            sb.append(" AND name = "+name);

        }
        if(deptId!=null&&!"".equals(deptId)){
            sb.append(" AND dept_id = "+deptId);

        }
        if(status!=null&&!"".equals(status)){
            sb.append(" AND status = "+status);

        }
        if(createBy!=null&&!"".equals(createBy)){
            sb.append(" AND create_by = "+createBy);

        }
        if(sex!=null&&!"".equals(sex)){
            sb.append(" AND sex = "+sex);
        }
        if(province!=null&&!"".equals(province)){
            sb.append(" AND province = "+province);
        }
        if(city!=null&&!"".equals(city)){
            sb.append(" AND city = "+city);
        }
        if(district!=null&&!"".equals(district)){
            sb.append(" AND district = "+district);
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
        return sb.toString();
    }

    public  String getUserCount(Map<String,Object> map){
        StringBuffer sb = new StringBuffer();
        Integer id = (Integer) map.get("id");
        String username = (String)map.get("username");
        String name = (String)map.get("name");
        String deptId = (String )map.get("deptId");
        String status = (String)map.get("status");
        String createBy = (String)map.get("createBy");
        String  sex = (String)map.get("sex");
        String province = (String)map.get("province");
        String city = (String)map.get("city");
        String district = (String)map.get("district");
        sb.append("SELECT count(*) FROM sys_user WHERE 1= 1");

        if(id!=null){
            sb.append(" AND id  ="+ id);
        }
        if(username!=null&&!"".equals(username)){
            sb.append(" AND username = '"+username+"'");
        }
        if(name!=null&&!"".equals(name)){
            sb.append(" AND name = "+name);

        }
        if(deptId!=null&&!"".equals(deptId)){
            sb.append(" AND dept_id = "+deptId);

        }
        if(status!=null&&!"".equals(status)){
            sb.append(" AND status = "+status);

        }
        if(createBy!=null&&!"".equals(createBy)){
            sb.append(" AND create_by = "+createBy);

        }
        if(sex!=null&&!"".equals(sex)){
            sb.append(" AND sex = "+sex);
        }
        if(province!=null&&!"".equals(province)){
            sb.append(" AND province = "+province);
        }
        if(city!=null&&!"".equals(city)){
            sb.append(" AND city = "+city);
        }
        if(district!=null&&!"".equals(district)){
            sb.append(" AND district = "+district);
        }
        return sb.toString();
    }
}
