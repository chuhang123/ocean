package com.mengyunzhi.measurement.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by liming on 17-5-17.
 * 用户
 */
public interface UserRepository extends CrudRepository<User, Long> , JpaSpecificationExecutor {
    User findOneByUsername(String name);
    Page<User> findAll(Pageable pageable);
}
