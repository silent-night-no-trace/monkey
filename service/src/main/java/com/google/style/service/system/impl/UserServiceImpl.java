package com.google.style.service.system.impl;


import com.google.style.dao.mapper.system.DeptMapper;
import com.google.style.dao.mapper.system.UserMapper;
import com.google.style.dao.mapper.system.UserRoleMapper;
import com.google.style.model.Tree;
import com.google.style.model.system.Dept;
import com.google.style.model.system.User;
import com.google.style.model.system.UserRole;
import com.google.style.service.system.UserService;
import com.google.style.utils.BuildTree;
import com.google.style.utils.FileUtil;
import com.google.style.utils.MD5Utils;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.*;

@Transactional
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserMapper userMapper;
	@Autowired
	UserRoleMapper userRoleMapper;
	@Autowired
	DeptMapper deptMapper;
//	@Autowired
//	private FileService sysFileService;
//	@Autowired
//	private BootdoConfig bootdoConfig;
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Override
	public User get(Long id) {
		List<Long> roleIds = userRoleMapper.listRoleId(id);
		User user = userMapper.get(id);
		user.setDeptName(deptMapper.get(user.getDeptId()).getName());
		user.setRoleIds(roleIds);
		return user;
	}

	@Override
	public List<User> list(Map<String, Object> map) {
		return userMapper.list(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		return userMapper.count(map);
	}

	@Transactional
	@Override
	public int save(User user) {
		int count = userMapper.save(user);
		Long userId = user.getId();
		List<Long> roles = user.getRoleIds();
		userRoleMapper.removeByUserId(userId);
		List<UserRole> list = new ArrayList<>();
		for (Long roleId : roles) {
			UserRole ur = new UserRole();
			ur.setUserId(userId);
			ur.setRoleId(roleId);
			list.add(ur);
		}
		if (list.size() > 0) {
			userRoleMapper.batchSave(list);
		}
		return count;
	}

	@Override
	public int update(User user) {
		int r = userMapper.update(user);
		Long userId = user.getId();
		List<Long> roles = user.getRoleIds();
		userRoleMapper.removeByUserId(userId);
		List<UserRole> list = new ArrayList<>();
		for (Long roleId : roles) {
			UserRole ur = new UserRole();
			ur.setUserId(userId);
			ur.setRoleId(roleId);
			list.add(ur);
		}
		if (list.size() > 0) {
			userRoleMapper.batchSave(list);
		}
		return r;
	}

	@Override
	public int remove(Long userId) {
		userRoleMapper.removeByUserId(userId);
		return userMapper.remove(userId);
	}

	@Override
	public boolean exit(Map<String, Object> params) {
		boolean exit;
		exit = userMapper.list(params).size() > 0;
		return exit;
	}

	@Override
	public Set<String> listRoles(Long userId) {
		return null;
	}

//	@Override
//	public int resetPwd(UserVO userVO,UserDO userDO) throws Exception {
//		if(Objects.equals(userVO.getUserDO().getUserId(),userDO.getUserId())){
//			if(Objects.equals(MD5Utils.encrypt(userDO.getUsername(),userVO.getPwdOld()),userDO.getPassword())){
//				userDO.setPassword(MD5Utils.encrypt(userDO.getUsername(),userVO.getPwdNew()));
//				return userMapper.update(userDO);
//			}else{
//				throw new Exception("输入的旧密码有误！");
//			}
//		}else{
//			throw new Exception("你修改的不是你登录的账号！");
//		}
//	}
//	@Override
//	public int adminResetPwd(UserVO userVO) throws Exception {
//		UserDO userDO =get(userVO.getUserDO().getUserId());
//		if("admin".equals(userDO.getUsername())){
//			throw new Exception("超级管理员的账号不允许直接重置！");
//		}
//		userDO.setPassword(MD5Utils.encrypt(userDO.getUsername(), userVO.getPwdNew()));
//		return userMapper.update(userDO);
//
//
//	}

	@Transactional
	@Override
	public int batchremove(Long[] userIds) {
		int count = userMapper.batchRemove(userIds);
		userRoleMapper.batchRemoveByUserId(userIds);
		return count;
	}

	@Override
	public Tree<Dept> getTree() {
		List<Tree<Dept> > trees = new ArrayList<Tree<Dept>>();
		List<Dept> depts = deptMapper.list(new HashMap<String, Object>(16));
		Long[] pDepts = deptMapper.listParentDept();
		Long[] uDepts = userMapper.listAllDept();
		Long[] allDepts = (Long[]) ArrayUtils.addAll(pDepts, uDepts);
		for (Dept dept : depts) {
			if (!ArrayUtils.contains(allDepts, dept.getId())) {
				continue;
			}
			Tree<Dept> tree = new Tree<Dept>();
			tree.setId(dept.getId().toString());
			tree.setParentId(dept.getParentId().toString());
			tree.setText(dept.getName());
			Map<String, Object> state = new HashMap<>(16);
			state.put("opened", true);
			state.put("mType", "dept");
			tree.setState(state);
			trees.add(tree);
		}
		List<User> users = userMapper.list(new HashMap<String, Object>(16));
		for (User user : users) {
			Tree<Dept> tree = new Tree<Dept>();
			tree.setId(user.getId().toString());
			tree.setParentId(user.getDeptId().toString());
			tree.setText(user.getName());
			Map<String, Object> state = new HashMap<>(16);
			state.put("opened", true);
			state.put("mType", "user");
			tree.setState(state);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<Dept> t = BuildTree.build(trees);
		return t;
	}

	@Override
	public int updatePersonal(User user) {
		return userMapper.update(user);
	}

//    @Override
//    public Map<String, Object> updatePersonalImg(MultipartFile file, String avatar_data, Long userId) throws Exception {
//		String fileName = file.getOriginalFilename();
//		fileName = FileUtil.renameToUUID(fileName);
//		FileDO sysFile = new FileDO(FileType.fileType(fileName), "/files/" + fileName, new Date());
//		//获取图片后缀
//		String prefix = fileName.substring((fileName.lastIndexOf(".")+1));
//		String[] str=avatar_data.split(",");
//		//获取截取的x坐标
//		int x = (int)Math.floor(Double.parseDouble(str[0].split(":")[1]));
//		//获取截取的y坐标
//		int y = (int)Math.floor(Double.parseDouble(str[1].split(":")[1]));
//		//获取截取的高度
//		int h = (int)Math.floor(Double.parseDouble(str[2].split(":")[1]));
//		//获取截取的宽度
//		int w = (int)Math.floor(Double.parseDouble(str[3].split(":")[1]));
//		//获取旋转的角度
//		int r = Integer.parseInt(str[4].split(":")[1].replaceAll("}", ""));
//		try {
//			BufferedImage cutImage = ImageUtils.cutImage(file,x,y,w,h,prefix);
//			BufferedImage rotateImage = ImageUtils.rotateImage(cutImage, r);
//			ByteArrayOutputStream out = new ByteArrayOutputStream();
//			boolean flag = ImageIO.write(rotateImage, prefix, out);
//			//转换后存入数据库
//			byte[] b = out.toByteArray();
//			FileUtil.uploadFile(b, bootdoConfig.getUploadPath(), fileName);
//		} catch (Exception e) {
//			throw  new Exception("图片裁剪错误！！");
//		}
//		Map<String, Object> result = new HashMap<>();
//		if(sysFileService.save(sysFile)>0){
//			UserDO userDO = new UserDO();
//			userDO.setUserId(userId);
//			userDO.setPicId(sysFile.getId());
//			if(userMapper.update(userDO)>0){
//				result.put("url",sysFile.getUrl());
//			}
//		}
//		return result;
//    }

}
