package com.woniu.yoga.user.service;

import com.woniu.yoga.user.pojo.Role;

/**
 * @Description: java类作用描述RoleService
 * @Author: lxy
 * @time: 2019/4/24 2:12
 */
public interface RoleService {

    Role findByRoleId(Integer roleId);

    Role findByRoleName(String roleName);
}
