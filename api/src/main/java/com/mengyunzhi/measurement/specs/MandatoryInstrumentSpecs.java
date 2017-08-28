package com.mengyunzhi.measurement.specs;

import com.mengyunzhi.measurement.repository.District;
import com.mengyunzhi.measurement.repository.MandatoryInstrument;
import org.apache.log4j.Logger;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;


/**
 * Created by panjie on 17/8/11.
 * 强制检定器具多条件查询
 */
public class MandatoryInstrumentSpecs {
    private final static Logger logger = Logger.getLogger(MandatoryInstrumentSpecs.class.getName());

    /**
     * 新建一个方法，这个方法的返回值类型为：org.springframework.data.jpa.domain.Specification <MandatoryInstrument>
     * 这个方法接收一个参数，由于只对这个参数进行读取，不进行写入，所以设置为:final，即该变量不可变。
     */
    public static Specification<MandatoryInstrument> base(final Map<String, Object> map) {
        /**
         * 直接返回了一个继承了 Specification 接口的对象，由于 Specification 接口中声明了
         * Predicate toPredicate(Root<T> var1, CriteriaQuery<?> var2, CriteriaBuilder var3);
         * 方法。所以在这里，我们需要有下面的这个函数：
         * public Predicate toPredicate(Root<MandatoryInstrument> root, CriteriaQuery<?> query,
         CriteriaBuilder criteriaBuilder)
         * 其实这我们先建立一个类，继承Specification接口，然后实现它的方法。然后我们在这里直接return new XXXX<>()意思是一样的。
         */
        return new Specification<MandatoryInstrument>() {
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
            public Predicate toPredicate(Root<MandatoryInstrument> root, CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                /**
                 * predicate 译为：谓语。 比如：器具名称为XXX， 则"为"是predicate；器具名称包含XXX，则“包含”是predicate
                 * criteria 译为：标准、准则。用以创建标准、准则的对象，我们称为：criteriaBuilder
                 * like是标准的一种：除此以外的，我们常用的还有equal, notEqual, gt, ge, lt, le, between, isNull, isEmpty, isNotEmpty, isMember, isNotMember, size 等
                 * 这些标准是jpa 2.0 的 criteria 规定的。我们可以在这找到他们的身影：http://www.objectdb.com/java/jpa/query/criteria#Criteria_Query_Expressions_
                 * root前面讲了，代表正在查询的实体。get指获取实体的字段名. as是指做为什么类型. 所以：root.get("name").as(String.class) = 找到实体中的name字段，并做为字符串来处理。
                 * like和SQL中的like相同，用于模糊查找.like(要查找的字段，查找的规则)
                 * 所以String.valueOf(jsonObject.get("name")) + "%"是这样一个规则：以传入的name开始的，比如查找name = zhangsan, 则实体中name值为：zhangsan, zhangsan1, zhangsan12 都符合这条规则
                 * 使用String.valueOf(jsonObject.get("name"))是针对jsonObject的返回值做的强制转换处理.
                 */
                logger.info("设置私有变量");
                this.criteriaBuilder = criteriaBuilder;
                if (map.get("id") != null) {
                    logger.info("传入了id, 则只按ID进行查询");
                    Predicate idPredicate = criteriaBuilder.equal(root.get("id").as(Long.class), (Long) map.get("id"));
                    this.andPredicate(idPredicate);
                }

                logger.info("未传入ID，则参考其它查询条件");
                if (map.get("name") != null) {
                    Predicate namePredicate = criteriaBuilder.like(root.get("name").as(String.class), String.valueOf(map.get("name")) + "%");
                    this.andPredicate(namePredicate);
                }

                if (null != map.get("districts")) {
                    logger.info("传入了区域信息");
                    List<District> districts = (List<District>) map.get("districts");
                    if (districts.size() > 0) {
                        logger.info("传入的区域大小不为0");
                        logger.info("设置查询条件IN");
                        CriteriaBuilder.In<Long> in = criteriaBuilder.in(root.join("department").join("district").get("id").as(Long.class));
                        logger.info("为IN添加数组");
                        for (District district : districts) {
                            in.value(district.getId());
                        }
                        this.andPredicate(in);
                    }
                }

                // 声明第二个谓语
                Predicate predicate1 = null;
                if (map.get("instrumentTypeId") != null) {
                    logger.info("传入的器具类别ID不为null");
                    // 使用Long.valueOf((String.valueOf(xxx)); 来达到最终转化为Long类型的目的
                    Long instrumentTypeId = Long.valueOf((String.valueOf(map.get("instrumentTypeId"))));
                    // 建立第二个 谓语。当前实体的 instrumentType 关联实体中的 id 字段值为 传入的器具类型id
                    predicate1 = criteriaBuilder.equal(root.join("instrumentType").get("id").as(Long.class), instrumentTypeId);
                } else if (map.get("instrumentFirstLevelTypeId") != null) {
                    logger.info("传入的器具类别ID为NULL，一级器具类别ID不为null");
                    Long instrumentFirstLevelTypeId = Long.valueOf(String.valueOf(map.get("instrumentFirstLevelTypeId")));
                    // 建立第二个 谓语
                    predicate1 = criteriaBuilder.equal(root.join("instrumentType").join("instrumentFirstLevelType").get("id").as(Long.class), instrumentFirstLevelTypeId);
                } else if (map.get("disciplineId") != null) {
                    logger.info("只传了学科类别id");
                    Long disciplineId = Long.valueOf(String.valueOf(map.get("disciplineId")));
                    // 建立第二个 谓语
                    predicate1 = criteriaBuilder.equal(
                            root.join("instrumentType").join("instrumentFirstLevelType").join("discipline").get("id").as(Long.class),
                            disciplineId);
                }

                // 把这个谓语与上一个合并，得到一个合并后的大谓语。
                this.andPredicate(predicate1);

                if (map.get("audit") != null) {
                    logger.info("查询器具是否经过审核");
                    Predicate predicate2 = criteriaBuilder.equal(root.get("audit").as(Boolean.class), Boolean.valueOf(String.valueOf(map.get("audit"))));
                    this.andPredicate(predicate2);
                }

                if (map.get("checkDepartmentId") != null) {
                    logger.info("器具审核部门");
                    Predicate checkDepartmentPredicate = criteriaBuilder.equal(root.join("checkDepartment").get("id").as(Long.class), map.get("checkDepartmentId"));
                    this.andPredicate(checkDepartmentPredicate);
                }

                if (map.get("departmentId") != null) {
                    logger.info("器具所属部门");
                    Predicate predicate3 = criteriaBuilder.equal(root.join("department").get("id").as(Long.class), Long.valueOf(String.valueOf(map.get("departmentId"))));
                    this.andPredicate(predicate3);
                }

                /**
                 * 将前面得到的谓语，添加到 查询准则 中。其实除了能添加where外，还可以添加orderBy, groupBy等信息。
                 * 官方文档：http://docs.oracle.com/javaee/6/api/javax/persistence/criteria/CriteriaQuery.html
                 */
                if (null != this.predicate) {
                    query.where(criteriaBuilder.and(this.predicate));
                }

                /**
                 * 获取最后的谓语信息。由于，我们在query中，仅仅添加了.where(谓语信息),所以以下代码相当于return predicate;
                 *  但当我们并不仅仅设置where信息时，就不一样了
                 */
                return query.getRestriction();
            }
        };
    }
}
