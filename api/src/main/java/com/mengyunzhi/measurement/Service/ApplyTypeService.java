package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.ApplyType;

/**
 * Created by panjie on 17/7/6.
 * 申请类型M层
 */
public interface ApplyTypeService {
    ApplyType getOneApplyType();
    ApplyType getOneByWebAppMenuId(Long webAppMenuId);
}
