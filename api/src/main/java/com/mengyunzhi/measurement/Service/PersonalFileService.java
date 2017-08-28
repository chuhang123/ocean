package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.PersonalFile;

import java.util.List;

/**
 * Created by liming on 17-4-28.
 */
public interface PersonalFileService {

    //保存数据
    PersonalFile save(PersonalFile personalFile);

    //获取所有数据
    List<PersonalFile> getAll();
}
