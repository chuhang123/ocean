package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.StandardAuthorization;
import com.mengyunzhi.measurement.repository.StandardAuthorizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liming on 17-5-7.
 */
@Service
public class StandardAuthorizationServieImpl implements StandardAuthorizationServie {
    @Autowired
    private StandardAuthorizationRepository standardAuthorizationRepository;

    @Override
    public StandardAuthorization save(StandardAuthorization standardAuthorization) {
        standardAuthorizationRepository.save(standardAuthorization);
        return standardAuthorization;
    }

    @Override
    public List<StandardAuthorization> getAll() {
        List<StandardAuthorization> list = new ArrayList<StandardAuthorization>();
        list = (List<StandardAuthorization>) standardAuthorizationRepository.findAll();

        return list;
    }
}
