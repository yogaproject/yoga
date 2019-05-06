package com.woniu.yoga.user.service;

import com.woniu.yoga.user.pojo.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: java类作用描述RoleService
 * @Author: lxy
 * @time: 2019/4/24 2:12
 */
@Service
@Transactional
public interface RoleService {

    Role findByRoleId(Integer roleId);

    Role findByRoleName(String roleName);
}
