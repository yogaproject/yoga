package com.woniu.yoga.user.service.serviceImpl;

import com.woniu.yoga.user.pojo.Role;
import com.woniu.yoga.user.repository.RoleRepository;
import com.woniu.yoga.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * @Description: java类作用描述RoleServiceImpl
 * @Author: lxy
 * @time: 2019/4/24 2:14
 */
@Service
@Repository
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;


    @Override
    public Role findByRoleId(Integer roleId) {
        return roleRepository.findByRoleId(roleId);
    }

    @Override
    public Role findByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }
}
