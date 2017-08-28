package com.mengyunzhi.measurement.specs;

import com.mengyunzhi.measurement.repository.DeviceInstrument;
import com.mengyunzhi.measurement.repository.DeviceSet;
import com.mengyunzhi.measurement.repository.District;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by liming on 17-8-14.
 * 授权检定项目支持多条件查询
 */
public class DeviceInstrumentSpecs {
    private final static Logger logger = Logger.getLogger(DeviceInstrumentSpecs.class.getName());

    /**
     * 这个方法有一个参数,因为不需要读取,不进行写入,所以设置为final
     * @param map
     * @return
     */
    public static Specification<DeviceInstrument> base(final Map<String, Object> map) {
        return new Specification<DeviceInstrument>() {

            private Predicate predicate = null;         //谓词
            private CriteriaBuilder criteriaBuilder;
            private void andPredicate(Predicate predicate) {
                if (null != predicate) {
                    if (null == this.predicate) {
                        this.predicate = predicate;
                    } else {
                        this.predicate = this.criteriaBuilder.and(this.predicate, predicate);
                    }
                }
            }

            /**
             * @param root                  //代表的查询的实体
             * @param criteriaQuery         //查询
             * @param criteriaBuilder
             * @return:代表一个查询条件
             */
            @Override
            public Predicate toPredicate(Root<DeviceInstrument> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                logger.info("设置私有变量");
                this.criteriaBuilder = criteriaBuilder;

                if (null != map.get("deviceSetId")) {
                    logger.info("如果有标准装置ID,只对标准装置进行查询");
                    //String 转化为 Long
                    Long deviceSetId = Long.valueOf((String.valueOf(map.get("deviceSetId"))));

                    //@ManyToMany
                    Predicate deviceSetIdPredicate = criteriaBuilder.equal(root.joinSet("deviceSets").get("id").as(Long.class), deviceSetId);
                    this.andPredicate(deviceSetIdPredicate);
                } else {
                    logger.info("如果没有传入标准装置ID, 参考其他条件");
                    if (map.get("name") != null) {          //标准装置名称
                        Predicate namePredicate = criteriaBuilder.like(root.joinSet("deviceSets").get("name").as(String.class), String.valueOf(map.get("name")) + "%");
                        this.andPredicate(namePredicate);
                    }

                    if (null != map.get("districts")) {
                        logger.info("传入区域信息");
                        List<District> districts = (List<District>) map.get("districts");

                        if (districts.size() > 0) {
                            logger.info("设置查询条件IN");
                            CriteriaBuilder.In<Long> in = criteriaBuilder.in(root.joinSet("deviceSets").join("department").join("district").get("id").as(Long.class));
                            logger.info("为IN添加数组");
                            for (District district: districts) {
                                in.value(district.getId());
                            }
                            this.andPredicate(in);
                        }
                    }

                    //声明第二个谓语
                    Predicate predicate1 = null;
                    if (map.get("instrumentTypeId") != null) {
                        logger.info("传入的器具类别ID不为null");
                        Long instrumentTypeId = Long.valueOf((String.valueOf(map.get("instrumentTypeId"))));
                        //建立第二个 谓语
                        predicate1 = criteriaBuilder.equal(root.join("measureScale").join("instrumentType").get("id"), instrumentTypeId);
                    } else if (map.get("instrumentFirstLevelTypeId") != null) {
                        logger.info("传入的器具一级类别ID不为null");
                        Long instrumentFirstLevelTypeId = Long.valueOf((String.valueOf(map.get("instrumentFirstLevelTypeId"))));
                        //建立第二个 谓语
                        predicate1 = criteriaBuilder.equal(root.join("measureScale").join("instrumentType").join("instrumentFirstLevelType").get("id"), instrumentFirstLevelTypeId);
                    } else if (map.get("disciplineId") != null) {
                        logger.info("传入的学科类别不为null");
                        Long disciplineId = Long.valueOf((String.valueOf(map.get("disciplineId"))));
                        //建立第二个谓语
                        predicate1 = criteriaBuilder.equal(root.join("measureScale").join("instrumentType").join("instrumentFirstLevelType").join("discipline").get("id"), disciplineId);
                    }

                    if (predicate1 != null) {
                        this.andPredicate(predicate1);
                    }

                    if (null != map.get("departmentId")) {
                        logger.info("授权检定项目所属部门");
                        Long departmentId = Long.valueOf((String.valueOf(map.get("departmentId"))));
                        Predicate predicate2 = criteriaBuilder.equal(root.joinSet("deviceSets").join("department").get("id"), departmentId);
                        this.andPredicate(predicate2);
                    }
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
