package com.anyarusova;

import com.anyarusova.client.LabWorkClient;
import com.anyarusova.dto.LabWorkData;
import com.anyarusova.interceptor.ExtendedException;
import com.anyarusova.service.model.LabWork;

import jakarta.ejb.Remote;
import jakarta.ejb.Stateless;
import org.modelmapper.ModelMapper;


@Stateless
public class BarsServiceImpl implements BarsService {

    private static final LabWorkClient labWorkClient = new LabWorkClient();
    private final ModelMapper mapper = new ModelMapper();

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
