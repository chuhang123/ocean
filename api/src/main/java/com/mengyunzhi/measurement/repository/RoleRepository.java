package com.mengyunzhi.measurement.repository;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by liming on 17-5-17.
 */
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findOneByName(String name);
    Role findOneByIsAdmin(Boolean isAdmin);
}
