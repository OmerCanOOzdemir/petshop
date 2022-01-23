package com.example.petshop.dao;

import com.example.petshop.entities.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role,Integer> {
    Role getRoleByName(String name);
}
