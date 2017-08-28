package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.StandardAuthorization;

import java.util.List;

/**
 * Created by liming on 17-5-7.
 */
public interface StandardAuthorizationServie {
    StandardAuthorization save(StandardAuthorization standardAuthorization);

    List<StandardAuthorization> getAll();
}
