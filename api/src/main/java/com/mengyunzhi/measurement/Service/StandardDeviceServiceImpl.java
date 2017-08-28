package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.StandardDevice;
import com.mengyunzhi.measurement.repository.StandardDeviceRepository;
import com.mengyunzhi.measurement.repository.StandardFile;
import com.mengyunzhi.measurement.repository.StandardFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liming on 17-5-9.
 */
@Service
public class StandardDeviceServiceImpl implements StandardDeviceService {
    @Autowired
    private StandardDeviceRepository standardDeviceRepository;

    @Override
    public StandardDevice save(StandardDevice standardDevice) {

        return standardDeviceRepository.save(standardDevice);
    }

    @Override
    public StandardDevice findOne(Long id) {
        return standardDeviceRepository.findOne(id);
    }

    @Override
    public List<StandardDevice> getAllByDeviceSetId(Long deviceSetId) {
        List<StandardDevice> standardDevices = standardDeviceRepository.findAllByDeviceSetId(deviceSetId);
        return standardDevices;
    }

    @Override
    public StandardDevice update(Long id, StandardDevice standardDevice) {
        standardDevice.setId(id);
        return standardDeviceRepository.save(standardDevice);
    }


    @Override
    public void delete(Long id) {
        standardDeviceRepository.delete(id);
        return;
    }

    @Override
    public Page<StandardDevice> pageAllByDeviceSetId(Long deviceSetId, Pageable pageable) {
        return standardDeviceRepository.findAllByDeviceSetId(deviceSetId, pageable);
    }
}
