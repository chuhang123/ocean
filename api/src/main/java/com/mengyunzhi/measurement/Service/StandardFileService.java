package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.StandardFile;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by panjie on 17/5/5.
 * 标准装置
 */
public interface StandardFileService {
    //保存标准装置
    StandardFile save(StandardFile standardFile);
    //获取全部标准装置
    List<StandardFile> getAll();
}
