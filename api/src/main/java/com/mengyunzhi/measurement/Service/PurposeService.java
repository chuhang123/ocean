package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.Purpose;

import java.util.List;

/**
 * Created by zhangjiahao on 2017/6/13.
 * 用途M层（基础数据只有getAll方法）
 */
public interface PurposeService {
    //    获取index界面数据
    List<Purpose> getAll();
    Purpose getOnePurpose();
}
