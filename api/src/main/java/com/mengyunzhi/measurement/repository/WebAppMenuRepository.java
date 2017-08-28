package com.mengyunzhi.measurement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by 安强 on 2017/6/1.
 * 前台菜单  实体仓库
 */
public interface WebAppMenuRepository extends CrudRepository<WebAppMenu, Long>, JpaRepository<WebAppMenu, Long>{
    /**
     * 直接使用SQL语句进行查询
     * @param userId 用户ID
     * @return
     * 注意：需要用right join的地方，我们需要使用where a.xx = b.xx来解决。
     * 感谢：https://stackoverflow.com/questions/21683666/hql-left-outer-join-path-expected-for-join
     * 官方文件未对left join ,right join进行说明：
     * 参考: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
     * 我们将查询出的数据直接给了实体WebAppMenu，此时，要求实体WebAppMenu拥有相应的构造函数 (Long id)
     * @author panjie
     */
    @Query(value = "SELECT new WebAppMenu(w.webAppMenu.id) FROM UserRole u, WebAppMenuRole w where w.role.id = u.role.id and u.user.id=?1")
    List<WebAppMenu> findAllByUserId(Long userId);

    WebAppMenu findOneByRouteName(String routeName);

}
