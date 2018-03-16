package com.google.style.dao.mapper.tools;


import java.util.List;
import java.util.Map;

import com.google.style.model.tools.SysLog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * 系统日志
 * 
 * @author liangz
 * @email xx@.com
 * @date 2018-03-14 17:54:27
 */
@Mapper
@Repository
public interface SysLogMapper {

	@Select("select `id`, `user_id`, `username`, `operation`, `time`, `method`, `params`, `ip`, `create_time` from sys_log where id = #{id}")
    SysLog get(Long id);
	
	@Select("<script>" +
	"select * from sys_log " + 
			"<where>" + 
		  		  "<if test=\"id != null and id != ''\">"+ "and id = #{id} " + "</if>" + 
		  		  "<if test=\"userId != null and userId != ''\">"+ "and user_id = #{userId} " + "</if>" + 
		  		  "<if test=\"username != null and username != ''\">"+ "and username = #{username} " + "</if>" + 
		  		  "<if test=\"operation != null and operation != ''\">"+ "and operation = #{operation} " + "</if>" + 
		  		  "<if test=\"time != null and time != ''\">"+ "and time = #{time} " + "</if>" + 
		  		  "<if test=\"method != null and method != ''\">"+ "and method = #{method} " + "</if>" + 
		  		  "<if test=\"params != null and params != ''\">"+ "and params = #{params} " + "</if>" + 
		  		  "<if test=\"ip != null and ip != ''\">"+ "and ip = #{ip} " + "</if>" + 
		  		  "<if test=\"createTime != null and createTime != ''\">"+ "and create_time = #{createTime} " + "</if>" + 
		  			"</where>"+ 
			" <choose>" + 
	            "<when test=\"sort != null and sort.trim() != ''\">" + 
	                "order by ${sort} ${order}" + 
	            "</when>" + 
				"<otherwise>" + 
	                "order by id desc" + 
				"</otherwise>" + 
	        "</choose>"+
			"<if test=\"offset != null and limit != null\">"+
			"limit #{offset}, #{limit}" + 
			"</if>"+
			"</script>")
	List<SysLog> list(Map<String, Object> map);
	
	@Select("<script>" +
	"select count(*) from sys_log " + 
			"<where>" + 
		  		  "<if test=\"id != null and id != ''\">"+ "and id = #{id} " + "</if>" + 
		  		  "<if test=\"userId != null and userId != ''\">"+ "and user_id = #{userId} " + "</if>" + 
		  		  "<if test=\"username != null and username != ''\">"+ "and username = #{username} " + "</if>" + 
		  		  "<if test=\"operation != null and operation != ''\">"+ "and operation = #{operation} " + "</if>" + 
		  		  "<if test=\"time != null and time != ''\">"+ "and time = #{time} " + "</if>" + 
		  		  "<if test=\"method != null and method != ''\">"+ "and method = #{method} " + "</if>" + 
		  		  "<if test=\"params != null and params != ''\">"+ "and params = #{params} " + "</if>" + 
		  		  "<if test=\"ip != null and ip != ''\">"+ "and ip = #{ip} " + "</if>" + 
		  		  "<if test=\"createTime != null and createTime != ''\">"+ "and create_time = #{createTime} " + "</if>" + 
		  			"</where>"+ 
			"</script>")
	int count(Map<String, Object> map);
	
	@Insert("insert into sys_log (`user_id`, `username`, `operation`, `time`, `method`, `params`, `ip`, `create_time`)"
	+ "values (#{userId}, #{username}, #{operation}, #{time}, #{method}, #{params}, #{ip}, #{createTime})")
	int save(SysLog log);
	
	@Update("<script>"+ 
			"update sys_log " + 
					"<set>" + 
		            "<if test=\"id != null\">`id` = #{id}, </if>" + 
                    "<if test=\"userId != null\">`user_id` = #{userId}, </if>" + 
                    "<if test=\"username != null\">`username` = #{username}, </if>" + 
                    "<if test=\"operation != null\">`operation` = #{operation}, </if>" + 
                    "<if test=\"time != null\">`time` = #{time}, </if>" + 
                    "<if test=\"method != null\">`method` = #{method}, </if>" + 
                    "<if test=\"params != null\">`params` = #{params}, </if>" + 
                    "<if test=\"ip != null\">`ip` = #{ip}, </if>" + 
                    "<if test=\"createTime != null\">`create_time` = #{createTime}, </if>" + 
          					"</set>" + 
					"where id = #{id}"+
			"</script>")
	int update(SysLog log);
	
	@Delete("delete from sys_log where id =#{id}")
	int remove(Long id);
	
	@Delete("<script>"+ 
			"delete from sys_log where id in " + 
					"<foreach item=\"id\" collection=\"array\" open=\"(\" separator=\",\" close=\")\">" + 
						"#{id}" + 
					"</foreach>"+
			"</script>")
	int batchRemove(Long[] ids);
}
