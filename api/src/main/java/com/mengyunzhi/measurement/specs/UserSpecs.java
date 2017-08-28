package com.mengyunzhi.measurement.specs;

import com.mengyunzhi.measurement.repository.District;
import com.mengyunzhi.measurement.repository.User;
import org.apache.log4j.Logger;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;

/**
 * Created by panjie on 17/8/15.
 * 用户
 */
public class UserSpecs {
    final static Logger logger = Logger.getLogger(UserSpecs.class.getName());
    public static Specification<User> base(final Map<String, Object> map) {
        return new Specification<User>() {
            private Predicate predicate = null;
            private CriteriaBuilder criteriaBuilder;

            // 设置and谓语.注意，这里只能设置and关系的谓语，如果谓语为OR，则需要手动设置
            private void andPredicate(Predicate predicate) {
                if (null != predicate) {
                    if (null == this.predicate) {
                        this.predicate = predicate;
                    } else {
                        this.predicate = this.criteriaBuilder.and(this.predicate, predicate);
                    }
                }
            }

            /*
             * @param root: 代表的查询的实体类
             * @param query：可以从中得到Root对象，即告知JPA Criteria查询要查询哪一个实体类，
             * 还可以来添加查询条件，还可以结合EntityManager对象得到最终查询的TypedQuery 对象
             * @Param cb:criteriaBuilder对象，用于创建Criteria相关的对象工程，当然可以从中获取到predicate类型
             * @return:代表一个查询条件
             * 参考地址(入门)：http://blog.csdn.net/luckyzhoustar/article/details/49362951
             * 参考地址(本示例代码参考教程)：https://my.oschina.net/u/2434456/blog/596938
             * 官方文档：
             * https://docs.spring.io/spring-data/jpa/docs/1.11.6.RELEASE/api/org/springframework/data/jpa/domain/Specification.html
             * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#specifications
             */
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {
                // 设置CriteriaBuilder，用于合并谓语
                this.criteriaBuilder = criteriaBuilder;

                if (null != map.get("districts")) {
                    logger.info("传入了区域信息districts");
                    List<District> districts = (List<District>) map.get("districts");
                    if (districts.size() > 0) {
                        logger.info("传入的区域大小不为0");
                        logger.info("设置查询条件 IN");
                        CriteriaBuilder.In<Long> in = criteriaBuilder.in(root.join("department").join("district").get("id").as(Long.class));
                        logger.info("为IN添加数组");
                        for (District district : districts) {
                            in.value(district.getId());
                        }
                        this.andPredicate(in);
                    }
                }

                if (null != map.get("departmentTypeId")) {
                    logger.info("传入了部门类型");
                    this.andPredicate(
                            criteriaBuilder.equal(root.join("department").join("departmentType").get("id").as(Long.class), map.get("departmentTypeId")));
                }

                if (null != map.get("status")) {
                    logger.info("传入了状态信息");
                    this.andPredicate(
                            criteriaBuilder.equal(root.get("status").as(Integer.class), map.get("status")));
                }

                if (null != map.get("departmentName")) {
                    logger.info("传入了部门名称");
                    this.andPredicate(
                            criteriaBuilder.like(root.join("department").get("name").as(String.class), map.get("departmentName") + "%")
                    );
                }

                //将上面的谓词添加的查询准则中去
                if (null != this.predicate) {
                    criteriaQuery.where(criteriaBuilder.and(this.predicate));
                }

                return criteriaQuery.getRestriction();
            }
        };
    }
}
