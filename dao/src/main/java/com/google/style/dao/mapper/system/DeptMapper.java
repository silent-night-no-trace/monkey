package com.google.style.dao.mapper.system;

import com.google.style.dao.provider.DeptProvider;
import com.google.style.model.system.Dept;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 部门管理
 * @author liangz
 * @date 2018-03-05 13:40:39
 */
@Mapper
@Repository
public interface DeptMapper {
	/**
	 * id 查询
	 * @param id id
	 * @return
	 */
	@Select("SELECT * FROM sys_dept WHERE id = #{id}")
	Dept get(Long id);

	@SelectProvider(type =DeptProvider.class ,method = "getDeptList" )
	List<Dept> list(Map<String, Object> map);

    @SelectProvider(type =DeptMapper.class ,method = "count" )
    int count(Map<String, Object> map);

    /**
     * save
     * @param dept dept
     * @return
     */
    @Insert("INSERT INTO sys_dept (id,parent_id,name,order_num,del_flag) VALUES(null,#{parentId},#{name},#{orderNum},#{delFlag})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
	int save(Dept dept);

    @Update("UPDATE sys_dept SET parent_id = #{parentId} ,name = #{name} ,order_num = #{orderNum} ,del_flag = #{delFlag} WHERE id =#{id}")
	int update(Dept dept);

	/**
	 * 删除 主键删除
	 */
	@Delete("DELETE from sys_dept where id = #{deptId}\n")
	int delete(Long deptId);

	@Delete("DELETE from sys_dept where id = in{deptIds}")
	int batchRemove(Long[] deptIds);

    /**
     * 获取部门
     * @return
     */
	@Select("select DISTINCT parent_id from sys_dept\n")
	Long[] listParentDept();

    /**
     * 获取部门对应用户数量
     * @param deptId
     * @return
     */
	@Select("\t\tselect count(*) from sys_user where id = #{deptId}\n")
	int getDeptUserNumber(Long deptId);
}
