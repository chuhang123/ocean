package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.OptionalIntegrated;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by liming on 17-4-26.
 */
public interface OptionalIntergratedService {
    //保存单个对象
    OptionalIntegrated save(OptionalIntegrated optionalIntegrated);

    //取出表中的所有数据
    List<OptionalIntegrated> getAll();
}