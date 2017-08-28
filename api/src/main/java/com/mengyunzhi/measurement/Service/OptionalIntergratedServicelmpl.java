package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.OptionalIntegrated;
import com.mengyunzhi.measurement.repository.OptionalIntergratedRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liming on 17-4-26.
 */
@Service
public class OptionalIntergratedServicelmpl implements OptionalIntergratedService  {

    @Autowired
    private OptionalIntergratedRepository optionalIntergratedRepository;

    @Override
    public OptionalIntegrated save(OptionalIntegrated optionalIntegrated) {

        //保存数据
        optionalIntergratedRepository.save(optionalIntegrated);

        return optionalIntegrated;
    }

    @Override
    public List<OptionalIntegrated> getAll() {

        List<OptionalIntegrated> list = new ArrayList<OptionalIntegrated>();
        list = (List<OptionalIntegrated>)optionalIntergratedRepository.findAll();

        return list;
    }
}