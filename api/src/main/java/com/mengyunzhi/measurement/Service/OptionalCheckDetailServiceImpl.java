package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.OptionalCheckDetail;
import com.mengyunzhi.measurement.repository.OptionalCheckDetailRepository;
import com.mengyunzhi.measurement.repository.OptionalIntegrated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 0000 on 2017/5/9.
 * 非强检器具-检定信息
 */
@Service
public class OptionalCheckDetailServiceImpl implements OptionalCheckDetailService {
    @Autowired
    private OptionalCheckDetailRepository optionalCheckDetailRepository;

    @Override
    public OptionalCheckDetail save(OptionalCheckDetail optionalCheckDetail) {
        try{
            optionalCheckDetailRepository.save(optionalCheckDetail);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return optionalCheckDetail;
    }

    @Override
    public List<OptionalCheckDetail> getAll() {

        List<OptionalCheckDetail> list = new ArrayList<OptionalCheckDetail>();
        list = (List<OptionalCheckDetail>) optionalCheckDetailRepository.findAll();
        return list;
    }

    @Override
    public List<OptionalCheckDetail> getAllByOptionalIntergrated(OptionalIntegrated optionalIntegrated) {
        List<OptionalCheckDetail> list = new ArrayList<OptionalCheckDetail>();
        //根据非强检器具获取检验信息
        list = optionalCheckDetailRepository.getAllByOptionalIntegrated(optionalIntegrated);

        return list;
    }
}
