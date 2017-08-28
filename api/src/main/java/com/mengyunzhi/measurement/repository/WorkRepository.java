package com.mengyunzhi.measurement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by liming on 17-5-22.
 * 工作
 */
public interface WorkRepository extends CrudRepository<Work, Long> {
    /**
     * 获取某个部门拥有的 某类（ApplyTableName）审核列表
     * 由于每类申请都会新建一个类继承于Apply，然后指定一个TableName。
     * 所以我们在取一些工作流信息时，需要指定是获取的哪种。
     * 比如，我们的强检器具审核，对应的实体审核类为：MandatoryInstrumentApply
     * 指定的tableName为：MandatoryInstrument
     * 则取某个部门的强检器具审核时，将ApplyTableName指定为MandatoryInstrument
     * @param department 部门
     * @param pageable 分页
import static org.junit.Assert.*;     * 参考:
     * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.at-query
     * https://stackoverflow.com/questions/4884249/java-jpa-query-with-specified-inherited-type
     * https://stackoverflow.com/questions/43489490/java-lang-illegalargumentexceptioneither-use-param-on-all-parameters-except-pa
     */
    @Query("select DISTINCT w.apply from #{#entityName} w where w.auditDepartment = :department and w.apply.class = :applyClassName")
    Page<Apply> findDistinctApplyByDepartmentAndApplyTableName(@Param("department") Department department, @Param("applyClassName") String applyClassName, Pageable pageable);

    @Query("SELECT work from #{#entityName} work where work.id = (select max(work1.id) from #{#entityName} work1 where work.apply = work1.apply and work1.auditDepartment = :department and work1.apply.class = :applyClassName) order by work.id desc")
    Page<Work> pageByDepartmentAndApplyClassNameOrderByIdDescGroupByApply(@Param("department") Department department, @Param("applyClassName") String applyClassName, Pageable pageable);

    Work findOneByPreWork(Work work);
    List<Work> findAllByApplyId(Long applyId);
}
