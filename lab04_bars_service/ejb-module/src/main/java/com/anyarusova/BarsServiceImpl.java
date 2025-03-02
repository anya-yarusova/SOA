package com.anyarusova;

import com.anyarusova.client.LabWorkClient;
import com.anyarusova.dto.LabWorkData;
import com.anyarusova.dto.Response;
import com.anyarusova.interceptor.ExtendedException;
import com.anyarusova.service.model.LabWork;

import jakarta.ejb.Stateless;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import org.modelmapper.ModelMapper;


@Stateless
@WebService(name = "BarsService", serviceName = "BarsService")
@SOAPBinding
public class BarsServiceImpl implements BarsService {

    private static final LabWorkClient labWorkClient = new LabWorkClient();
    private final ModelMapper mapper = new ModelMapper();

    @WebMethod(operationName = "IncreaseDifficulty")
    @Override
    public Response increaseDifficulty(@WebParam(name = "id") long id, @WebParam(name = "stepsCount") int stepsCount) throws ExtendedException {
        return processDifficultyChange(id, stepsCount, true);
    }

    @WebMethod(operationName = "DecreaseDifficulty")
    @Override
    public Response decreaseDifficulty(@WebParam(name = "id")long id, @WebParam(name = "stepsCount") int stepsCount) throws ExtendedException {
        return processDifficultyChange(id, stepsCount, false);
    }

    private Response processDifficultyChange(
            long id,
            int stepsCount,
            boolean increase) throws ExtendedException {

        validate(id, stepsCount);
        LabWorkData labWorkData = labWorkClient.getLabWorkById(id);
        LabWork labWork = mapper.map(labWorkData, LabWork.class);

        if (increase) {
            labWork.increaseDifficulty(stepsCount);
        } else {
            labWork.decreaseDifficulty(stepsCount);
        }

        LabWorkData updatedLabWorkData = mapper.map(labWork, LabWorkData.class);
        labWorkClient.updateLabWork(id, updatedLabWorkData);

        return new Response(jakarta.ws.rs.core.Response.Status.OK.getStatusCode(), updatedLabWorkData.toString());
    }

    private void validate(long id, int stepsCount) throws ExtendedException {
        if (id < 0 || stepsCount < 0) {
            throw new ExtendedException(jakarta.ws.rs.core.Response.Status.BAD_REQUEST, "Некорректный ID лабораторной работы или значение для количества шагов");
        }
    }
}
