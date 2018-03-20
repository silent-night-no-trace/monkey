package com.google.style.dao.mapper.system;

import com.google.style.dao.provider.system.UserProvider;
import com.google.style.model.system.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 用户dao操作类
 * @author liangz
 * @date 2018/03/13 11:52
 */
@Mapper
@Repository
public interface UserMapper {

    @Select("SELECT id,\tusername,name,password,dept_id,dept_name,email,mobile,pic_id,status,\tcreate_by,\tcreate_time,update_time\t,sex,hobby,live_address,province,city,district\t FROM sys_user WHERE id = #{userId}")
	User get(Long userId);

    @SelectProvider(type = UserProvider.class,method = "getUserList")
    @Results({@Result(column = "id",property = "id"),@Result(column = "username",property = "username"),@Result(column = "name",property = "name"),
            @Result(column = "password",property = "password"),@Result(column = "dept_id",property = "deptId"), @Result(column = "dept_name",property = "deptName"),
            @Result(column = "email",property = "email"), @Result(column = "mobile",property = "mobile"),@Result(column = "pic_id",property = "picId"),
            @Result(column = "status",property = "status"),@Result(column = "create_by",property = "createBy"), @Result(column = "create_time",property = "createTime"),
            @Result(column = "update_time",property = "updateTime"),@Result(column = "sex",property = "sex"),@Result(column = "hobby",property = "hobby"),
            @Result(column = "live_address",property = "liveAddress"),@Result(column = "province",property = "province"),@Result(column = "city",property = "city"),
            @Result(column = "district",property = "district")})
    List<User> list(Map<String, Object> map);

    @SelectProvider(type = UserProvider.class,method = "getUserCount")
    int count(Map<String, Object> map);

    @Insert("INSERT INTO sys_user (id,\tusername,name,password,dept_id,dept_name,email,mobile,pic_id,status,\tcreate_by,\tcreate_time,update_time\t,sex,hobby,live_address,province,city,district\t) VALUES (NULL,#{username},#{name},#{password},#{deptId},#{deptName},#{email},#{mobile},#{picId},#{status},#{createBy},#{createTime},#{updateTime},#{sex},#{hobby},#{liveAddress},#{province},#{city},#{district})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int save(User user);

    @Update("<script>"+
            "update sys_user " +
            "<set>" +
            "<if test=\"username != null\">`username` = #{username}, </if>" +
            "<if test=\"name != null\">`name` = #{name}, </if>" +
            "<if test=\"password != null\">`password` = #{password}, </if>" +
            "<if test=\"deptId != null\">`dept_id` = #{deptId} ,</if>" +
            "<if test=\"deptName != null\">`dept_name` = #{deptName} ,</if>" +
            "<if test=\"email != null\">`email` = #{email}, </if>" +
            "<if test=\"mobile != null\">`mobile` = #{mobile} ,</if>" +
            "<if test=\"picId != null\">`pic_id` = #{picId}, </if>" +
            "<if test=\"status != null\">`status` = #{status} ,</if>" +
            "<if test=\"createBy != null\">`create_by` = #{createBy} ,</if>" +
            "<if test=\"updateTime != null\">`update_time` = #{updateTime} ,</if>" +
            "<if test=\"sex != null\">`sex` = #{sex}, </if>" +
            "<if test=\"hobby != null\">`hobby` = #{hobby}, </if>" +
            "<if test=\"liveAddress != null\">`live_address` = #{liveAddress}, </if>" +
            "<if test=\"province != null\">`province` = #{province}, </if>" +
            "<if test=\"city != null\">`city` = #{city} , </if>" +
            "<if test=\"district != null\">`district` = #{district} </if>" +
            "</set>" +
            "where id = #{id}"+
            "</script>")
	int update(User user);

	@Delete("DELETE FROM sys_user WHERE id = #{userId}")
	int remove(Long userId);

    @Delete("DELETE FROM sys_user WHERE id IN #{userIds}")
    int batchRemove(Long[] userIds);

	@Select("\t\t\t\tselect DISTINCT dept_id from sys_user\n")
	Long[] listAllDept();

}
