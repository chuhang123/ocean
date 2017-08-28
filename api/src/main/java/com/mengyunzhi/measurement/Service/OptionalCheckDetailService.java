package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.OptionalCheckDetail;
import com.mengyunzhi.measurement.repository.OptionalIntegrated;

import java.util.List;

/**
 * Created by zhangjiahao on 2017/5/9.
 * 非强检器具-检定信息
 */
public interface OptionalCheckDetailService {

    //保存方法
    OptionalCheckDetail save(OptionalCheckDetail optionalCheckDetail);

    //获取数据表中的所有数据
    List<OptionalCheckDetail> getAll();

    //根据OptionalIntergrated获取非强检器具信息
    List<OptionalCheckDetail> getAllByOptionalIntergrated(OptionalIntegrated optionalIntegrated);
}
