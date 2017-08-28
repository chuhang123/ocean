package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.StandardDevice;
import com.mengyunzhi.measurement.repository.StandardDeviceCheckDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by liming on 17-5-14.
 */
public interface StandardDeviceCheckDetailService {
    //保存数据
    StandardDeviceCheckDetail save(StandardDeviceCheckDetail standardDeviceCheckDetail);
    // 编辑数据
    StandardDeviceCheckDetail update(StandardDeviceCheckDetail standardDeviceCheckDetail , Long id);
    // 删除数据
    void delete(Long id);
    //根据standardDeviceCheckDetailId获取相应的检定信息
    List<StandardDeviceCheckDetail> getAllByStandardDevice(StandardDevice standardDevice);
    //根据标准器ID获取所有的标准器检定信息
    Page<StandardDeviceCheckDetail> pageAllByStandardDeviceId(Long standardDeviceId, Pageable pageable);
}
