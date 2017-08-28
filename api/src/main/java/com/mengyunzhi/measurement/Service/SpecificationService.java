package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.Specification;

import java.util.List;

/**
 * Created by zhangjiahao on 2017/6/27.
 * 规格型号 M层
 */
public interface SpecificationService {
    //保存方法
    Specification save(Specification specification);

    //删除方法
    void delete(Long id);

    //更新方法
    void update(Long id, Specification specification);

    //获取index界面全部数据
    List<Specification> getAll();
}
