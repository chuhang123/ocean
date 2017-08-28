package com.mengyunzhi.measurement.specs;

import com.mengyunzhi.measurement.repository.District;
import com.mengyunzhi.measurement.repository.InstrumentCheckInfo;
import org.apache.log4j.Logger;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by chuhang on 17-8-14.
 */
public class MandatoryInstrumentCheckInfoSpecs {

    private final static Logger logger = Logger.getLogger(MandatoryInstrumentCheckInfoSpecs.class.getName());

    /**
     * 新建一个方法，这个方法的返回值类型为：org.springframework.data.jpa.domain.Specification <MandatoryInstrument>
     * 这个方法接收一个参数，由于只对这个参数进行读取，不进行写入，所以设置为:final，即该变量不可变。
     */
    public static Specification<InstrumentCheckInfo> base(final Map<String, Object> map) {
        /**
         * 直接返回了一个继承了 Specification 接口的对象，由于 Specification 接口中声明了
         * Predicate toPredicate(Root<T> var1, CriteriaQuery<?> var2, CriteriaBuilder var3);
         * 方法。所以在这里，我们需要有下面的这个函数：
         * public Predicate toPredicate(Root<MandatoryInstrument> root, CriteriaQuery<?> query,
         CriteriaBuilder criteriaBuilder)
         * 其实这我们先建立一个类，继承Specification接口，然后实现它的方法。然后我们在这里直接return new XXXX<>()意思是一样的。
         */
        return new Specification<InstrumentCheckInfo>() {

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

            public Predicate toPredicate(Root<InstrumentCheckInfo> root, CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                logger.info("设置私有变量");
                this.criteriaBuilder = criteriaBuilder;

                logger.info("1.设置区域查询条件");
                if (null != map.get("districts")) {
                    logger.info("传入区域信息");
                    List<District> districts = (List<District>) map.get("districts");
                    if (districts.size() > 0) {
                        logger.info("传入的区域大小不为0");
                        logger.info("设置查询条件IN");
                        CriteriaBuilder.In<Long> in = criteriaBuilder.in(root.join("mandatoryInstrument").join("department").join("district").get("id").as(Long.class));
                        logger.info("为IN添加数组中");
                        for (District district : districts) {
                            in.value(district.getId());
                        }
                        this.andPredicate(in);
                    }
                }

                logger.info("2.器具用户id");
                if (map.get("departmentId") != null) {
                    logger.info("器具所属部门");
                    Predicate predicate1 = criteriaBuilder.equal(root.join("mandatoryInstrument").join("department").get("id").as(Long.class), Long.valueOf(String.valueOf(map.get("departmentId"))));
                    this.andPredicate(predicate1);
                }

                logger.info("3.强检器具名称");
                if (map.get("name") != null) {
                    Predicate namePredicate = criteriaBuilder.like(root.join("mandatoryInstrument").get("name").as(String.class), String.valueOf(map.get("name")) + "%");
                    this.andPredicate(namePredicate);
                }

                logger.info("4.获取年度信息");
                if (map.get("year") != null) {
                    try {
                        logger.info("获取年度的起止日期和截止日期");
                        Integer year = Integer.parseInt(map.get("year").toString());
                        String startDate = String.valueOf(year) + "-01-01";
                        String endDate = String.valueOf(year + 1) + "-01-01";

                        logger.info("获取日期格式");
                        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");

                        logger.info("建立谓语，checkdate字段应大于startDate");
                        Predicate startDatePredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("checkDate"), formatter.parse(startDate));
                        logger.info("建立谓语，checkdate字段应小于endDate");
                        Predicate endDatePredicate = criteriaBuilder.lessThanOrEqualTo(root.get("checkDate"), formatter.parse(endDate));

                        this.andPredicate(startDatePredicate);
                        this.andPredicate(endDatePredicate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                logger.info("5强检器具id");
                if (map.get("mandatoryInstrumentId") != null) {
                    logger.info("传入了id, 则只按ID进行查询");
                    Long mandatoryInstrumentId = Long.valueOf((String.valueOf(map.get("mandatoryInstrumentId"))));
                    Predicate idPredicate = criteriaBuilder.equal(root.join("mandatoryInstrument").get("id").as(Long.class), mandatoryInstrumentId);
                    this.andPredicate(idPredicate);
                }


                logger.info("6.器具类别ID");
                Predicate predicate1 = null;
                if (map.get("instrumentTypeId") != null) {
                    logger.info("传入的器具类别ID不为null");
                    Long instrumentTypeId = Long.valueOf((String.valueOf(map.get("instrumentTypeId"))));
                    predicate1 = criteriaBuilder.equal(root.join("mandatoryInstrument").join("instrumentType").get("id").as(Long.class), instrumentTypeId);
                } else if (map.get("instrumentFirstLevelTypeId") != null) {
                    logger.info("7.传入的器具类别ID为NULL，一级器具类别ID不为null");
                    Long instrumentFirstLevelTypeId = Long.valueOf(String.valueOf(map.get("instrumentFirstLevelTypeId")));
                    predicate1 = criteriaBuilder.equal(root.join("mandatoryInstrument").join("instrumentType").join("instrumentFirstLevelType").get("id").as(Long.class), instrumentFirstLevelTypeId);
                } else if (map.get("disciplineId") != null) {
                    logger.info("8.只传了学科类别id");
                    Long disciplineId = Long.valueOf(String.valueOf(map.get("disciplineId")));
                    predicate1 = criteriaBuilder.equal(
                            root.join("mandatoryInstrument").join("instrumentType").join("instrumentFirstLevelType").join("discipline").get("id").as(Long.class),
                            disciplineId);
                }

                // 把这个谓语与上一个合并，得到一个合并后的大谓语。
                this.andPredicate(predicate1);

                logger.info("9.检定机构id");
                if (map.get("checkDepartmentId") != null) {
                    logger.info("器具审核部门");
                    Predicate checkDepartmentPredicate = criteriaBuilder.equal(root.join("mandatoryInstrument").join("checkDepartment").get("id").as(Long.class), Long.valueOf(String.valueOf(map.get("checkDepartmentId"))));
                    this.andPredicate(checkDepartmentPredicate);
                }

                logger.info("10证书编号");
                if (map.get("certificateNum") != null) {
                    Predicate certificateNumPredicate = criteriaBuilder.like(root.get("certificateNum").as(String.class), String.valueOf(map.get("certificateNum")) + "%");
                    this.andPredicate(certificateNumPredicate);
                }
                logger.info("11检定结果");
                if (map.get("checkResultId") != null) {
                    Predicate checkResultPredicate = criteriaBuilder.equal(root.join("checkResult").get("id").as(Long.class), Long.valueOf(String.valueOf(map.get("checkResultId"))));
                    this.andPredicate(checkResultPredicate);
                }
                logger.info("12精确度等级");
                if (map.get("accuracyDisplayNameId") != null) {
                    Long accuracyDisplayNameId = Long.valueOf((String.valueOf(map.get("accuracyDisplayNameId"))));
                    Predicate accuracyDisplayNamePredicate1 = criteriaBuilder.equal(root.join("mandatoryInstrument").join("instrumentType").join("accuracyDisplayName").get("id").as(Long.class), accuracyDisplayNameId);
                    this.andPredicate(accuracyDisplayNamePredicate1);
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
