package com.beetech.mvcspringboot.repository;

import com.beetech.mvcspringboot.constants.RoleEnum;
import com.beetech.mvcspringboot.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findRoleByName(RoleEnum name);
}
