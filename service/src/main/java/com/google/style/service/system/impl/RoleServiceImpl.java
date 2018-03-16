package com.google.style.service.system.impl;


import com.google.style.dao.mapper.system.RoleMapper;
import com.google.style.dao.mapper.system.RoleMenuMapper;
import com.google.style.dao.mapper.system.UserMapper;
import com.google.style.dao.mapper.system.UserRoleMapper;
import com.google.style.model.system.Role;
import com.google.style.model.system.RoleMenu;
import com.google.style.service.system.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * 角色管理
 * @author liangz
 * @date  2018/03/14 11:50
 */

@Service
public class RoleServiceImpl implements RoleService {

    public static final String ROLE_ALL_KEY = "\"role_all\"";

    public static final String DEMO_CACHE_NAME = "role";

    @Autowired
    RoleMapper roleMapper;
    @Autowired
    RoleMenuMapper roleMenuMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserRoleMapper userRoleMapper;

    @Override
    public List<Role> list() {
        List<Role> roles = roleMapper.list(new HashMap<>(16));
        return roles;
    }


    @Override
    public List<Role> list(Long userId) {
        List<Long> rolesIds = userRoleMapper.listRoleId(userId);
        List<Role> roles = roleMapper.list(new HashMap<>(16));
        for (Role Role : roles) {
            Role.setRoleSign("false");
            for (Long roleId : rolesIds) {
                if (Objects.equals(Role.getId(), roleId)) {
                    Role.setRoleSign("true");
                    break;
                }
            }
        }
        return roles;
    }
    @Transactional
    @Override
    public int save(Role role) {
        int count = roleMapper.save(role);
        List<Long> menuIds = role.getMenuIds();
        Long roleId = role.getId();
        List<RoleMenu> rms = new ArrayList<>();
        for (Long menuId : menuIds) {
            RoleMenu rmDo = new RoleMenu();
            rmDo.setRoleId(roleId);
            rmDo.setMenuId(menuId);
            rms.add(rmDo);
        }
        roleMenuMapper.removeByRoleId(roleId);
        if (rms.size() > 0) {
            for (RoleMenu roleMenu:rms) {
                roleMenuMapper.save(roleMenu);
            }
        }
        return count;
    }

    @Transactional
    @Override
    public int remove(Long id) {
        int count = roleMapper.remove(id);
        roleMenuMapper.removeByRoleId(id);
        return count;
    }

    @Override
    public Role get(Long id) {
        Role Role = roleMapper.get(id);
        return Role;
    }

    @Override
    public int update(Role role) {
        int r = roleMapper.update(role);
        List<Long> menuIds = role.getMenuIds();
        Long roleId = role.getId();
        roleMenuMapper.removeByRoleId(roleId);
        List<RoleMenu> rms = new ArrayList<>();
        for (Long menuId : menuIds) {
            RoleMenu rmDo = new RoleMenu();
            rmDo.setRoleId(roleId);
            rmDo.setMenuId(menuId);
            rms.add(rmDo);
        }
        if (rms.size() > 0) {
            for (RoleMenu roleMenu:rms) {
                roleMenuMapper.save(roleMenu);
            }
        }
        return r;
    }

    @Override
    public int batchremove(Long[] ids) {
        int r = roleMapper.batchRemove(ids);
        return r;
    }

}
