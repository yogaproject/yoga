package com.woniu.yoga.user.repository;

import com.woniu.yoga.user.pojo.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: java类作用描述RoleRepository
 * @Author: lxy
 * @time: 2019/4/24 2:21
 */
@Transactional
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByRoleId(Integer roleId);

    Role findByRoleName(String roleName);
}
