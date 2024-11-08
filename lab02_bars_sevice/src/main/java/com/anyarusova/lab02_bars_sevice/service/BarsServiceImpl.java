package com.anyarusova.lab02_bars_sevice.service;

import com.anyarusova.lab02_bars_sevice.client.LabWorkClient;
import com.anyarusova.lab02_bars_sevice.dto.LabWorkData;
import com.anyarusova.lab02_bars_sevice.interceptor.ExtendedException;
import com.anyarusova.lab02_bars_sevice.service.model.LabWork;
import jakarta.enterprise.context.ApplicationScoped;
import org.dozer.DozerBeanMapper;


@ApplicationScoped
public class BarsServiceImpl implements BarsService {

    private static final LabWorkClient labWorkClient = new LabWorkClient();
    private final DozerBeanMapper mapper = new DozerBeanMapper();

    @Override
    public LabWorkData increaseDifficulty(long id, int stepsCount) throws ExtendedException {
        LabWorkData labWorkData = labWorkClient.getLabWorkById(id);
        LabWork labWork = mapper.map(labWorkData, LabWork.class);

        labWork.increaseDifficulty(stepsCount);

        LabWorkData updatedLabWorkData = mapper.map(labWork, LabWorkData.class);
        labWorkClient.updateLabWork(id, updatedLabWorkData);

        return updatedLabWorkData;
    }

    @Override
    public LabWorkData decreaseDifficulty(long id, int stepsCount) throws ExtendedException {
        LabWorkData labWorkData = labWorkClient.getLabWorkById(id);
        LabWork labWork = mapper.map(labWorkData, LabWork.class);

        labWork.decreaseDifficulty(stepsCount);

        LabWorkData updatedLabWorkData = mapper.map(labWork, LabWorkData.class);
        labWorkClient.updateLabWork(id, updatedLabWorkData);

        return updatedLabWorkData;
    }
}
