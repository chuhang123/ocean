package com.mengyunzhi.measurement.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by liming on 17-5-18.
 */
public interface CheckResultRepository extends CrudRepository<CheckResult, Long> {

    //获取根检定结果根节点
    List<CheckResult> findAllByParentCheckResultIsNull();
    //获取某检定结果的孩子
    List<CheckResult> findAllByParentCheckResult(CheckResult checkResult);
}
