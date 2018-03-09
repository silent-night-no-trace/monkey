package com.google.style.service.system;

import com.google.style.model.system.Role;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RoleService {

	Role get(Long id);

	List<Role> list();

	int save(Role role);

	int update(Role role);

	int remove(Long id);

	List<Role> list(Long userId);

	int batchremove(Long[] ids);
}
