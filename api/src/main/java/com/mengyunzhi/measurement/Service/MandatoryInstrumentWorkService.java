package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.Work;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by panjie on 17/7/16.
 * 强制检定工作
 */
public interface MandatoryInstrumentWorkService extends WorkService {
    Page<Work> pageOfCurrentLoginUser(Pageable pageable);
}
