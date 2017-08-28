package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.InstrumentCertificateType;
import com.mengyunzhi.measurement.repository.InstrumentCertificateTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liming on 17-6-8.
 * 器具证书状态类别Service实现
 */
@Service
public class InstrumentCertificateTypeServiceImpl implements InstrumentCertificateTypeService {
    @Autowired
    private InstrumentCertificateTypeRepository instrumentCertificateTypeRepository;
    @Override
    public InstrumentCertificateType save(InstrumentCertificateType instrumentCertificateType) {
        return instrumentCertificateTypeRepository.save(instrumentCertificateType);
    }

    @Override
    public InstrumentCertificateType get(Long id) {
        return instrumentCertificateTypeRepository.findOne(id);
    }

    @Override
    public void delete(Long id) {
        instrumentCertificateTypeRepository.delete(id);
        return;
    }

    @Override
    public List<InstrumentCertificateType> getAll() {
        return (List<InstrumentCertificateType>) instrumentCertificateTypeRepository.findAll();
    }
}
