package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.StandardFile;
import com.mengyunzhi.measurement.repository.StandardFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by panjie on 17/5/5.
 * 标准装置
 */
@Service
public class StandardFileServiceImpl implements StandardFileService {
    @Autowired
    private StandardFileRepository standardFileRepository;
    @Override
    public StandardFile save(StandardFile standardFile) {
        standardFileRepository.save(standardFile);
        return standardFile;
    }

    @Override
    public List<StandardFile> getAll() {
        List<StandardFile> list = new ArrayList<StandardFile>();
        list = (List<StandardFile>) standardFileRepository.findAll();

        return list;
    }
}
