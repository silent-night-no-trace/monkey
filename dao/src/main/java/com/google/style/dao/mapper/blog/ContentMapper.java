package com.google.style.dao.mapper.blog;


import java.util.List;
import java.util.Map;

import com.google.style.model.blog.Content;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * 文章内容
 * 
 * @author liangz
 * @email xx@.com
 * @date 2018-03-21 10:46:28
 */
@Mapper
@Repository
public interface ContentMapper {

	@Select("select `id`, `title`, `slug`, `created`, `modified`, `content`, `type`, `tags`, `categories`, `hits`, `comments_num`, `allow_comment`, `allow_ping`, `allow_feed`, `status`, `author`, `create_time`, `update_time` from blog_content where id = #{id}")
	Content get(Long id);
	
	@Select("<script>" +
	"select * from blog_content " + 
			"<where>" + 
		  		  "<if test=\"id != null and id != ''\">"+ "and id = #{id} " + "</if>" + 
		  		  "<if test=\"title != null and title != ''\">"+ "and title = #{title} " + "</if>" + 
		  		  "<if test=\"slug != null and slug != ''\">"+ "and slug = #{slug} " + "</if>" + 
		  		  "<if test=\"created != null and created != ''\">"+ "and created = #{created} " + "</if>" + 
		  		  "<if test=\"modified != null and modified != ''\">"+ "and modified = #{modified} " + "</if>" + 
		  		  "<if test=\"content != null and content != ''\">"+ "and content = #{content} " + "</if>" + 
		  		  "<if test=\"type != null and type != ''\">"+ "and type = #{type} " + "</if>" + 
		  		  "<if test=\"tags != null and tags != ''\">"+ "and tags = #{tags} " + "</if>" + 
		  		  "<if test=\"categories != null and categories != ''\">"+ "and categories = #{categories} " + "</if>" + 
		  		  "<if test=\"hits != null and hits != ''\">"+ "and hits = #{hits} " + "</if>" + 
		  		  "<if test=\"commentsNum != null and commentsNum != ''\">"+ "and comments_num = #{commentsNum} " + "</if>" + 
		  		  "<if test=\"allowComment != null and allowComment != ''\">"+ "and allow_comment = #{allowComment} " + "</if>" + 
		  		  "<if test=\"allowPing != null and allowPing != ''\">"+ "and allow_ping = #{allowPing} " + "</if>" + 
		  		  "<if test=\"allowFeed != null and allowFeed != ''\">"+ "and allow_feed = #{allowFeed} " + "</if>" + 
		  		  "<if test=\"status != null and status != ''\">"+ "and status = #{status} " + "</if>" + 
		  		  "<if test=\"author != null and author != ''\">"+ "and author = #{author} " + "</if>" + 
		  		  "<if test=\"createTime != null and createTime != ''\">"+ "and create_time = #{createTime} " + "</if>" + 
		  		  "<if test=\"updateTime != null and updateTime != ''\">"+ "and update_time = #{updateTime} " + "</if>" + 
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
	List<Content> list(Map<String, Object> map);
	
	@Select("<script>" +
	"select count(*) from blog_content " + 
			"<where>" + 
		  		  "<if test=\"id != null and id != ''\">"+ "and id = #{id} " + "</if>" + 
		  		  "<if test=\"title != null and title != ''\">"+ "and title = #{title} " + "</if>" + 
		  		  "<if test=\"slug != null and slug != ''\">"+ "and slug = #{slug} " + "</if>" + 
		  		  "<if test=\"created != null and created != ''\">"+ "and created = #{created} " + "</if>" + 
		  		  "<if test=\"modified != null and modified != ''\">"+ "and modified = #{modified} " + "</if>" + 
		  		  "<if test=\"content != null and content != ''\">"+ "and content = #{content} " + "</if>" + 
		  		  "<if test=\"type != null and type != ''\">"+ "and type = #{type} " + "</if>" + 
		  		  "<if test=\"tags != null and tags != ''\">"+ "and tags = #{tags} " + "</if>" + 
		  		  "<if test=\"categories != null and categories != ''\">"+ "and categories = #{categories} " + "</if>" + 
		  		  "<if test=\"hits != null and hits != ''\">"+ "and hits = #{hits} " + "</if>" + 
		  		  "<if test=\"commentsNum != null and commentsNum != ''\">"+ "and comments_num = #{commentsNum} " + "</if>" + 
		  		  "<if test=\"allowComment != null and allowComment != ''\">"+ "and allow_comment = #{allowComment} " + "</if>" + 
		  		  "<if test=\"allowPing != null and allowPing != ''\">"+ "and allow_ping = #{allowPing} " + "</if>" + 
		  		  "<if test=\"allowFeed != null and allowFeed != ''\">"+ "and allow_feed = #{allowFeed} " + "</if>" + 
		  		  "<if test=\"status != null and status != ''\">"+ "and status = #{status} " + "</if>" + 
		  		  "<if test=\"author != null and author != ''\">"+ "and author = #{author} " + "</if>" + 
		  		  "<if test=\"createTime != null and createTime != ''\">"+ "and create_time = #{createTime} " + "</if>" + 
		  		  "<if test=\"updateTime != null and updateTime != ''\">"+ "and update_time = #{updateTime} " + "</if>" + 
		  			"</where>"+ 
			"</script>")
	int count(Map<String, Object> map);
	
	@Insert("insert into blog_content (`title`, `slug`, `created`, `modified`, `content`, `type`, `tags`, `categories`, `hits`, `comments_num`, `allow_comment`, `allow_ping`, `allow_feed`, `status`, `author`, `create_time`, `update_time`)"
	+ "values (#{title}, #{slug}, #{created}, #{modified}, #{content}, #{type}, #{tags}, #{categories}, #{hits}, #{commentsNum}, #{allowComment}, #{allowPing}, #{allowFeed}, #{status}, #{author}, #{createTime}, #{updateTime})")
	int save(Content content);
	
	@Update("<script>"+ 
			"update blog_content " + 
					"<set>" + 
		            "<if test=\"id != null\">`id` = #{id}, </if>" + 
                    "<if test=\"title != null\">`title` = #{title}, </if>" + 
                    "<if test=\"slug != null\">`slug` = #{slug}, </if>" + 
                    "<if test=\"created != null\">`created` = #{created}, </if>" + 
                    "<if test=\"modified != null\">`modified` = #{modified}, </if>" + 
                    "<if test=\"content != null\">`content` = #{content}, </if>" + 
                    "<if test=\"type != null\">`type` = #{type}, </if>" + 
                    "<if test=\"tags != null\">`tags` = #{tags}, </if>" + 
                    "<if test=\"categories != null\">`categories` = #{categories}, </if>" + 
                    "<if test=\"hits != null\">`hits` = #{hits}, </if>" + 
                    "<if test=\"commentsNum != null\">`comments_num` = #{commentsNum}, </if>" + 
                    "<if test=\"allowComment != null\">`allow_comment` = #{allowComment}, </if>" + 
                    "<if test=\"allowPing != null\">`allow_ping` = #{allowPing}, </if>" + 
                    "<if test=\"allowFeed != null\">`allow_feed` = #{allowFeed}, </if>" + 
                    "<if test=\"status != null\">`status` = #{status}, </if>" + 
                    "<if test=\"author != null\">`author` = #{author}, </if>" + 
                    "<if test=\"createTime != null\">`create_time` = #{createTime}, </if>" + 
                    "<if test=\"updateTime != null\">`update_time` = #{updateTime}, </if>" + 
          					"</set>" + 
					"where id = #{id}"+
			"</script>")
	int update(Content content);
	
	@Delete("delete from blog_content where id =#{id}")
	int remove(Long id);
	
	@Delete("<script>"+ 
			"delete from blog_content where id in " + 
					"<foreach item=\"id\" collection=\"array\" open=\"(\" separator=\",\" close=\")\">" + 
						"#{id}" + 
					"</foreach>"+
			"</script>")
	int batchRemove(Long[] ids);
}
