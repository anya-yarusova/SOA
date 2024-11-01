package com.anyarusova.lab02_bars_sevice.service;

import com.anyarusova.lab02_bars_sevice.client.LabWorkClient;
import com.anyarusova.lab02_bars_sevice.dto.LabWorkData;
import com.anyarusova.lab02_bars_sevice.service.model.LabWork;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import org.dozer.DozerBeanMapper;

@ApplicationScoped
public class BarsServiceImpl implements BarsService {

    @Inject
    private LabWorkClient labWorkClient;
    private final DozerBeanMapper mapper = new DozerBeanMapper();

    @Override
    public LabWorkData increaseDifficulty(long id, int stepsCount) {
        LabWorkData labWorkData = labWorkClient.getLabWorkById(id);
        if (labWorkData == null) {
            throw new NotFoundException("Лабораторная работа не найдена");
        }
        LabWork labWork = mapper.map(labWorkData, LabWork.class);

        labWork.increaseDifficulty(stepsCount);

        LabWorkData updatedLabWorkData = mapper.map(labWork, LabWorkData.class);
        labWorkClient.updateLabWork(id, updatedLabWorkData);

        return updatedLabWorkData;
    }

    @Override
    public LabWorkData decreaseDifficulty(long id, int stepsCount) {
        LabWorkData labWorkData = labWorkClient.getLabWorkById(id);
        if (labWorkData == null) {
            throw new NotFoundException("Лабораторная работа не найдена");
        }
        LabWork labWork = mapper.map(labWorkData, LabWork.class);

        labWork.decreaseDifficulty(stepsCount);

        LabWorkData updatedLabWorkData = mapper.map(labWork, LabWorkData.class);
        labWorkClient.updateLabWork(id, updatedLabWorkData);

        return updatedLabWorkData;
    }
}
