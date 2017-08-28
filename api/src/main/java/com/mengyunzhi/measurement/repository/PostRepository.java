package com.mengyunzhi.measurement.repository;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by liming on 17-5-17.
 */
public interface PostRepository extends CrudRepository<Post, Long> {
    Post getByName(String name);
    // 获取数据表中的第一条数据
    Post findTopByOrderByIdAsc();
    Post findByName(String name);
}
