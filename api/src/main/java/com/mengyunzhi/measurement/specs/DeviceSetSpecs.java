package com.mengyunzhi.measurement.specs;


import com.mengyunzhi.measurement.repository.DeviceSet;
import net.sf.json.JSONObject;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.logging.Logger;

/**
 * Created by liming on 17-8-13.
 * 标准装置多条件查询
 */
public class DeviceSetSpecs {
    private final static Logger logger = Logger.getLogger(DeviceSetSpecs.class.getName());

    /**
     * 新建一个方法，这个方法的返回值类型为：org.springframework.data.jpa.domain.Specification <DeviceSet>
     * 这个方法接收一个参数，由于只对这个参数进行读取，不进行写入，所以设置为:final，即该变量不可变。
     */
    public static Specification<DeviceSet> base(final JSONObject jsonObject) {
        return new Specification<DeviceSet>() {
            @Override
            public Predicate toPredicate(Root<DeviceSet> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                Predicate predicate = criteriaBuilder.like(root.get("code").as(String.class), String.valueOf(jsonObject.get("code")) + "%");
                Predicate predicate2 = criteriaBuilder.like(root.get("name").as(String.class), String.valueOf(jsonObject.get("name")) + "%");
                //两个谓词合成一个大谓词
                predicate = criteriaBuilder.and(predicate, predicate2);

                //声明第三个谓词
                Predicate predicate1 = null;
                if (jsonObject.get("departmentId") != null) {
                    logger.info("传入的技术机构ID不为null");
                    //String 转化为Long
                    Long departmentId = Long.valueOf((String.valueOf(jsonObject.get("departmentId"))));
                    //建立第三个谓语
                    predicate1 = criteriaBuilder.equal(root.join("department").get("id").as(Long.class), departmentId);
                } else if (jsonObject.get("districtId") != null){
                    logger.info("传入的区域ID不为null");
                    //String转化为Long
                    Long districtId = Long.valueOf((String.valueOf(jsonObject.get("districtId"))));
                    //建立第三个谓语
                    predicate1 = criteriaBuilder.equal(root.join("department").join("district").get("id").as(Long.class), districtId);
                }

                //如果第三个谓语成立，则建立合成一个大谓语
                if (predicate1 != null) {
                    predicate = criteriaBuilder.and(predicate, predicate1);
                }

                //查询
                criteriaQuery.where(predicate);

                //返回
                return criteriaQuery.getRestriction();
            }
        };
    }
}
