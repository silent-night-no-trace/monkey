package com.google.style.service.system;

import com.google.style.model.Tree;
import com.google.style.model.system.Dept;
import com.google.style.model.system.User;
import com.google.style.model.system.UserVO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 用户 service
 * @author liangz
 */
@Service
public interface UserService {
	/**
	 * 用户获取
	 * @param id id
 	 * @return user
	 */
	User get(Long id);

	/**
	 * list
	 * @param map map
 	 * @return list<User>
	 */
	List<User> list(Map<String, Object> map);

	/**
	 * count
	 * @param map map
	 * @return int
	 */
	int count(Map<String, Object> map);

	/**
	 * save
	 * @param user user
	 * @return int
	 */
	int save(User user);

	/**
	 * update
	 * @param user user
	 * @return int
	 */
	int update(User user);

	/**
	 * remove
	 * @param userId userId
	 * @return int
	 */
	int remove(Long userId);

	/**
	 * batchRemove
	 * @param userIds userIds
	 * @return int
	 */
	int batchRemove(Long[] userIds);

	/**
	 * exit
	 * @param params params
	 * @return boolean
	 */
	boolean exit(Map<String, Object> params);

	/**
	 * list roles
	 * @param userId userId
	 * @return set<String>
	 */
	Set<String> listRoles(Long userId);

	/**
	 * reset password
	 * @param userVO userVo
	 * @param user user
	 * @return int
	 * @throws Exception
	 */
	int resetPwd(UserVO userVO, User user) throws Exception;

	/**
	 * admin reset password
	 * @param userVO userVO
	 * @return int
	 * @throws Exception
	 */
	int adminResetPwd(UserVO userVO) throws Exception;

	/**
	 * 获取 tree 菜单
	 * @return Tree<Dept>
	 */
	Tree<Dept> getTree();

	/**
	 * 更新个人信息
	 * @param user
	 * @return
	 */
	int updatePersonal(User user);

	/**
	 * 更新个人图片
	 * @param file 图片
	 * @param avatar_data 裁剪信息
	 * @param userId 用户ID
	 * @throws Exception
	 */
    Map<String, Object> updatePersonalImg(MultipartFile file, String avatar_data, Long userId) throws Exception;
}
