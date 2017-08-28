package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.PersonalFile;
import com.mengyunzhi.measurement.repository.PersonalFileRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liming on 17-4-28.
 */
@Service
public class PersonalFileServicelmpl implements PersonalFileService{

    @Autowired
    private PersonalFileRepository personalFileRepository;
    @Override
    public PersonalFile save(PersonalFile personalFile) {
        //保存
        personalFileRepository.save(personalFile);
        return personalFile;
    }

    @Override
    public List<PersonalFile> getAll() {

        List<PersonalFile> list = new ArrayList<PersonalFile>();
        list = (List<PersonalFile>) personalFileRepository.findAll();
        return list;
    }
}
