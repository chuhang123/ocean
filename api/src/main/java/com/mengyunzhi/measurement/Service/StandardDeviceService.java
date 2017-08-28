package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.StandardDevice;
import com.mengyunzhi.measurement.repository.StandardFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by liming on 17-5-9.
 */
public interface StandardDeviceService {
    //保存
    StandardDevice save(StandardDevice standardDevice);
    //查找一个StandardDevice
    StandardDevice findOne(Long id);
    //根据standardFileId获取相应的标准器
    List<StandardDevice> getAllByDeviceSetId(Long deviceSetId);
    //更新标准器
    StandardDevice update(Long id, StandardDevice standardDevice);
    //删除标准器
    void delete(Long id);
    //根据standardFileId获取相应的标准器分页
    Page<StandardDevice> pageAllByDeviceSetId(Long deviceSetId, Pageable pageable);
}
